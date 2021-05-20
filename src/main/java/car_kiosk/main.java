package car_kiosk;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class main {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	public MemberDAO memberDAO;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;
	private int pw_check = 0; //회원가입시 비번 검증 체크섬
	//
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		CustomerDAO customerDAO = CustomerDAO.getInstance();
		customerDAO.getConnection();
//		customerDAO.CreateTable("Customer");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
	public main() {
		initialize();
	
		//
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
			
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("코드의 美");
		frame.getContentPane().setBackground(new Color(230, 230, 250));
		frame.setBounds(500, 0, 960, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ImageIcon image1;
		image1 = new ImageIcon(".\\image\\main_image.png");
		Image img = image1.getImage();
		Image changImg = img.getScaledInstance(300, 150, Image.SCALE_SMOOTH);
		ImageIcon changIcon = new ImageIcon(changImg);
		JLabel lblNewLabel = new JLabel(changIcon);
		lblNewLabel.setBounds(324, 56, 300, 150);
		lblNewLabel.setVerticalTextPosition(JLabel.CENTER);
		lblNewLabel.setHorizontalTextPosition(JLabel.RIGHT);
		lblNewLabel.setVisible(true);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel main_panel = new JPanel(); //메인 패널 (회원, 비회원 선택창)
		main_panel.setBackground(new Color(230, 230, 250));
		main_panel.setBounds(79, 208, 795, 726);
		frame.getContentPane().add(main_panel);
		main_panel.setLayout(null);
		
		ImageIcon image2 = new ImageIcon(".\\image\\main_gif.gif");
		JLabel lblNewLabel2 = new JLabel(image2);
		lblNewLabel2.setBounds(158, 44, 479, 381);
		lblNewLabel2.setVerticalTextPosition(JLabel.CENTER);
		lblNewLabel2.setHorizontalTextPosition(JLabel.RIGHT);
		lblNewLabel2.setVisible(true);
		main_panel.add(lblNewLabel2);
		
		JButton btnNewButton = new JButton("회원");
		btnNewButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main_panel.setVisible(false);//비회원 선택시 main페널 비활성 및 비회원 창으로 전환
				login_panel(frame, main_panel);
			}
		});
		btnNewButton.setBounds(17, 453, 357, 170);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorder(new RoundedBorder(60));
		main_panel.add(btnNewButton);
		

		JButton btnNewButton_1 = new JButton("비회원");
		btnNewButton_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main_panel.setVisible(false);//비회원 선택시 main페널 비활성 및 비회원 창으로 전환
				non_Member(frame, main_panel);
				
			}
		});
		btnNewButton_1.setBounds(426, 453, 357, 170);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorder(new RoundedBorder(60));
		main_panel.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("회원가입");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sign_Up(frame, main_panel);
				main_panel.setVisible(false);
			}
		});
		btnNewButton_1_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setBorder(new RoundedBorder(60));
		btnNewButton_1_1.setBounds(224, 633, 347, 71);
		main_panel.add(btnNewButton_1_1);  //메인패널 끝
		
		
		
	}//initialize 끝
	private void sign_Up(JFrame frame, JPanel main_panel) {//회원 가입 패널 임돠....
		JPanel sign_Up_panel = new JPanel(); 
		sign_Up_panel.setBackground(new Color(230, 230, 250));
		sign_Up_panel.setBounds(79, 208, 795, 726);
		frame.getContentPane().add(sign_Up_panel);
		sign_Up_panel.setLayout(null);
		
		ImageIcon image2 = new ImageIcon(".\\image\\main_gif.gif");
		JLabel lblNewLabel2 = new JLabel(image2);
		lblNewLabel2.setBounds(148, 10, 499, 308);
		lblNewLabel2.setVerticalTextPosition(JLabel.CENTER);
		lblNewLabel2.setHorizontalTextPosition(JLabel.RIGHT);
		lblNewLabel2.setVisible(true);
		sign_Up_panel.add(lblNewLabel2);
		
		JPanel info_panel = new JPanel();
		info_panel.setBounds(69, 345, 520, 269);
		sign_Up_panel.add(info_panel);
		info_panel.setLayout(new GridLayout(6, 2, 5, 5));
		
		JLabel label = new JLabel("아이디 ");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		info_panel.add(label);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		info_panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("비밀번호");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		info_panel.add(lblNewLabel_1);
		
		passwordField_1 = new JPasswordField();
		info_panel.add(passwordField_1);
		
		JLabel lblNewLabel_2 = new JLabel("비밀번호확인");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		info_panel.add(lblNewLabel_2);
		
		passwordField_2 = new JPasswordField();
		info_panel.add(passwordField_2);
		
		JLabel lblNewLabel_3 = new JLabel("면허번호");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		info_panel.add(lblNewLabel_3);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		info_panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("발급일자");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		info_panel.add(lblNewLabel_4);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		info_panel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("전화번호");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		info_panel.add(lblNewLabel_5);
		
		textField_6 = new JTextField();
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		info_panel.add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnPw = new JButton("PW검증");
		btnPw.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		btnPw.setBorder(new RoundedBorder(60));
		btnPw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pw = ""; //패스워드 String 변수
				String pw2 = ""; //패스워드확인 String 변수
				char[] secret_pw = passwordField_1.getPassword();
				char[] secret_pw2 = passwordField_2.getPassword();
				for(char cha : secret_pw){ //char 패스워드 String으로 for문으로 가져오기
					Character.toString(cha); 
					pw += (pw.equals("")) ? ""+cha+"" : ""+cha+"";
				}
				for(char cha : secret_pw2){ //char 패스워드 String으로 for문으로 가져오기
					Character.toString(cha); 
					pw2 += (pw.equals("")) ? ""+cha+"" : ""+cha+"";
				}
				if(pw.equals(pw2)) {
					JOptionPane jOptionPane = new JOptionPane();
		            jOptionPane.showMessageDialog(null, "비밀번호가 일치합니다.","" , JOptionPane.INFORMATION_MESSAGE);
		            pw_check = 2;
		            
				}else {
					JOptionPane jOptionPane = new JOptionPane();
		            jOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.","" , JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnPw.setBackground(new Color(230, 230, 250));
		btnPw.setBounds(601, 431, 182, 53);
		sign_Up_panel.add(btnPw);
		
		JButton btnPw_1 = new JButton("가입하기");
		btnPw_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(pw_check==0) {
					JOptionPane jOptionPane = new JOptionPane();
		            jOptionPane.showMessageDialog(null, "ID 검증하기 버튼을 눌러주세용~~","" , JOptionPane.INFORMATION_MESSAGE);
				}else if(pw_check==1) {
					JOptionPane jOptionPane = new JOptionPane();
		            jOptionPane.showMessageDialog(null, "PW 검증하기 버튼을 눌러주세용~~","" , JOptionPane.INFORMATION_MESSAGE);
				}else {
					if(textField_1.getText().length()!=0 && passwordField_2.getPassword().length !=0 && textField_4.getText().length()!=0
							&& textField_5.getText().length()!=0 && textField_6.getText().length()!=0) {//빈칸이 여부 검증 조건문
					String pw = ""; //패스워드 String 변수
					char[] secret_pw = passwordField_1.getPassword();
					for(char cha : secret_pw){ //char 패스워드 String으로 for문으로 가져오기
						Character.toString(cha); 
						pw += (pw.equals("")) ? ""+cha+"" : ""+cha+"";
					}
					MemberDTO memberDTO = MemberDTO.getInstance();
					MemberDAO memberDAO = MemberDAO.getInstance();
					memberDTO.setMember_Id(textField_1.getText());
					memberDTO.setMember_Pw(pw);
					memberDTO.setLi_num(textField_4.getText());
					memberDTO.setLi_Date(textField_5.getText());
					memberDTO.setPh_Num(textField_6.getText());	
					memberDAO.member_Add();
					memberDTO.toString();//추가된 정보 확인.
					
					memberDTO.setMember_Id("");
					memberDTO.setMember_Pw("");
					memberDTO.setLi_num("");
					memberDTO.setLi_Date("");
					memberDTO.setPh_Num("");
					
					sign_Up_panel.setVisible(false);
					main_panel.setVisible(true);
					}else {
						JOptionPane jOptionPane = new JOptionPane();
			            jOptionPane.showMessageDialog(null, "빈칸을 입력해주세요.","" , JOptionPane.INFORMATION_MESSAGE); 
					}
				}
			}
		});
		btnPw_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		btnPw_1.setBorder(new RoundedBorder(60));
		btnPw_1.setBackground(new Color(230, 230, 250));
		btnPw_1.setBounds(601, 636, 182, 53);
		sign_Up_panel.add(btnPw_1);
		
		JButton btnPw_1_1 = new JButton("뒤로");
		btnPw_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sign_Up_panel.setVisible(false);
				main_panel.setVisible(true);
			}
		});
		btnPw_1_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		btnPw_1_1.setBorder(new RoundedBorder(60));
		btnPw_1_1.setBackground(new Color(230, 230, 250));
		btnPw_1_1.setBounds(407, 636, 182, 53);
		sign_Up_panel.add(btnPw_1_1);
		
		JButton btn_id_check = new JButton("ID검증");
		btn_id_check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDTO memberDTO = MemberDTO.getInstance();
				MemberDAO memberDAO = MemberDAO.getInstance();
				memberDTO.setMember_Id(textField_1.getText());
				if(memberDAO.member_Id_Check(memberDTO.getMember_Id())) {
					JOptionPane jOptionPane = new JOptionPane();
		            jOptionPane.showMessageDialog(null, "사용할 수 있는 아이디 입니다.","" , JOptionPane.INFORMATION_MESSAGE);
		            pw_check = 1;
				}else {
					JOptionPane jOptionPane = new JOptionPane();
		            jOptionPane.showMessageDialog(null, "사용할 수 없는 아이디 입니다.\n 다른 아이디를 입력해주세요.","" , JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_id_check.setFont(new Font("한컴산뜻돋움", Font.BOLD, 15));
		btn_id_check.setBorder(new RoundedBorder(60));
		btn_id_check.setBackground(new Color(230, 230, 250));
		btn_id_check.setBounds(601, 345, 182, 53);
		sign_Up_panel.add(btn_id_check);
		
	}
	
	private void login_Check(JPanel member_panel, String id, String pw) {//로그인 버튼 처리 메서드
		ArrayList<String> login_info = memberDAO.MemberMatch(id, pw); // DB접속하여 해당 login ID 정보 검색
		if(login_info.size() == 0) {
			JOptionPane jOptionPane = new JOptionPane();
            jOptionPane.showMessageDialog(null, "등록되지 않은 아이디 입니다.","" , JOptionPane.INFORMATION_MESSAGE);
		}else if(login_info.size() == 1) {
			JOptionPane jOptionPane = new JOptionPane();
            jOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.","" , JOptionPane.INFORMATION_MESSAGE);
		}else {
			System.out.println("=====정상 로그인=====");
			System.out.println("login_info"+login_info.toString());
			textField.setText("");// 아이디 비번 필드 초기화
			passwordField.setText("");
			
			CustomerDTO customerDTO = CustomerDTO.getInstance();
			CustomerDAO customerDAO = CustomerDAO.getInstance();
			customerDTO.setAgree(true);//임의로 넣음 동의 값 -> 바꿔주세여
			customerDTO.setLi_num(login_info.get(2));
			customerDTO.setLi_Date(login_info.get(3));
			customerDTO.setPh_Num(login_info.get(4));
			customerDAO.SetAgreement(customerDTO);
			
			CarSelect carSelect = new CarSelect();
			frame.dispose();
			
			
			
		}
	}
	
	private void login_panel(JFrame frame, JPanel main_panel) {//회원 클릭시 활성화 메서드
		
		JPanel member_panel = new JPanel(); //회원인 경우 패널 (회원, 비회원 선택창)
		member_panel.setBackground(new Color(230, 230, 250));
		member_panel.setBounds(79, 208, 795, 726);
		frame.getContentPane().add(member_panel);
		member_panel.setLayout(null);
		
		ImageIcon image2 = new ImageIcon(".\\image\\main_gif.gif");
		JLabel lblNewLabel2 = new JLabel(image2);
		lblNewLabel2.setBounds(158, 44, 479, 381);
		lblNewLabel2.setVerticalTextPosition(JLabel.CENTER);
		lblNewLabel2.setHorizontalTextPosition(JLabel.RIGHT);
		lblNewLabel2.setVisible(true);
		member_panel.add(lblNewLabel2);
		
		JLabel label_id = new JLabel("아이디");
		label_id.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		label_id.setHorizontalAlignment(SwingConstants.CENTER);
		label_id.setBounds(102, 532, 131, 53);
		member_panel.add(label_id);
		
		JLabel label_pw = new JLabel("비밀번호");
		label_pw.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		label_pw.setHorizontalAlignment(SwingConstants.CENTER);
		label_pw.setBounds(102, 598, 131, 53);
		member_panel.add(label_pw);
		
		textField = new JTextField(); //아이디 텍스트 필드
		textField.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(293, 532, 215, 53);
		member_panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField(); //패스워드 필드
		passwordField.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(293, 598, 215, 53);
		member_panel.add(passwordField);
		
		JButton button_login = new JButton("로그인");
		button_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MemberDAO member_DAO = MemberDAO.getInstance();
				memberDAO = member_DAO;
				
				String pw = ""; //패스워드 String 변수
				char[] secret_pw = passwordField.getPassword();
				for(char cha : secret_pw){ //char 패스워드 String으로 for문으로 가져오기
					Character.toString(cha); 
					pw += (pw.equals("")) ? ""+cha+"" : ""+cha+"";
				}
				login_Check(member_panel,textField.getText(), pw); //로그인 DB체크 메서드

			}
		});
		button_login.setBackground(new Color(230, 230, 250));
		button_login.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		button_login.setBounds(541, 598, 190, 53);
		button_login.setBorder(new RoundedBorder(60));
		member_panel.add(button_login);
		
		JButton button_login_1 = new JButton("뒤로");
		button_login_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		button_login_1.setBorder(new RoundedBorder(60));
		button_login_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				member_panel.setVisible(false);
				main_panel.setVisible(true);
			}
		});
		button_login_1.setBackground(new Color(230, 230, 250));
		button_login_1.setBounds(541, 661, 190, 53);
		member_panel.add(button_login_1);
		
		JButton button_login_2 = new JButton("반납");
		button_login_2.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		button_login_2.setBorder(new RoundedBorder(60));
		button_login_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Return_Car return_car = new Return_Car();
				frame.dispose();
			}
		});
		button_login_2.setBackground(new Color(230, 230, 250));
		button_login_2.setBounds(541, 530, 190, 53);
		member_panel.add(button_login_2);
		
	}
	
	private void non_Member(JFrame frame, JPanel main_panel) {//비회원 클릭시 활성화 창
		
		JPanel non_member_panel = new JPanel(); //비회원인 경우 패널 (회원, 비회원 선택창)
		non_member_panel.setBackground(new Color(230, 230, 250));
		non_member_panel.setBounds(79, 208, 795, 726);
		frame.getContentPane().add(non_member_panel);
		non_member_panel.setLayout(null);
		
		ImageIcon image2 = new ImageIcon(".\\image\\main_gif.gif");//메인 이미지
		JLabel lblNewLabel2 = new JLabel(image2);
		lblNewLabel2.setBounds(158, 44, 479, 381);
		lblNewLabel2.setVerticalTextPosition(JLabel.CENTER);
		lblNewLabel2.setHorizontalTextPosition(JLabel.RIGHT);
		lblNewLabel2.setVisible(true);
		non_member_panel.add(lblNewLabel2);
		
		JButton btnNewButton = new JButton("\uBC18\uB0A9");
		btnNewButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Return_Car return_car = new Return_Car();
				frame.dispose();
			}
		});
		btnNewButton.setBounds(426, 453, 357, 170);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorder(new RoundedBorder(60));
		non_member_panel.add(btnNewButton);
		

		JButton btnNewButton_1 = new JButton("\uB300\uC5EC");
		btnNewButton_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Agreement agree = new Agreement();
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(17, 453, 357, 170);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorder(new RoundedBorder(60));
		non_member_panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("뒤로");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				non_member_panel.setVisible(false);
				main_panel.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorder(new RoundedBorder(60));
		btnNewButton_2.setBounds(574, 630, 192, 78);
		non_member_panel.add(btnNewButton_2);
	}
}//클래스


