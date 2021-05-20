package car_kiosk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
//차량 url이미지 경로 설정, 차종 설정, 
public class Return_Detail extends JFrame{

	private JFrame frame;
	private static String car_Four;
	List<CustomerDTO> return_select_car;
	List<CarDTO> return_Car;

		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Return_Detail window = new Return_Detail(car_Four);
						window.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

	
	public Return_Detail(String car_Four) {
		this.car_Four = car_Four;
		initialize();
	}

	private void initialize() {
		CarDAO carDAO = CarDAO.getInstance();
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		CustomerDTO customerDTO = customerDAO.select_One("car_Num", car_Four);
		return_select_car = customerDAO.SearchLentCar();//반납할 차량 정보 조회
		return_Car = carDAO.GetTable(); //반납할 차량 정보 return_car에 저장.
		
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("\uCF54\uB4DC\uC758 \u7F8E");
		frame.getContentPane().setBackground(new Color(230, 230, 250));
		frame.setBounds(500, 0, 960, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("반납 정보");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		lblNewLabel.setBounds(327, 18, 300, 100);
		frame.getContentPane().add(lblNewLabel);
		
		ImageIcon image1;
		String url = String.format(return_car_url(customerDTO.getCar_Num()));
		image1 = new ImageIcon(url);
		Image img = image1.getImage();
		Image changImg = img.getScaledInstance(720, 360, Image.SCALE_SMOOTH);
		ImageIcon changIcon = new ImageIcon(changImg);
		
		JLabel img_Label = new JLabel(changIcon);
		img_Label.setHorizontalAlignment(SwingConstants.CENTER);
		img_Label.setBounds(114, 121, 720, 360);
		frame.getContentPane().add(img_Label);

		JPanel panel = new JPanel();
		panel.setBounds(12, 491, 930, 367);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(12, 10, 906, 350);
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(7, 2, 5, 5));
		
		JLabel lb_1 = new JLabel("차종 :");
		lb_1.setHorizontalAlignment(SwingConstants.CENTER);
		lb_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		panel_1.add(lb_1);
		JTextField text_1 = new JTextField();
		text_1.setText(return_car_size(customerDTO.getCar_Num()));
		text_1.setHorizontalAlignment(SwingConstants.CENTER);
		text_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		panel_1.add(text_1);
		
		JLabel lb_2 = new JLabel("차량 번호 :");
		lb_2.setHorizontalAlignment(SwingConstants.CENTER);
		lb_2.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		panel_1.add(lb_2);
		JTextField text_2 = new JTextField();
		text_2.setText(customerDTO.getCar_Num());
		text_2.setHorizontalAlignment(SwingConstants.CENTER);
		text_2.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		panel_1.add(text_2);
		
		JLabel lb_3 = new JLabel("서비스 시작 시간 :");
		lb_3.setHorizontalAlignment(SwingConstants.CENTER);
		lb_3.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		panel_1.add(lb_3);
		JTextField text_3 = new JTextField();
		text_3.setText(return_select_car.get(0).getStart_Time());
		text_3.setHorizontalAlignment(SwingConstants.CENTER);
		text_3.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		panel_1.add(text_3);
		
		JLabel lb_3_1 = new JLabel("서비스 종료 시간 :");
		lb_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lb_3_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		panel_1.add(lb_3_1);
		
		textField_retunTime = new JTextField();
		textField_retunTime.setText((String) null);
		textField_retunTime.setHorizontalAlignment(SwingConstants.CENTER);
		textField_retunTime.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		Calendar cal = Calendar.getInstance(); // 시간 캘린더 메서드
		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm"); // 연/월/일 시:분
		String date = format.format(cal.getTime()); //시간 String 변환
		textField_retunTime.setText(date);
		panel_1.add(textField_retunTime);
		
		JLabel lb_4 = new JLabel("이용 시간 :");
		lb_4.setHorizontalAlignment(SwingConstants.CENTER);
		lb_4.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		panel_1.add(lb_4);
		JTextField text_4 = new JTextField();
		text_4 = new JTextField();
		text_4.setText(customerDAO.date_Diff(car_Four));
		text_4.setHorizontalAlignment(SwingConstants.CENTER);
		text_4.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		panel_1.add(text_4);
		
		JLabel lb_5 = new JLabel("선불 요금 :");
		lb_5.setHorizontalAlignment(SwingConstants.CENTER);
		lb_5.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		panel_1.add(lb_5);
		JTextField text_5 = new JTextField();
		text_5 = new JTextField();
		text_5.setText(com_Money(customerDTO.getPay_Money()));
		text_5.setHorizontalAlignment(SwingConstants.CENTER);
		text_5.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		panel_1.add(text_5);
		
		JLabel lb_6 = new JLabel("추가 요금 :");
		lb_6.setHorizontalAlignment(SwingConstants.CENTER);
		lb_6.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		panel_1.add(lb_6);
		JTextField text_6 = new JTextField();
		text_6 = new JTextField();
		
		int extra_Charge = ex_Charge(customerDAO.date_Diff(car_Four), customerDTO.getLent_Time()); 
		// text_6.setText(com_Money(extra_Charge));
		text_6.setText("0 원");
		text_6.setHorizontalAlignment(SwingConstants.CENTER);
		text_6.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		panel_1.add(text_6);
		
		JButton btn_Pay = new JButton("결제");
		btn_Pay.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		btn_Pay.setContentAreaFilled(false);
		btn_Pay.setBounds(360, 876, 235, 72);
		frame.getContentPane().add(btn_Pay);
		
		JButton btn_Return = new JButton("반납");
		btn_Return.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		btn_Return.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(return_select_car.get(0).getCar_Num()+"의 iskey : "+ return_select_car.get(0).getIsKey());
				customerDTO.setCar_Num(text_2.getText());
				customerDAO.setRetrunCar(return_select_car.get(0).getCar_Num());
				main main = new main();
				frame.dispose();
			}
		});
		btn_Return.setContentAreaFilled(false);
		btn_Return.setBounds(360, 876, 235, 72);
		frame.getContentPane().add(btn_Return);
		text = new JTextField();
		text.setBounds(387, 570, 450, 45);
		frame.getContentPane().add(text);
		text.setText(customerDTO.getStart_Time());
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		
		if (text_6.getText().equals("0 원")) {
			btn_Pay.setVisible(false);
		}
		else {
			btn_Return.setVisible(false);
			
		}
	}
	//차량 url 가져오는 메소드
	private String return_car_url(String car_num) {
		for (CarDTO cardto: return_Car) {
			if(cardto.getCar_Num().equals(car_num)) { 
				car_num =  cardto.getCar_Url();
			}
		}return car_num;
	}
	//차종 이름 가져 오는 메소드
	private String return_car_size(String car_num) {
		for (CarDTO cardto: return_Car) {
			if(cardto.getCar_Num().equals(car_num)) {
				car_num =  cardto.getCar_Name();
			}
		}return car_num;
	}
	
	// 추가요금 구하는 메소드
	// date_1 = 사용한 시간 date_2 = 결제 시간
	String date_1 = null;
	String date_2 = null;
	private JTextField textField_retunTime;
	private JTextField text;
	private int ex_Charge(String date_1, String date_2) {
		this.date_1 = date_1; // 3일 1시간 21분
		this.date_2 = date_2; // 1일 2시간 32분
		date_1 = date_1.replace("일", "");
		date_1 = date_1.replace("시", "");
		date_1 = date_1.replace("분", "");
		date_2 = date_2.replace("일", "");
		date_2 = date_2.replace("시", "");
		date_2 = date_2.replace("분", "");
		
		String[] arr_1 = date_1.split(" "); // 3 1 21
		String[] arr_2 = date_2.split(" "); // 1 2 32
		
		int time_1 = Integer.parseInt(arr_1[0]) * 1440 + Integer.parseInt(arr_1[1]) * 60 + Integer.parseInt(arr_1[2]);
		int time_2 = Integer.parseInt(arr_2[0]) * 1440 + Integer.parseInt(arr_2[1]) * 60 + Integer.parseInt(arr_2[2]);
		
		int res = 0;
		
		// 3일 81200
		// 1일 28500
		// 6시간 11200
		// 3시간 5800
		// 1시간 2100
		// 10분 370
		
		if (time_2 - time_1 < 0) {
			int diff_time = time_1 - time_2;
			System.out.println(diff_time);
			// 3일
			int three_Days = diff_time / 4320;
			res += three_Days * 81200;
			diff_time %= 4320;
			// 1일
			int one_Days = diff_time / 1440;
			res += one_Days * 28500;
			diff_time %= 1440; 
			// 6시간
			int six_Hours = diff_time / 360;
			res += six_Hours * 11200;
			diff_time %= 360;
			// 3시간
			int three_Hours = diff_time / 180;
			res += three_Hours * 5800;
			diff_time %= 180;
			// 1시간
			int one_Hours = diff_time / 60;
			res += one_Hours * 2100;
			diff_time %= 60;
			// 10분
			int ten_Minutes = diff_time / 10;
			diff_time %= 10;
			if (diff_time > 0) {
				ten_Minutes += 1;
			}
			res += ten_Minutes * 370;
		}
		
		return res;
	}
	
	private String com_Money(int m) {
		String money = Integer.toString(m);
		String com_Money = String.format("%,d", Integer.parseInt(money));
		String result = com_Money + " 원";
		return result;
	}
}
