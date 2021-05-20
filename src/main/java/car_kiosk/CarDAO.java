package car_kiosk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private CarDAO() {
		try {
			Class.forName("org.sqlite.JDBC"); 
			//System.out.println("Driver Loading Success!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static CarDAO carDao = new CarDAO();
	
	public static CarDAO getInstance() {//외부에서 new연산자 못쓰게 막아주는 역할
		if(carDao == null)
			carDao = new CarDAO();//비어있으면 생성해주고
		return carDao;//없으면 기존꺼 가져옴
	}
	

	//db 연동 메소드
	public Connection getConnection() {
		
		try {
			String dbFile = "carKiosk.db";
			String dbFileUrl ="jdbc:sqlite:"+dbFile;
			conn=DriverManager.getConnection(dbFileUrl);
			System.out.println("DB Connection Success!");	
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return conn;
	}
	
	//Car테이블 전체 내용 CarDTO에 저장하는 메소드
	public List<CarDTO> GetTable(){
		List<CarDTO> carList = new ArrayList();
		String sql = "SELECT * FROM Car ";
		try {
			getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {//전체 테이블 탐색
				CarDTO carDTO = new CarDTO(
						rs.getString("car_Name")	
						,rs.getString("car_Num")
						,rs.getBoolean("isKey")
						,rs.getString("car_Url")
						,rs.getString("size")
						);
				//db안을 검색하므로 db안의 필드명을 써야함
				carList.add(carDTO);//sql과 일치하는거 carList에 넣는다. 
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return carList;
	}
	
	
	//찾고싶은 차 이름으로 검색해서 리스트에 넣는 메소드 -> Car_Detail에서 다음버튼 누르면 콘솔창에 찍힘
	public List<CarDTO> SelectTable(String carName){
			List<CarDTO> carList = new ArrayList();
			
			String sql = "SELECT * FROM Car WHERE car_Name LIKE ? AND isKey LIKE '1'";
			
			try {
				getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, carName);//carName과 true값인걸 들고오겠다
				
				rs=pstmt.executeQuery();

				while(rs.next()) {//전체 테이블 탐색
					CarDTO carDTO = new CarDTO(
							rs.getString("car_Name")	
							,rs.getString("car_Num")
							,rs.getBoolean("isKey")
							,rs.getString("car_Url")
							,rs.getString("size")
							);
					//db안을 검색하므로 db안의 필드명을 써야함
					carList.add(carDTO);//sql과 일치하는거 carList에 넣는다. 
				}
		
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try{
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return carList;
		}
	
	//차량 배정후  CAR테이블 iskey false로 변경 메서드
	public void setIsKey(String carNum){
			
			String sql = "UPDATE Car SET isKey = '0' WHERE car_Num = '"+carNum+"'";
			System.out.println(sql);
			try {
				getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
		
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try{
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	//자동차 사이즈 DAO 
	public List<ChargeDTO> SelectCharge(String carSize) {
	      int result=0;
	      String sql = "SELECT * FROM Charge WHERE car_Size = '"+carSize+"'";
	      List<ChargeDTO> chargeList = new ArrayList();
	      try { 
	         getConnection();
	         pstmt = conn.prepareStatement(sql);
	         rs=pstmt.executeQuery();

	         while(rs.next()) {//전체 테이블 탐색
	            ChargeDTO chargeDTO = new ChargeDTO(
	                  rs.getString("car_Size")   
	                  ,rs.getInt("ten_Min")
	                  ,rs.getInt("one_Hour")
	                  ,rs.getInt("three_Hour")
	                  ,rs.getInt("six_Hour")
	                  ,rs.getInt("one_Day")
	                  ,rs.getInt("three_Day")
	                  );
	            chargeList.add(chargeDTO);
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try{
	            if(rs!=null)rs.close();
	            if(pstmt!=null)pstmt.close();
	            if(conn!=null)pstmt.close();
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	      }
	      return chargeList;
	   }

}
