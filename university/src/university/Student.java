package university;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import university.Professor.MyAdapter;

public class Student extends JFrame implements ActionListener {
	JLabel label1, label2, label3,label4,label5,label6,label7,label8,label9,label10,label11,label12,label13,label14,label15;
	JTextField text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12,text13;
	JComboBox box1, box2, box3;
	JRadioButton radio1,radio2;
	JButton button1, button2, button3,button4,button5,button6;
	JPanel panel1,panel2,panel3,panel4;
	Font font;
	ButtonGroup group;
	
	
	DefaultTableModel dtm;
	JTable table;
	JScrollPane jsp;
	
	PreparedStatement pstmt;
	ResultSet rs, rs1;
	ConnectDb connect = new ConnectDb();
	
	String realGender = "남자";
	
	String [] std_cate = {"학생","주소","학과","교수","졸업고교","성별"};
	String [] sfield = {"name","addr","dept_code","prof_code","hischool","gender"};
	
	String deptcate = null;String cate = null;
	String deptcode=null;String deptname=null;
	
	String profcate = null;
	String profcode = null; String profname=null;
	int srow = -1;
	
	Student(){
		setTitle("학생관리: "+Login.username);
		setSize(700,650);
		setLayout(null);
		
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(0, 0, 700, 50);
		panel1.setBackground(Color.black);
		label1 = new JLabel("학생 관리");
		label1.setLayout(null);
		label1.setBounds(300, 0, 200, 50);
		label1.setForeground(Color.white);
		font = new Font("돋움", Font.BOLD, 15);
		label1.setFont(font);
		
		panel1.add(label1);
		
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(0,50,685,200);
		panel2.setBorder(new TitledBorder(new LineBorder(Color.gray, 1), "입력"));
		

		label1 = new JLabel("학번");
		label1.setBounds(30,20,50,20);
		label2 = new JLabel("이름");
		label2.setBounds(220,20,50,20);
		label3 = new JLabel("주민번호");
		label3.setBounds(380,20,50,20);
		label4 = new JLabel(" - ");
		label4.setBounds(545,20,50,20);
		label5 = new JLabel("주소");
		label5.setBounds(30,50,50,20);
		label6 = new JLabel("휴대폰");
		label6.setBounds(30,80,50,20);
		label7 = new JLabel(" - ");
		label7.setBounds(165,80,50,20);
		label8 = new JLabel(" - ");
		label8.setBounds(255,80,50,20);
		label9 = new JLabel("전화번호");
		label9.setBounds(360,80,50,20);
		label10 = new JLabel("입학년도");
		label10.setBounds(30,110,50,20);
		label11 = new JLabel("졸업고교");
		label11.setBounds(200,110,50,20);
		label12 = new JLabel("고교졸업년도");
		label12.setBounds(360,110,100,20);
		label13 = new JLabel("학과");
		label13.setBounds(30,140,50,20);
		label14 = new JLabel("지도교수");
		label14.setBounds(200,140,50,20);
		label15 = new JLabel("성별");
		label15.setBounds(360,140,50,20);
		
		
		text1= new JTextField();
		text1.setBounds(90,20,100,20);
		text2= new JTextField();
		text2.setBounds(260,20,100,20);
		text3= new JTextField();
		text3.setBounds(440,20,100,20);
		text4= new JTextField();
		text4.setBounds(560,20,100,20);
		text5= new JTextField();
		text5.setBounds(90,50,570,20);
		text6= new JTextField();
		text6.setBounds(90,80,70,20);
		text7= new JTextField();
		text7.setBounds(180,80,70,20);
		text8= new JTextField();
		text8.setBounds(270,80,70,20);
		text9= new JTextField();
		text9.setBounds(440,80,220,20);
		text10= new JTextField();
		text10.setBounds(90,110,90,20);
		text11= new JTextField();
		text11.setBounds(260,110,80,20);
		text12= new JTextField();
		text12.setBounds(440,110,220,20);
		
		box2 = new JComboBox();
		box2.setBounds(90,140,90,20);
		box2.addItem("학과선택");
		Major();
		box3 = new JComboBox();
		box3.setBounds(260,140,80,20);
		box3.addItem("교수선택");
		Prof();
		
		radio1 = new JRadioButton("남자");
		radio1.setBounds(440,140,50,20);
		radio1.setSelected(true);
		radio2 = new JRadioButton("여자");
		radio2.setBounds(500,140,50,20);
		
		group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);
		
		panel2.add(label1);
		panel2.add(label2);
		panel2.add(label3);
		panel2.add(label4);
		panel2.add(label5);
		panel2.add(label6);
		panel2.add(label7);
		panel2.add(label8);
		panel2.add(label9);
		panel2.add(label10);
		panel2.add(label11);
		panel2.add(label12);
		panel2.add(label13);
		panel2.add(label14);
		panel2.add(label15);
		

		
		panel2.add(text1);
		panel2.add(text2);
		panel2.add(text3);
		panel2.add(text4);
		panel2.add(text5);
		panel2.add(text6);
		panel2.add(text7);
		panel2.add(text8);
		panel2.add(text9);
		panel2.add(text10);
		panel2.add(text11);
		panel2.add(text12);
		panel2.add(box2);
		panel2.add(box3);
		panel2.add(radio1);
		panel2.add(radio2);
		
		panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(0,250, 700, 70);
		panel3.setBackground(Color.pink);
		
		box1 = new JComboBox(std_cate);
		box1.setBounds(30,25,135,20);
		box1.addItem("카테고리선택");
		box1.setSelectedIndex(6);
		
		
		
		
		text13 = new JTextField();
		text13.setBounds(190,25,230,20);
		
		button1 = new JButton("조회");
		button1.setBounds(470,25,70,20);
		button2 = new JButton("전체조회");
		button2.setBounds(560,25,85,20);
		
		panel3.add(box1);
		panel3.add(text13);
		panel3.add(button1);
		panel3.add(button2);
		
		

		panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setBounds(0,540,685,70);
		
		
		button3 = new JButton("등록");
		button3.setBounds(30,10,110,40);
		button4 = new JButton("수정");
		button4.setBounds(200,10,110,40);
		button5 = new JButton("삭제");
		button5.setBounds(370,10,110,40);
		button6 = new JButton("종료");
		button6.setBounds(540,10,110,40);
		
		panel4.add(button3);
		panel4.add(button4);
		panel4.add(button5);
		panel4.add(button6);
		
		DepartTables();
		
		
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(jsp);
		
		box1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				cate = (String) box1.getSelectedItem();
				text13.requestFocus();
			}

		});

		box2.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				deptcate = (String) box2.getSelectedItem();

				try {
					String sqls =  "SELECT * FROM department where department = ?";
					pstmt = connect.conn.prepareStatement(sqls);
					pstmt.setString(1, deptcate);
					
					rs = pstmt.executeQuery();

					while (rs.next()) {
						deptcode = rs.getString(1);
						deptname = rs.getString(2);
					}
				} catch (SQLException a) {
					a.printStackTrace();
				}
			}

		});
		
		box3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				profcate = (String) box3.getSelectedItem();

				try {
					String sqls =  "SELECT * FROM professor where name = ?";
					pstmt = connect.conn.prepareStatement(sqls);
					pstmt.setString(1, profcate);
					
					rs = pstmt.executeQuery();

					while (rs.next()) {
						profcode = rs.getString(1);
						profname = rs.getString(2);
					}
				} catch (SQLException a) {
					a.printStackTrace();
				}
			}

		});
		
		table.addMouseListener(new MyAdapter());
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		radio1.addActionListener(this);
		radio2.addActionListener(this);

		setVisible(true);
	}
	
	void Major() {

		try {
			String sql = "SELECT * FROM department";
			pstmt = connect.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String dept = rs.getString(2);
				box2.addItem(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	void Prof() {

		try {
			String sql = "SELECT * FROM professor";
			pstmt = connect.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String prof = rs.getString(2);
				box3.addItem(prof);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void DepartTables() {
		String[] head = {"학번","이름","주소","주민등록번호","휴대폰","전화번호","입학년도","졸업고교","고교졸업년도","학과","지도교수","성별"};
		String[][] row = {};
		
		dtm = new DefaultTableModel(row, head) {// 삭제,추가가 가능
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable(dtm);
		jsp = new JScrollPane(table);
		table.setRowHeight(20);
		jsp.setBorder(new TitledBorder(new LineBorder(Color.gray, 1), "테이블"));
		jsp.setBounds(0,320,685,220);
	}
	public void getListAll() {
		String dept=null;
		String prof=null;
		dtm.setRowCount(0);// 0번행부터 addRow가능
		try {
			String sql = "SELECT * FROM student";
			pstmt = connect.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String code = rs.getString(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				String juminno = rs.getString(4);
				String mphone = rs.getString(5);
				String phone = rs.getString(6);
				String uni_year = rs.getString(7);
				String hischool = rs.getString(8);
				String end_year = rs.getString(9);
				String dept_code = rs.getString(10);
				String prof_code = rs.getString(11);
				String gender = rs.getString(12);
				
				String sql1 = "SELECT * FROM department where code =?";
				try {
					pstmt = connect.conn.prepareStatement(sql1);
					pstmt.setString(1, dept_code);
					rs1 = pstmt.executeQuery();

					while (rs1.next()) {
						 dept = rs1.getString(2);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				String sql2 = "SELECT * FROM professor where code =?";
				try {
					pstmt = connect.conn.prepareStatement(sql2);
					pstmt.setString(1, prof_code);
					rs1 = pstmt.executeQuery();

					while (rs1.next()) {
						 prof = rs1.getString(2);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				

				Object[] rowData = { code, name, addr, juminno, mphone, phone, uni_year, hischool, end_year, dept,prof,gender };
				dtm.addRow(rowData);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void Search(String s) {
		s = sfield[box1.getSelectedIndex()];
		dtm.setRowCount(0);
		
		String deptcode1 = null; String deptname1 = null;
		String profcode1 = null; String profname1 = null;
		try {
			if(text13.getText().length()==0) {
				JOptionPane.showConfirmDialog(this, "조회할 내용을 입력하세요");
				text13.requestFocus();
				return;
			}
			
			if(s.equals("dept_code")) {
				String sql = "SELECT * FROM department where department =? or code=?";
				pstmt = connect.conn.prepareStatement(sql);
				pstmt.setString(1, text13.getText());
				pstmt.setString(2, text13.getText());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					deptcode1 = rs.getString(1);
				
				}
				
				sql = "SELECT * FROM student where " + s + "='" + deptcode1 + "'";
				pstmt = connect.conn.prepareStatement(sql);
				
			}
			else if(s.equals("prof_code")) {
				String sql = "SELECT * FROM professor where name =? or code=?";
				pstmt = connect.conn.prepareStatement(sql);
				pstmt.setString(2, text13.getText());
				pstmt.setString(1, text13.getText());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					profcode1 = rs.getString(1);
				
				}
				
				sql = "SELECT * FROM student where " + s + "='" + profcode1 + "'";
				pstmt = connect.conn.prepareStatement(sql);
				
			}
			else {
				String sql = "SELECT * FROM student where "+s + "='" + text13.getText() + "'";
				pstmt = connect.conn.prepareStatement(sql); 
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String code = rs.getString(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				String juminno = rs.getString(4);
				String mphone = rs.getString(5);
				String phone = rs.getString(6);
				String uni_year = rs.getString(7);
				String hischool = rs.getString(8);
				String end_year = rs.getString(9);
				String dept_code = rs.getString(10);
				String prof_code = rs.getString(11);
				String gender = rs.getString(12);
				
				String sql1 = "SELECT * FROM department where code =?";
				try {
					pstmt = connect.conn.prepareStatement(sql1);
					pstmt.setString(1, dept_code);
					rs1 = pstmt.executeQuery();

					while (rs1.next()) {
						 deptname1 = rs1.getString(2);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				String sql2 = "SELECT * FROM professor where code =?";
				try {
					pstmt = connect.conn.prepareStatement(sql2);
					pstmt.setString(1, prof_code);
					rs1 = pstmt.executeQuery();

					while (rs1.next()) {
						 profname1 = rs1.getString(2);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				

				Object[] rowData = { code, name, addr, juminno, mphone, phone, uni_year, hischool, end_year, deptname1,profname1,gender };
				dtm.addRow(rowData);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int Regist(String code) {
		int result = 0;

		String sql = "SELECT * FROM student where code='" + text1.getText() + "'";
		try {
			pstmt = connect.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JOptionPane.showMessageDialog(this, "이미 등록된 코드 입니다");
				text1.setEditable(true);
				text1.requestFocus();
				return 0;
			}
			sql = "INSERT INTO student(code, name, addr, juminno, mphone, phone, uni_year, hischool, end_year, dept_code,prof_code,gender) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(1, text1.getText());
			pstmt.setString(2, text2.getText());
			pstmt.setString(3, text5.getText());
			pstmt.setString(4, text3.getText() + '-' + text4.getText());
			pstmt.setString(5, text6.getText() + '-' + text7.getText() + '-' + text8.getText());
			pstmt.setString(6, text9.getText());
			pstmt.setString(7, text10.getText());
			pstmt.setString(8, text11.getText());
			pstmt.setString(9, text12.getText());
			pstmt.setString(10, deptcode);	//학과코드
			pstmt.setString(11, profcode);
			pstmt.setString(12, realGender);
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
		
		
	}
	
	public int Edit(String code) {
		int result = 0;
		String sql = "UPDATE student SET name=?,addr=?,juminno=?,mphone=?,phone=?,uni_year=?,hischool=?,end_year=?,dept_code=?,prof_code=?,gender=? WHERE code=?";

		try {
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(12, text1.getText());
			pstmt.setString(1, text2.getText());
			pstmt.setString(2, text5.getText());
			pstmt.setString(3, text3.getText() + '-' + text4.getText());
			pstmt.setString(4, text6.getText() + '-' + text7.getText() + '-' + text8.getText());
			pstmt.setString(5, text9.getText());
			pstmt.setString(6, text10.getText());
			pstmt.setString(7, text11.getText());
			pstmt.setString(8, text12.getText());
			pstmt.setString(9, deptcode);	//학과코드
			pstmt.setString(10, profcode);
			pstmt.setString(11, realGender);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public int deleteDb(String code) {
		int result = 0; // 기본값 0 지정
		// executeUpdate를 사용해야함 이때 0,1반환
		String sql = "DELETE FROM student WHERE code=?";

		try {
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(1, code);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	

	public void actionPerformed(ActionEvent e) {
		String code = text1.getText();
		if (e.getSource() == button1) {
			Search(cate);
		}
		if (e.getSource() == button2) {
			getListAll();
		}
		if (e.getSource() == button3) {
			if (text1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "학번을 입력하세요");
				return;
			} else if (text2.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "이름을 입력하세요");
				return;
			} else if (text5.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "주소를 입력하세요");
				return;
			} else if ((text3.getText().isEmpty()) || (text4.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "주민번호를 입력하세요");
				return;
			} else if ((text6.getText().isEmpty()) || (text7.getText().isEmpty()) || (text8.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "휴대폰 번호를 입력하세요");
				return;
			} else if ((text9.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "연락처를 입력하세요");
				return;
			} else if ((text10.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "입학년도를 입력하세요");
				return;
			} else if ((text11.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "졸업고교를 입력하세요");
				return;
			} else if ((text12.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "고교졸업년도를 입력하세요");
				return;
			} else if (realGender == null) {
				JOptionPane.showMessageDialog(null, "성별을 입력하세요");
				return;
			} else if (deptcode == null) {
				JOptionPane.showMessageDialog(null, "학과를 입력하세요");
				return;
			} else if (profcode == null) {
				JOptionPane.showMessageDialog(null, "학과를 입력하세요");
				return;
			} else {
				int result = Regist(code);
				if (result != 0) {
					getListAll();
					text1.setText(null);
					text2.setText(null);
					text3.setText(null);
					text4.setText(null);
					text5.setText(null);
					text6.setText(null);
					text7.setText(null);
					text8.setText(null);
					text9.setText(null);
					text10.setText(null);
					text11.setText(null);
					text12.setText(null);
					box1.setSelectedIndex(0);
					box2.setSelectedIndex(0);
					box3.setSelectedIndex(0);
					
				}
			}
		}
		
		if (e.getSource() == button4) {
			if (srow == -1) {
				JOptionPane.showMessageDialog(null, "Empty");
			} else {
				int result = Edit(code); // 성공하면 1 실패하면 0
				if (result != 0) {
					getListAll();
					text1.setText(null);
					text2.setText(null);
					text3.setText(null);
					text4.setText(null);
					text5.setText(null);
					text6.setText(null);
					text7.setText(null);
					text8.setText(null);
					text9.setText(null);
					text10.setText(null);
					text11.setText(null);
					text12.setText(null);
					text1.setEditable(true);
					box1.setSelectedIndex(0);
					box2.setSelectedIndex(0);
					box3.setSelectedIndex(0);
					

				}

			}
		}
		if (e.getSource() == button5) {
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
						text4.setText(null);
						text5.setText(null);
						text6.setText(null);
						text7.setText(null);
						text8.setText(null);
						text9.setText(null);
						text10.setText(null);
						text11.setText(null);
						text12.setText(null);
						int result = deleteDb(abc);
						if (result != 0) {
							getListAll();
							text1.setEditable(true);
							box1.setSelectedIndex(0);
							box2.setSelectedIndex(0);
							box3.setSelectedIndex(0);
							
						}
					} else {
						return;
					}
				}
			}
		}
		if (e.getSource() == button6) {
			dispose();
		}
		if (radio1.isSelected()) {
			realGender = "남자";
		}
		if (radio2.isSelected()) {
			realGender = "여자";
		}
	}
	
	class MyAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			srow = table.getSelectedRow();

			String code = (String) table.getValueAt(srow, 0);
			String name = (String) table.getValueAt(srow, 1);
			String addr = (String) table.getValueAt(srow, 2);
			String juminno = (String) table.getValueAt(srow, 3);
			String mphone = (String) table.getValueAt(srow, 4);
			String phone = (String) table.getValueAt(srow, 5);
			String uni_year = (String) table.getValueAt(srow, 6);
			String hischool = (String) table.getValueAt(srow, 7);
			String end_year = (String) table.getValueAt(srow, 8);
			String dept_code = (String) table.getValueAt(srow, 9);
			String prof_code = (String) table.getValueAt(srow, 10);
			String gender = (String) table.getValueAt(srow, 11);

			text1.setText(code);
			text2.setText(name);
			text3.setText(juminno.substring(0, 6));
			text4.setText(juminno.substring(7, 14));
			text5.setText(addr);
			text6.setText(mphone.substring(0, 3));
			text7.setText(mphone.substring(4, 8));
			text8.setText(mphone.substring(9, 13));
			text9.setText(phone);
			text10.setText(uni_year);
			text11.setText(hischool);
			text12.setText(end_year);
			
			box2.setSelectedItem(dept_code);
			box3.setSelectedItem(prof_code);

			if (gender.equals("남자")) {
				radio1.setSelected(true);
			} else if (gender.equals("여자")) {
				radio2.setSelected(true);
			}
			
			
			text1.setEditable(false);

		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Student();
	}

}
