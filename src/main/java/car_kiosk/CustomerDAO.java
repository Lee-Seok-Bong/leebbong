package car_kiosk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomerDAO {

	private int last_seq;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private CustomerDAO() {
		try {
			Class.forName("org.sqlite.JDBC"); 
			//System.out.println("Driver Loading Success!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static CustomerDAO custmDao = new CustomerDAO();
	
	public static CustomerDAO getInstance() {//외부에서 new연산자 못쓰게 막아주는 역할
		if(custmDao == null)
			custmDao = new CustomerDAO();//비어있으면 생성해주고
		return custmDao;//없으면 기존꺼 가져옴
	}
	

	//db 연동 메소드
	public Connection getConnection() {
		
		try {
			String dbFile = "carKiosk.db";
			String dbFileUrl ="jdbc:sqlite:"+dbFile;
			conn=DriverManager.getConnection(dbFileUrl);
			System.out.println("DB Connection Success!");
			
			String dropSql = "SELECT MAX(customer_Num) FROM Customer";// 마지막 seq 값 가져오는 SQL문		
			pstmt = conn.prepareStatement(dropSql);	
			pstmt.execute();
			rs = pstmt.executeQuery();
			String seq = rs.getString(1);
			last_seq = Integer.parseInt(seq);
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return conn;
	}
	
	//기존의 Customer 테이블 없애고 Customer 테이블 생성하는 메소드
	public void CreateTable( String tableName){
			getConnection();
			try {
				stmt = conn.createStatement();
				String dropSql = "Drop table "+tableName;		
				String createSql = "create table if not exists "+tableName
						+ "(customer_Num integer primary key autoincrement,li_Num text,li_Date text,ph_Num text,car_Num text,Agree boolean"
						+",lent_Time text, return_Time text,pay_Method text,pay_Money int"
						+",overpay_Method text,overpay_Money int,isKey boolean,start_Time text)";
					
				stmt.executeUpdate(dropSql);
				stmt.executeUpdate(createSql);

				stmt.close();
				conn.close();
		
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try{
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)pstmt.close();
				} catch (Exception e) {
			}
		}		
	}
		
	//동의 관련 정보 넣는 메서드 
	public void SetAgreement(CustomerDTO customerDTO) {
		getConnection();			
		String sql = "INSERT INTO Customer (Agree,li_Num,li_Date,ph_Num) VALUES (?,?,?,?)";
		//물음표에는 우리가 처리한 데이터 관련 넣기
		
	try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.setBoolean(1, customerDTO.isAgree());//물음표 순서대로 1->4
			pstmt.setString(2, customerDTO.getLi_num());
			pstmt.setString(3, customerDTO.getLi_Date());
			pstmt.setString(4, customerDTO.getPh_Num());
			
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
	}
	//차량선택 관련 정보 넣는 메서드 
	public void SetCar(CustomerDTO customerDTO) {
		getConnection();			
		String sql = "UPDATE Customer set car_Num = '"+customerDTO.getCar_Num()+"' where customer_Num = "+last_seq; //customer_Num 번호 처리 나중에 넣기
		//물음표에는 우리가 처리한 데이터 관련 넣기
		
	try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
	}
	//대여시간 선택 관련 정보 넣는 메서드 
	public void SetSelectTime(CustomerDTO customerDTO) {
		getConnection();			
		String sql = "UPDATE Customer set lent_Time = '"+customerDTO.getLent_Time()+"', pay_Money = '"+customerDTO.getPay_Money()+"' where customer_Num = "+last_seq; //customer_Num 번호 처리 나중에 넣기
		//물음표에는 우리가 처리한 데이터 관련 넣기
		
	try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
	}
	//결제방법 선택 관련 정보 넣는 메서드 
	public void SetPayMethod(CustomerDTO customerDTO) {
		getConnection();	
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm"); // 연/월/일 시:분
		String date = format.format(cal.getTime());
		
		String sql = "UPDATE Customer set pay_Method = '"+customerDTO.getPay_Method()+"', isKey = '1', start_Time = '"+date+"'"+" where customer_Num = "+last_seq; 
		//결제방법, 키분출, 시작시간 DB에 기록
		
		
	try {
			pstmt = conn.prepareStatement(sql);	
			
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
	}
	//Customer 테이블 전체 읽어오기
	public void GetLastRow() {
		getConnection();		
		List<CustomerDTO> lastRow = new ArrayList();
		String sql = "SELECT * FROM Customer"; //
		//물음표에는 우리가 처리한 데이터 관련 넣기
		
	try {
			pstmt = conn.prepareStatement(sql);	
			
			pstmt.execute();
			rs = pstmt.executeQuery();
			int i = 0;
			while(rs.next()) {//전체 테이블 탐색
				
				CustomerDTO customerDTO = new CustomerDTO(
						 rs.getString("li_Num")
						,rs.getString("li_Date")
						,rs.getString("ph_Num")
						,rs.getString("car_Num")
						,rs.getBoolean("Agree")
						,rs.getString("lent_Time")
						,rs.getString("return_Time")
						,rs.getString("pay_Method")
						,rs.getInt("pay_Money")
						,rs.getString("overpay_Method")
						,rs.getString("overpay_Money")
						,rs.getBoolean("isKey")
						,rs.getString("start_Time")
						);
				//db안을 검색하므로 db안의 필드명을 써야함
				lastRow.add(customerDTO);//sql과 일치하는거 carList에 넣는다. 
				System.out.println("순번 "+ (i+1) +" " + lastRow.get(i)); i++;
			}
			last_seq = i;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
	}
	
	//Customer 렌트중인 차량 검색 메서드
	public List<CustomerDTO> SearchLentCar() {
		getConnection();		
		CustomerDTO customer_car = CustomerDTO.getInstance();
		List<CustomerDTO> ct_lent_Car = new ArrayList();
		String sql = "SELECT * FROM Customer WHERE car_num LIKE '_____" +customer_car.getCar_Num()+"' AND isKey LIKE '1'"; //
		//물음표에는 우리가 처리한 데이터 관련 넣기
		
	try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.execute();
			rs = pstmt.executeQuery();
			int i = 0;
			while(rs.next()) {//전체 테이블 탐색
				
				CustomerDTO customerDTO = new CustomerDTO(
						 rs.getString("li_Num")
						,rs.getString("li_Date")
						,rs.getString("ph_Num")
						,rs.getString("car_Num")
						,rs.getBoolean("Agree")
						,rs.getString("lent_Time")
						,rs.getString("return_Time")
						,rs.getString("pay_Method")
						,rs.getInt("pay_Money")
						,rs.getString("overpay_Method")
						,rs.getString("overpay_Money")
						,rs.getBoolean("isKey")
						,rs.getString("start_Time")
						);
				//db안을 검색하므로 db안의 필드명을 써야함
				ct_lent_Car.add(customerDTO);//sql과 일치하는거 carList에 넣는다. 
				System.out.println("선택된 차량 "+ (i+1) +" " + ct_lent_Car.get(i));;
			};
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	return ct_lent_Car;			
	}
	
	//차량 배정후  CAR테이블 iskey false로 변경 메서드
	public void setRetrunCar(String carNum){
			System.out.println(carNum);
			String sql = "UPDATE Car SET isKey = '1' WHERE car_Num = '"+carNum+"';"; // 반납후 Car 테이블 isKey 상태 true로 변경
			String sql2 = "UPDATE Customer SET isKey = '0' WHERE car_Num = '"+carNum+"' AND isKey LIKE '1';"; //차량 반납후 Custoemr 테이블 isKey '무'로 변경
			
			
			try {
				getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				pstmt = conn.prepareStatement(sql2);
				pstmt.executeUpdate();
				System.out.println(carNum+" 반납완료^^");
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
	// 총 이용 시간을 구하는 메소드, 매개변수 : 차량번호(ex - 2010, 1013, ...)
		public String date_Diff(String car_Num) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm"); // 연/월/일 시:분
			
			// 대여 시각 & 반납 시각 String 타입
			String date_1 = null;
			String date_2 = format.format(cal.getTime());
			
			// 대여 시각 & 반납 시각 Date 타입
			Date start_Date = null;
			Date last_Date = null;
			
			getConnection();
			String sql = String.format("SELECT start_Time FROM CUSTOMER WHERE car_Num LIKE \'%%%s\';", car_Num) ;
			
			try {
				pstmt = conn.prepareStatement(sql);	
				pstmt.execute();
				rs = pstmt.executeQuery();
				while(rs.next()) {
					date_1 = rs.getString("start_Time"); // 21/05/12 18:40
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				start_Date = format.parse(date_1);
				last_Date = format.parse(date_2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			long diff = last_Date.getTime() - start_Date.getTime();

			long diffDays = diff / (24 * 60 * 60 * 1000); // 일
			diff %= (24 * 60 * 60 * 1000);
			long diffHours = diff / (60 * 60 * 1000);     // 시
			diff %= (60 * 60 * 1000);
			long diffMinutes = diff / (60 * 1000);        // 분		
			diff %= (60 * 1000);
			
			String result = diffDays + "일 " + diffHours + "시 " + diffMinutes + "분";
			
			return result;
		}
		// 속성, 속성값을 입력 시 조건문으로 하나의 엔터티를 출력하는 메소드 (ex - select_One(start_Time) )
		public CustomerDTO select_One(String attr, String attr_v) {
			getConnection();
			CustomerDTO result = null;
			
			String query = String.format("SELECT * FROM CUSTOMER WHERE %S LIKE \'%%%S%%\';", attr, attr_v);
			try {
				pstmt = conn.prepareStatement(query);	
				pstmt.execute();
				rs = pstmt.executeQuery();
				result = new CustomerDTO(
						 rs.getString("li_Num")
						,rs.getString("li_Date")
						,rs.getString("ph_Num")
						,rs.getString("car_Num")
						,rs.getBoolean("Agree")
						,rs.getString("lent_Time")
						,rs.getString("return_Time")
						,rs.getString("pay_Method")
						,rs.getInt("pay_Money")
						,rs.getString("overpay_Method")
						,rs.getString("overpay_Money")
						,rs.getBoolean("isKey")
						,rs.getString("start_Time")
						);
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			return result;
		}

}



