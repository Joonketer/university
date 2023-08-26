package university;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import university.Department.MyAdapter;

public class Admin extends JFrame implements ActionListener{
	JPanel panel1,panel2,panel4;
	JLabel label1, label2, label3, label4, label5, label6, label7;
	Font font;
	JTextField text1,text2,text3,text4,text5,text6;
	JButton button1, button2, but1, but2, but3, but4;
	
	
	DefaultTableModel dtm;
	JTable table;
	JScrollPane jsp;
	
	PreparedStatement pstmt;
	ConnectDb connect = new ConnectDb();
	ResultSet rs;
	int srow = -1;
	
	boolean idtrue = false ,passwordtrue= false;
	
	Admin() {
		setTitle("사용자 등록: "+Login.username);
		setSize(500, 650);
		setLayout(null);

		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(0, 0, 500, 50);
		panel1.setBackground(Color.black);
		label1 = new JLabel("사용자관리");
		label1.setLayout(null);
		label1.setBounds(200, 0, 100, 50);
		label1.setForeground(Color.white);
		font = new Font("돋움", Font.BOLD, 15);
		label1.setFont(font);
		
		panel1.add(label1);
		
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(0,60,485,200);
		panel2.setBorder(new TitledBorder(new LineBorder(Color.gray, 1), "입력"));
		
		label2 = new JLabel("아이디");
		label2.setBounds(25, 30, 70, 30);
		label3 = new JLabel("패스워드");
		label3.setBounds(25, 70, 70, 30);
		label4 = new JLabel("패스워드확인");
		label4.setBounds(195, 70, 100, 30);
		label5 = new JLabel("사용자");
		label5.setBounds(25, 110, 70, 30);
		label6 = new JLabel("사용권한");
		label6.setBounds(195, 110, 70, 30);
		label7 = new JLabel("등록날짜");
		label7.setBounds(25, 150, 70, 30);
		
		text1 = new JTextField();
		text1.setBounds(90, 35, 90, 20);
		text2 = new JTextField();
		text2.setBounds(90, 75, 90, 20);
		text3= new JTextField();
		text3.setBounds(275, 75, 90, 20);
		text4 = new JTextField();
		text4.setBounds(90,115,90,20);
		text5 = new JTextField();
		text5.setBounds(275,115,90,20);
		text6 = new JTextField();
		text6.setBounds(90,155,90,20);
		
		button1 = new JButton("ID중복확인");
		button1.setBounds(195,35,100,20);
		button2 = new JButton("PW중복확인");
		button2.setBounds(372,75,105,20);
		
		panel2.add(button1);
		panel2.add(button2);
		
		
		panel2.add(label2);
		panel2.add(label3);
		panel2.add(label4);
		panel2.add(label5);
		panel2.add(label6);
		panel2.add(label7);
		
		panel2.add(text1);
		panel2.add(text2);
		panel2.add(text3);
		panel2.add(text4);
		panel2.add(text5);
		panel2.add(text6);
		

		DepartTables();
		
		
		panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setBounds(0,530,500,80);
		
		but1 = new JButton("등록");
		but1.setBounds(5,20,110,40);
		but2 = new JButton("수정");
		but2.setBounds(125,20,110,40);
		but3 = new JButton("삭제");
		but3.setBounds(245,20,110,40);
		but4 = new JButton("종료");
		but4.setBounds(365,20,110,40);
		
		panel4.add(but1);
		panel4.add(but2);
		panel4.add(but3);
		panel4.add(but4);
		
		add(jsp);
		add(panel1);
		add(panel2);
		add(panel4);
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		but1.addActionListener(this);
		but2.addActionListener(this);
		but3.addActionListener(this);
		but4.addActionListener(this);
		
		getListAll();
		table.addMouseListener(new MyAdapter());
		
		setVisible(true);
		
	}
	
	void DepartTables() {
		String[] head = {"아이디","패스워드","사용자","사용권한","등록날짜"};
		String[][] row = {};
		
		dtm = new DefaultTableModel(row, head) {// 삭제,추가가 가능
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable(dtm);
		jsp = new JScrollPane(table);
		table.setRowHeight(20);
		jsp.setBounds(0,320,485,220);
	}
	
	public void getListAll() {
		dtm.setRowCount(0);// 0번행부터 addRow가능
		try {
			String sql = "SELECT * FROM admin";
			pstmt = connect.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				String password = rs.getString(2);
				String owner = rs.getString(3);
				String auth = rs.getString(4);
				String date = rs.getString(5);

				Object[] rowData = { id, password, owner, auth, date};
				dtm.addRow(rowData);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int Regist(String id, String password, String owner, String auth, String date) {

		int result = 0;// 등록성공여부를 확인하려고 성공하면 1 실패하면 0

		String sql = "SELECT * FROM admin where id='"+text1.getText()+"'";
		try {
			
			pstmt = connect.conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				JOptionPane.showMessageDialog(this, "이미 등록된 id 입니다");
				text1.setEditable(true);
				text1.requestFocus();
				text1.selectAll();
				return 0;
			}
			sql = "INSERT INTO admin(id, password, owner, auth, date) VALUES(?,?,?,?,?)";
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, owner);
			pstmt.setString(4, auth);
			pstmt.setString(5, date);
			

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int Edit(String id, String password, String owner, String auth, String date) {
		int result = 0;
		String sql = "UPDATE admin SET password=?, owner=?, auth=?, date=? WHERE id=?";

		try {
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(5, id);
			pstmt.setString(1, password);
			pstmt.setString(2, owner);
			pstmt.setString(3, auth);
			pstmt.setString(4, date);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public int deleteDb(String id) {
		int result = 0; // 기본값 0 지정
		// executeUpdate를 사용해야함 이때 0,1반환
		String sql = "DELETE FROM admin WHERE id=?";

		try {
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public void actionPerformed(ActionEvent e) {
		String id = text1.getText();
		String password = text2.getText();
		String owner = text4.getText();
		String auth = text5.getText();
		String date = text6.getText();

		if (e.getSource() == button1) {
			String sql = "SELECT * FROM admin where id='"+text1.getText()+"'";
			try {
				pstmt = connect.conn.prepareStatement(sql);
				rs= pstmt.executeQuery();
				while(rs.next()) {
					JOptionPane.showMessageDialog(this, "이미 등록된 id 입니다");
					text1.setEditable(true);
					text1.requestFocus();
					text1.selectAll();
					idtrue=false;
					return ;
				}
				JOptionPane.showMessageDialog(this, "사용가능한 id 입니다");
				idtrue = true;
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		if (e.getSource() == button2) {
			if(password.equals(text3.getText().toString())) {
				JOptionPane.showMessageDialog(this, "비밀번호가 일치합니다");
				
				passwordtrue = true;
			}
			else {
				JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다");
				text2.requestFocus();
				text2.selectAll();
				
				passwordtrue = false;
			}
		}

		if (e.getSource() == but1) {
			if((idtrue==true)&&(passwordtrue==true)) {
			Regist(id,password,owner,auth,date);
			getListAll();
			text1.setText(null);
			text2.setText(null);
			text3.setText(null);
			text4.setText(null);
			text5.setText(null);
			text6.setText(null);
			}
			else {
				JOptionPane.showMessageDialog(this, "아이디/비밀번호를 확인하세요");
			}
		}

		if (e.getSource() == but2) {
			Edit(id,password,owner,auth,date);
			getListAll();
			text1.setText(null);
			text2.setText(null);
			text3.setText(null);
			text4.setText(null);
			text5.setText(null);
			text6.setText(null);
			text1.setEditable(true);
		}

		if (e.getSource() == but3) {
			if (srow == -1) {
				JOptionPane.showMessageDialog(this, "행을 선택하세요");
			} else {
				String abc = (String) dtm.getValueAt(srow, 0);
				if (text1.getText().equals(abc)) {
					int i = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE);

					if (i == JOptionPane.YES_OPTION) {
						text1.setText(null);
						text2.setText(null);
						text3.setText(null);
						int result = deleteDb(abc);
						if (result != 0) {
							getListAll();
							text1.setText(null);
							text2.setText(null);
							text3.setText(null);
							text4.setText(null);
							text5.setText(null);
							text6.setText(null);
							text1.setEditable(true);
						}
					} else {
						return;
					}
				}
			}
		}

		if (e.getSource() == but4) {
			dispose();
		}
	}
	
	class MyAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			srow = table.getSelectedRow();

			String id = (String) table.getValueAt(srow, 0);
			String password = (String) table.getValueAt(srow, 1);
			String owner = (String) table.getValueAt(srow, 2);
			String auth = (String) table.getValueAt(srow, 3);
			String date = (String) table.getValueAt(srow, 4);

			text1.setText(id);
			text2.setText(password);
			text3.setText(password);
			text4.setText(owner);
			text5.setText(auth);
			text6.setText(date);

			text1.setEditable(false);

		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Admin();
	}

}
