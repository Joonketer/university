package university;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class Login extends JFrame implements ActionListener{
	JPanel panel1, panel2, panel3;
	JLabel label1, label2, label3, label4;
	JTextField text1, text2, text3;
	JButton button1;
	String userid, userpassword;
	static String username;
	
	PreparedStatement pstmt;
	ConnectDb connect = new ConnectDb();
	ResultSet rs;
	
	
	Login() {
		setTitle("로그인");
		setSize(630, 250);
		setLayout(null);
		

		// 패널
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();

		panel1.setLayout(null);
		panel2.setLayout(null);
		panel3.setLayout(null);

		panel1.setBounds(0, 0, 700, 40);
		panel1.setBackground(Color.black);
		panel2.setBounds(0, 70, 700, 40);
		panel3.setBounds(0, 130, 700, 40);
		panel3.setBackground(Color.gray);

		// 패널1
		label1 = new JLabel("로그인");
		label1.setBounds(285, 0, 100, 40);
		label1.setForeground(Color.white);
		

		// 패널2
		label2 = new JLabel("아이디");
		label3 = new JLabel("패스워드");
		label4 = new JLabel("고유코드");

		text1 = new JTextField();
		text2 = new JTextField();
		text3 = new JTextField();
		text3.setEditable(false);

		label2.setBounds(40, 10, 150, 20);
		label3.setBounds(215, 10, 150, 20);
		label4.setBounds(400, 10, 150, 20);

		text1.setBounds(90, 10, 100, 20);
		text2.setBounds(275, 10, 100, 20);
		text3.setBounds(475, 10, 100, 20);

		// 패널3
		button1 = new JButton("로그인");
		button1.setBounds(260, 5, 90, 30);

		panel1.add(label1);

		panel2.add(label2);
		panel2.add(label3);
		panel2.add(label4);

		panel2.add(text1);
		panel2.add(text2);
		panel2.add(text3);

		panel3.add(button1);

		this.add(panel1);
		this.add(panel2);
		this.add(panel3);

		button1.addActionListener(this);
		
		setVisible(true);

	}
	
	public void LoginDB() {
		
		String sql = "SELECT * FROM admin WHERE id=? and password = ?";
		try {

			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(1, text1.getText());
			pstmt.setString(2, text2.getText());
			rs = pstmt.executeQuery(); // 값 불러오기, 레코드단위로 반환

			while (rs.next()) {
				userid = rs.getString(1);
				userpassword = rs.getString(2);
				username = rs.getString(3);
			}

			if ((text1.getText().equals(userid)) && (text2.getText().equals(userpassword))) {
				text3.setText(username);
				JOptionPane.showMessageDialog(this, username + "님 반갑습니다.", "조회완료", JOptionPane.INFORMATION_MESSAGE);
				dispose();
				new Unimenu();
				
			} else {
				JOptionPane.showMessageDialog(this, "아이디와 패스워드가 맞지 않습니다.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			LoginDB();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}

}
