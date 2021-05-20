package car_kiosk;

import java.awt.Color;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

// import car_kiosk.win.Pay_Finished_win;


public class Pay extends JFrame{
	
	private JFrame frame;
	private JLabel pay_Money;
	
	//

	public Pay() {
		CarDAO carDAO = CarDAO.getInstance();
		CustomerDAO customerDAO = CustomerDAO.getInstance();
		CustomerDTO customerDTO = CustomerDTO.getInstance();
		
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("코드의 美");
		frame.getContentPane().setBackground(new Color(230, 230, 250));
		frame.setBounds(500, 0, 960, 1000);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(12, 20, 930, 928);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		ImageIcon image1;
		image1 = new ImageIcon(".\\Image\\main_image.png");
		Image img = image1.getImage();
		Image changImg = img.getScaledInstance(220, 100, Image.SCALE_SMOOTH);
		ImageIcon changIcon = new ImageIcon(changImg);
		JLabel lblNewLabel_logo = new JLabel(changIcon);
		lblNewLabel_logo.setBounds(679, 10, 239, 106);
		lblNewLabel_logo.setVerticalTextPosition(JLabel.CENTER);
		lblNewLabel_logo.setHorizontalTextPosition(JLabel.RIGHT);
		lblNewLabel_logo.setVisible(true);
		panel.add(lblNewLabel_logo);
		
		JButton btnNewButton = new JButton("현금결제");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane jOptionPane = new JOptionPane();
	            jOptionPane.showMessageDialog(null, btnNewButton.getText()+"가 완료 되었습니다.","" , JOptionPane.INFORMATION_MESSAGE);
	            customerDTO.setPay_Method(btnNewButton.getText());
			}
		});
		btnNewButton.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		btnNewButton.setBounds(33, 158, 257, 171);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorder(new RoundedBorder(60));
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("카드결제");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane jOptionPane = new JOptionPane();
	            jOptionPane.showMessageDialog(null, btnNewButton_1.getText()+"가 완료 되었습니다.","" , JOptionPane.INFORMATION_MESSAGE);
	            customerDTO.setPay_Method(btnNewButton_1.getText());
			}
		});
		btnNewButton_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		btnNewButton_1.setBounds(323, 158, 257, 171);
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorder(new RoundedBorder(60));
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("간편결제");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane jOptionPane = new JOptionPane();
	            jOptionPane.showMessageDialog(null, btnNewButton_2.getText()+"가 완료 되었습니다.","" , JOptionPane.INFORMATION_MESSAGE);
	            customerDTO.setPay_Method(btnNewButton_2.getText());
			}
		});
		btnNewButton_2.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		btnNewButton_2.setBounds(613, 158, 257, 171);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorder(new RoundedBorder(60));
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("결제방법을 선택해주세요");
		lblNewLabel.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		lblNewLabel.setBounds(291, 43, 329, 78);
		panel.add(lblNewLabel);
		
		JPanel Label_Pay = new JPanel();
		Label_Pay.setBackground(new Color(230, 230, 250));
		Label_Pay.setForeground(new Color(255, 255, 255));
		Label_Pay.setBounds(239, 560, 421, 90);
		Label_Pay.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		Label_Pay.setLayout(null);
		panel.add(Label_Pay);
		
		
		JLabel lblNewLabel_1 = new JLabel("승인 요청 금액");
		lblNewLabel_1.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(12, 20, 197, 53);
		Label_Pay.add(lblNewLabel_1);
		
		
		
		pay_Money = new JLabel(); //승인금액 표시
		pay_Money.setFont(new Font("한컴산뜻돋움", Font.BOLD, 30));
		DecimalFormat df = new DecimalFormat("#,###");// 콤마 전환 메서드
		int str =customerDTO.getPay_Money();
		pay_Money.setText(df.format(str)); //DTO에서 승인금액 가져옴
		pay_Money.setHorizontalAlignment(SwingConstants.RIGHT);
		pay_Money.setBounds(221, 20, 175, 59);
		pay_Money.setVisible(true);
		Label_Pay.add(pay_Money);
		
		JButton btnNewButton_3 = new JButton("결재완료");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carDAO.setIsKey(customerDTO.getCar_Num());
				customerDTO.setIsKey(true);
				customerDAO.SetPayMethod(customerDTO);
				System.out.println(customerDTO.toString());
				customerDAO.GetLastRow();
				PayCheck paycheck = new PayCheck();
				frame.dispose();
			}
		});
		btnNewButton_3.setFont(new Font("한컴산뜻돋움", Font.BOLD, 20));
		btnNewButton_3.setBounds(613, 755, 230, 72);
		btnNewButton_3.setContentAreaFilled(false);
		btnNewButton_3.setBorder(new RoundedBorder(60));
		panel.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("뒤로가기");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarSelect carselect = new CarSelect();
				frame.dispose();
			}
		});
		btnNewButton_4.setBounds(29, 25, 178, 62);
		btnNewButton_4.setContentAreaFilled(false);
		btnNewButton_4.setBorder(new RoundedBorder(60));
		panel.add(btnNewButton_4);
		

		frame.setBounds(500, 0, 960, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	
}
