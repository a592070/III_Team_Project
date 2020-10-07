package innocence741;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.naming.directory.DirContext;
import javax.sql.DataSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.ConnectionPool;
import pojo.AccountDO;


public class hsrDAO {
	private Connection conn;
	private DataSource ds;
	private String sql;
	private static List<hsrDO> hsrlist;
	private List<hsrDO> hsrList2user =new ArrayList<hsrDO>();
	
	public hsrDAO(int dataSourceType) throws IOException, SQLException {
		ds = ConnectionPool.getDataSource(dataSourceType);
		if (hsrlist == null || hsrlist.size() == 0) {
			hsrInit();
		}
	}
	
	public void hsrInit() throws SQLException {
		hsrlist = new ArrayList<>();
		try {
			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			sql = "select * from highspeedrail";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				hsrDO hsrDO = new hsrDO();
				
				hsrDO.setSnSchedule(rs.getInt("SN_SCHEDULE"));
				hsrDO.setIdHSR(rs.getString("ID_HSR"));
				hsrDO.setDirection(rs.getString("DIRECTION"));
				hsrDO.setNangang(rs.getString("NANGANG"));
				hsrDO.setTaipei(rs.getString("TAIPEI"));
				hsrDO.setBanqiao(rs.getString("BANQIAO"));
				hsrDO.setTaoyuan(rs.getString("Taoyuan"));
				hsrDO.setHsinchu(rs.getString("Hsinchu"));
				hsrDO.setMiaoli(rs.getString("Miaoli"));
				hsrDO.setTaichung(rs.getString("Taichung"));
				hsrDO.setChanghua(rs.getString("Changhua"));
				hsrDO.setYunlin(rs.getString("Yunlin"));
				hsrDO.setChiayi(rs.getString("Chiayi"));
				hsrDO.setTainan(rs.getString("Tainan"));
				hsrDO.setZuoying(rs.getString("Zuoying"));

				hsrlist.add(hsrDO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	
	public List<hsrDO> listHsrDO(){
		return hsrList2user;
	}
	
	public int location2num(String location) {
		int snNumLocation = 0;
		if(location.equals("Nangang") || location.equals("nangang")) {
			snNumLocation = 0;
		}else if(location.equals("Taipei") || location.equals("taipei")) {
			snNumLocation = 1;
		}else if (location.equals("Banqiao") || location.equals("banqiao")) {
			snNumLocation = 2;
		}else if (location.equals("Taoyuan") || location.equals("taoyuan")) {
			snNumLocation = 3;
		}else if (location.equals("Hsinchu") || location.equals("hsinchu")) {
			snNumLocation = 4;
		}else if (location.equals("Miaoli") || location.equals("miaoli")) {
			snNumLocation = 5;
		}else if (location.equals("Taichung") || location.equals("taichung")) {
			snNumLocation = 6;
		}else if (location.equals("Changhua") || location.equals("changhua")) {
			snNumLocation = 7;
		}else if (location.equals("Yunlin") || location.equals("yunlin")) {
			snNumLocation = 8;
		}else if (location.equals("Chiayi") || location.equals("chiayi")) {
			snNumLocation = 9;
		}else if (location.equals("Tainan") || location.equals("tainan")) {
			snNumLocation = 10;
		}else if (location.equals("Zuoying") || location.equals("zuoying")) {
			snNumLocation = 11;
		}
		return snNumLocation;
	}
	
    public String getDirection(String startPoint, String destination) {
    	int tmp1 = location2num(startPoint);
    	int tmp2 = location2num(destination);
    	int dirNum = tmp2 - tmp1;
    	String dir = new String();
    	if(dirNum > 0) {
    		dir = "downSouth";
    	}else if (dirNum < 0) {
			dir = "goNorth";
		}
    	return dir;
    }
    
    public void searchHSR(String startPoint, String destination, String departureTime) {
    	String direction = getDirection(startPoint, destination);
    	for (int i = 0;  i <hsrlist.size(); i++) {
//    		System.out.print(hsrlist.get(i).getDirection().equals(direction)+"  ");
//    		System.out.print(hsrlist.get(i).getArriveTime(startPoint)+"  ");
//    		System.out.println(hsrlist.get(i).getArriveTime(destination));
    		if( Objects.equals(direction,hsrlist.get(i).getDirection()) && hsrlist.get(i).getArriveTime(startPoint)!=null && hsrlist.get(i).getArriveTime(destination)!=null ) {
    			hsrList2user.add(hsrlist.get(i));
    		}
    	}
    }
    
    public int ticketPrice(String startPoint, String destination) {
    	int price = 0;
    	int tmp1 = location2num(startPoint);
    	int tmp2 = location2num(destination);
    	int[][] priceTable = {{0,40,70,200,330,480,750,870,970,1120,1390,1530},
    						  {40,0,40,160,290,430,700,820,930,1080,1350,1490},
    						  {70,40,0,130,260,400,670,790,900,1050,1320,1460},
    						  {200,160,130,0,130,280,540,670,780,920,1190,1330},
    						  {330,290,260,130,0,140,410,540,640,790,1060,1200},
    						  {480,430,400,280,140,0,270,390,500,640,920,1060},
    						  {750,700,670,540,410,270,0,130,230,380,650,790},
    						  {870,820,790,670,540,390,130,0,110,250,530,670},
    						  {970,930,900,780,640,500,230,110,0,150,420,560},
    						  {1390,1350,1320,1190,1060,920,650,530,420,280,0,140},
    						  {1530,1490,1460,1330,1200,1060,790,670,560,410,140,0}};
    	price = priceTable[tmp1][tmp2];
    	
		return price;
	}
	
    public void getSN_Schedule(String idHSR) {
    	for (int i = 0;  i <hsrlist.size(); i++) {
//    		System.out.print(hsrlist.get(i).getDirection().equals(direction)+"  ");
//    		System.out.print(hsrlist.get(i).getArriveTime(startPoint)+"  ");
//    		System.out.println(hsrlist.get(i).getArriveTime(destination));
			System.out.println("fucc"+i);

    		if(hsrlist.get(i).getIdHSR().equals(idHSR)) {
    			hsrList2user.add(hsrlist.get(i));
    			System.out.println("haha");
    			break;
    		}
    	}
    }
	

//	public static void main(String[] args) throws IOException, SQLException {
//		// TODO Auto-generated method stub
//		List<hsrDO> list;
//		hsrDAO hsrDAO = new hsrDAO(ConnectionPool.LOADING_WITHOUT_SERVER);
//		hsrDAO.searchHSR("Nangang", "Zuoying", "23:00");
//		list = hsrDAO.listHsrDO();
//		System.out.println(list.size());
////		for(int i = 0; i < list.size(); i++) {
////			if(a == 1) {
////				System.out.println(list.get(i).getSnSchedule());
////			}else if(a == 2) {
////				System.out.println(list.get(i).getIdHSR());
////			}else if (a == 3) {
////				System.out.println(list.get(i).getDirection());
////			}else if(a == 4 ) {
////				System.out.println(list.get(i).getNangang());
////			}
////		}
////		System.out.println(list.get(0).getArriveTime("Banqiao"));
//        ObjectMapper objectMapper = new ObjectMapper();
//        String ujson = objectMapper.writeValueAsString(list);
//        System.out.println(ujson);
//
//
//	}

}
