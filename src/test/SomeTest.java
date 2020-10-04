package test;

import controller.ConnectionPool;
import org.junit.Test;
import utils.IOUtils;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

public class SomeTest {
    @Test
    public void testInputBlob() throws IOException, SQLException {
        String path = "resources/分工圖.jpg";

        AccountDOForTest accountDOForTest = new AccountDOForTest();
        accountDOForTest.setUsername("admin");
        accountDOForTest.setPassword("admin");
        Blob blob = IOUtils.fileToBlob(path);
        accountDOForTest.setBlob(blob);

        // 將bean寫入DB
        DataSource ds = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITHOUT_SERVER);
        Connection conn = ds.getConnection();
        String sql = "insert into account(username, password, picture) values(?,?,?)";
        PreparedStatement predStmt = conn.prepareStatement(sql);

        predStmt.setString(1, accountDOForTest.getUsername());
        predStmt.setString(2, accountDOForTest.getPassword());

        InputStream blobStream = null;
        if(accountDOForTest.getBlob() != null) blobStream = accountDOForTest.getBlob().getBinaryStream();
        predStmt.setBlob(3, blobStream);

        predStmt.executeUpdate();
        conn.commit();
        if(blobStream!=null) blobStream.close();
        ConnectionPool.closeResources(conn, predStmt, null);
    }

    @Test
    public void testOutputBlob() throws IOException, SQLException {

        // 從DB讀取file
        DataSource ds = ConnectionPool.getDataSource(ConnectionPool.LOADING_WITHOUT_SERVER);
        Connection conn = ds.getConnection();
        String sql = "select username, picture from account where username=?";
        PreparedStatement predStmt = null;
        ResultSet rs = null;

        rs = ConnectionPool.execute(conn, predStmt, rs, sql, new Object[]{"admin"});

        AccountDOForTest accountDOForTest = new AccountDOForTest();
        if(rs.next()){
            accountDOForTest.setUsername(rs.getString(1));
            accountDOForTest.setBlob(rs.getBlob(2));
        }

        ConnectionPool.closeResources(conn, predStmt, rs);


        // 寫出file
        String dest = "resources/"+accountDOForTest.getUsername()+".jpg";
        InputStream in = accountDOForTest.getBlob().getBinaryStream();
        OutputStream out = new FileOutputStream(dest);
        byte[] buf = new byte[8192];
        int len = 0;
        while((len=in.read(buf)) != -1){
            out.write(buf, 0, len);
        }

        in.close();
        out.close();
    }
}
