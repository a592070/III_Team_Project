package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {
    public static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int len;
        byte[] buf = new byte[4194304]; //4MB
        while((len=in.read(buf)) != -1){
            buffer.write(buf, 0, len);
        }
        byte[] bytes = buffer.toByteArray();
        buffer.close();
        return bytes;
    }
}
