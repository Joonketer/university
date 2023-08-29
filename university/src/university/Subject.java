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

import university.Student.MyAdapter;

public class Subject extends JFrame implements ActionListener {
	JLabel label1, label2, label3,label4,label5,label6,label7,label8,label9;
	JTextField text1,text2,text3,text4,text5;
	JComboBox box1, box2, box3, box4, box5, box6;
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
	
	String deptcate = null;String cate = null;
	String deptcode=null;String deptname=null;
	
	String profcate = null;
	String profcode = null; String profname=null;
	int srow = -1;
	
	String year1 = null; String term1 = null; String grade1 = null;
	
	String [] sub_cate = {"교과코드","교과명","개설학과","담당교수","개설년도"};
	String [] sfield = {"code","subject","dept_code","prof_code","year"};
	
	Subject(){
		setTitle("교과목 관리: "+Login.username);
		setSize(700,650);
		setLayout(null);
		
		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(0, 0, 700, 50);
		panel1.setBackground(Color.black);
		label1 = new JLabel("교과목관리");
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
		
		label1 = new JLabel("교과목코드");
		label1.setBounds(30,20,70,20);
		label2 = new JLabel("개설강좌명");
		label2.setBounds(230,20,70,20);
		label3 = new JLabel("개설년도");
		label3.setBounds(460,20,70,20);
		label4 = new JLabel("개설학과");
		label4.setBounds(30,60,70,20);
		label5 = new JLabel("개설학년");
		label5.setBounds(230,60,70,20);
		label6 = new JLabel("개설학기");
		label6.setBounds(460,60,70,20);
		label7 = new JLabel("수업시수");
		label7.setBounds(30,100,70,20);
		label8 = new JLabel("담당교수");
		label8.setBounds(230,100,70,20);
		label9 = new JLabel("개설학점");
		label9.setBounds(460,100,70,20);
		
		
		text1= new JTextField();
		text1.setBounds(110,20,100,20);
		text2= new JTextField();
		text2.setBounds(310,20,100,20);
		text3= new JTextField();
		text3.setBounds(110,100,100,20);
		text4= new JTextField();
		text4.setBounds(540,100,100,20);
	
		
		box2 = new JComboBox();
		box2.setBounds(540,20,100,20);
		
		box2.addItem("2021");
		box2.addItem("2022");
		box2.addItem("2023");
		box2.addItem("2024");
		box2.addItem("2025");
		box2.addItem("2026");
		box2.addItem("2027");
		box2.addItem("2028");
		box2.addItem("2029");
		box2.addItem("2030");
		
		box3 = new JComboBox();
		box3.setBounds(110,60,100,20);
		box3.addItem("학과선택");
		Major();
		
		box3.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				deptcate = (String) box3.getSelectedItem();

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
		
		box4 = new JComboBox();
		box4.setBounds(310,60,100,20);
		box4.addItem("학년");
		box4.addItem("1학년");
		box4.addItem("2학년");
		box4.addItem("3학년");
		box4.addItem("4학년");
		
		box5 = new JComboBox();
		box5.setBounds(540,60,100,20);
		box5.addItem("학기");
		box5.addItem("1학기");
		box5.addItem("2학기");
		box5.addItem("여름학기");
		box5.addItem("겨울학기");
		
		box6 = new JComboBox();
		box6.setBounds(310,100,100,20);
		box6.addItem("담당교수");
		Prof();
		
		box6.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				profcate = (String) box6.getSelectedItem();

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
		
		
		
		
		panel2.add(label1);
		panel2.add(label2);
		panel2.add(label3);
		panel2.add(label4);
		panel2.add(label5);
		panel2.add(label6);
		panel2.add(label7);
		panel2.add(label8);
		panel2.add(label9);
	

		
		panel2.add(text1);
		panel2.add(text2);
		panel2.add(text3);
		panel2.add(text4);
	
		panel2.add(box2);
		panel2.add(box3);
		panel2.add(box4);
		panel2.add(box5);
		panel2.add(box6);
		
		
		panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(0,250, 700, 70);
		panel3.setBackground(Color.pink);
		
		box1 = new JComboBox(sub_cate);
		box1.setBounds(30,25,135,20);
		box1.addItem("카테고리 선택");
		box1.setSelectedIndex(5);
		
		box1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				cate = (String) box1.getSelectedItem();
				text5.requestFocus();
			}

		});
		
		
		text5 = new JTextField();
		text5.setBounds(190,25,230,20);
		
		button1 = new JButton("조회");
		button1.setBounds(470,25,70,20);
		button2 = new JButton("전체조회");
		button2.setBounds(560,25,85,20);
		
		panel3.add(box1);
		panel3.add(text5);
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
		
		SubjectTables();
		
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(jsp);
		
		getListAll();
		table.addMouseListener(new MyAdapter());
		
		box2.addActionListener(this);
		box4.addActionListener(this);
		box5.addActionListener(this);
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button5.addActionListener(this);
		button6.addActionListener(this);
		
		setVisible(true);
	}
	void Major() {

		try {
			String sql = "SELECT * FROM department";
			pstmt = connect.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String dept = rs.getString(2);
				box3.addItem(dept);
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
				box6.addItem(prof);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getListAll() {
		String dept=null;
		String prof=null;
		dtm.setRowCount(0);// 0번행부터 addRow가능
		try {
			String sql = "SELECT * FROM subject";
			pstmt = connect.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String code = rs.getString(1);
				String subject = rs.getString(2);
				String dept_code = rs.getString(3);
				String year = rs.getString(4);
				String grade = rs.getString(5);
				String term = rs.getString(6);
				String time = rs.getString(7);
				String prof_code = rs.getString(8);
				String credit = rs.getString(9);
				
				
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
				

				Object[] rowData = { code, subject, dept, year, grade, term, time, prof, credit };
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
			if(text5.getText().length()==0) {
				JOptionPane.showConfirmDialog(this, "조회할 내용을 입력하세요");
				text5.requestFocus();
				return;
			}
			
			if(s.equals("dept_code")) {
				String sql = "SELECT * FROM department where department =? or code=?";
				pstmt = connect.conn.prepareStatement(sql);
				pstmt.setString(1, text5.getText());
				pstmt.setString(2, text5.getText());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					deptcode1 = rs.getString(1);
				
				}
				
				sql = "SELECT * FROM subject where " + s + "='" + deptcode1 + "'";
				pstmt = connect.conn.prepareStatement(sql);
				
			}
			else if(s.equals("prof_code")) {
				String sql = "SELECT * FROM professor where name =? or code=?";
				pstmt = connect.conn.prepareStatement(sql);
				pstmt.setString(2, text5.getText());
				pstmt.setString(1, text5.getText());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					profcode1 = rs.getString(1);
				
				}
				
				sql = "SELECT * FROM subject where " + s + "='" + profcode1 + "'";
				pstmt = connect.conn.prepareStatement(sql);
				
			}
			else {
				String sql = "SELECT * FROM subject where "+s + "='" + text5.getText() + "'";
				pstmt = connect.conn.prepareStatement(sql); 
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String code = rs.getString(1);
				String subject = rs.getString(2);
				String dept_code = rs.getString(3);
				String year = rs.getString(4);
				String grade = rs.getString(5);
				String term = rs.getString(6);
				String time = rs.getString(7);
				String prof_code = rs.getString(8);
				String credit = rs.getString(9);
				
				
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
				

				Object[] rowData = { code, subject, deptname1, year, grade, term, time, profname1, credit };
				dtm.addRow(rowData);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int Regist(String code) {
		int result = 0;

		String sql = "SELECT * FROM subject where code='" + text1.getText() + "'";
		try {
			pstmt = connect.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JOptionPane.showMessageDialog(this, "이미 등록된 코드 입니다");
				text1.setEditable(true);
				text1.requestFocus();
				return 0;
			}
			sql = "INSERT INTO subject(code, subject, dept_code, year, grade, term, time, prof_code, credit) VALUES(?,?,?,?,?,?,?,?,?)";
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(1, text1.getText());
			pstmt.setString(2, text2.getText());
			pstmt.setString(3, deptcode);
			pstmt.setString(4, year1);
			pstmt.setString(5, grade1);
			pstmt.setString(6, term1);
			pstmt.setString(7, text3.getText());
			pstmt.setString(8, profcode);
			pstmt.setString(9, text4.getText());
			
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
		
		
	}
	
	public int Edit(String code) {
		int result = 0;
		String sql = "UPDATE subject SET subject=?,dept_code=?,year=?,grade=?,term=?,time=?,prof_code=?,credit=? where code =?";

		try {
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(1, text2.getText());
			pstmt.setString(2, deptcode);
			pstmt.setString(3, year1);
			pstmt.setString(4, grade1);
			pstmt.setString(5, term1);
			pstmt.setString(6, text3.getText());
			pstmt.setString(7, profcode);
			pstmt.setString(8, text4.getText());
			pstmt.setString(9, text1.getText());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	
	class MyAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			srow = table.getSelectedRow();

			String code = (String) table.getValueAt(srow, 0);
			String subject = (String) table.getValueAt(srow, 1);
			String dept_code = (String) table.getValueAt(srow, 2);
			String year = (String) table.getValueAt(srow, 3);
			String grade = (String) table.getValueAt(srow, 4);
			String term = (String) table.getValueAt(srow, 5);
			String time = (String) table.getValueAt(srow, 6);
			String prof_code = (String) table.getValueAt(srow, 7);
			String credit = (String) table.getValueAt(srow, 8);
			

			text1.setText(code);
			text2.setText(subject);
			box2.setSelectedItem(year);
			box3.setSelectedItem(dept_code);
			box4.setSelectedItem(grade);
			box5.setSelectedItem(term);
			box6.setSelectedItem(prof_code);
			text3.setText(time);
			text4.setText(credit);

			
			text1.setEditable(false);

		}
	}
	public int deleteDb(String code) {
		int result = 0; // 기본값 0 지정
		// executeUpdate를 사용해야함 이때 0,1반환
		String sql = "DELETE FROM subject WHERE code=?";

		try {
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(1, code);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	void SubjectTables() {
		String[] head = {"교과코드","교과명","개설학과","개설년도","개설학년","개설학기","수업시수","담당교수","학점"};
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
				JOptionPane.showMessageDialog(null, "교과목코드를 입력하세요");
				return;
			} else if (text2.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "강좌명을 입력하세요");
				return;
			} else if (text3.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "수업시수를 입력하세요");
				return;
			} else if ((text4.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "개설학점을 입력하세요");
				return;
			} else if (year1==null) {
				JOptionPane.showMessageDialog(null, "개설년도를 입력하세요");
				return;
			} else if (term1==null) {
				JOptionPane.showMessageDialog(null, "개설학기를 입력하세요");
				return;
			} else if (grade1==null) {
				JOptionPane.showMessageDialog(null, "학년을 입력하세요");
				return;
			} else if (deptcode == null) {
				JOptionPane.showMessageDialog(null, "학과를 입력하세요");
				return;
			} else if (profcode == null) {
				JOptionPane.showMessageDialog(null, "담당교수를 입력하세요");
				return;
			} else {
				int result = Regist(code);
				if (result != 0) {
					getListAll();
					text1.setText(null);
					text2.setText(null);
					text3.setText(null);
					text4.setText(null);
					box1.setSelectedIndex(0);
					box2.setSelectedIndex(0);
					box3.setSelectedIndex(0);
					box4.setSelectedIndex(0);
					box5.setSelectedIndex(0);
					box6.setSelectedIndex(0);
					
					
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
					text1.setEditable(true);
					box1.setSelectedIndex(0);
					box2.setSelectedIndex(0);
					box3.setSelectedIndex(0);
					box4.setSelectedIndex(0);
					box5.setSelectedIndex(0);
					box6.setSelectedIndex(0);

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
						int result = deleteDb(abc);
						if (result != 0) {
							getListAll();
							text1.setEditable(true);
							box1.setSelectedIndex(0);
							box2.setSelectedIndex(0);
							box3.setSelectedIndex(0);
							box4.setSelectedIndex(0);
							box5.setSelectedIndex(0);
							box6.setSelectedIndex(0);
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
		if (e.getSource() == box2) {
			if(box2.getSelectedItem().toString().equals("2021")) {
				year1 = "2021";
			}
			else if(box2.getSelectedItem().toString().equals("2022")) {
				year1 = "2022";
			}
			else if(box2.getSelectedItem().toString().equals("2023")) {
				year1 = "2023";
			}
			else if(box2.getSelectedItem().toString().equals("2024")) {
				year1 = "2024";
			}
			else if(box2.getSelectedItem().toString().equals("2025")) {
				year1 = "2025";
			}
			else if(box2.getSelectedItem().toString().equals("2026")) {
				year1 = "2026";
			}
			else if(box2.getSelectedItem().toString().equals("2027")) {
				year1 = "2027";
			}
			else if(box2.getSelectedItem().toString().equals("2028")) {
				year1 = "2028";
			}
			else if(box2.getSelectedItem().toString().equals("2029")) {
				year1 = "2029";
			}
			else if(box2.getSelectedItem().toString().equals("2030")) {
				year1 = "2030";
			}
		}
		if (e.getSource() == box4) {
			if(box4.getSelectedItem().toString().equals("1학년")) {
				grade1 = "1학년";
			}
			else if(box4.getSelectedItem().toString().equals("2학기")) {
				grade1 = "2학년";
			}
			else if(box4.getSelectedItem().toString().equals("3학년")) {
				grade1 = "3학년";
			}
			else if(box4.getSelectedItem().toString().equals("4학년")) {
				grade1 = "4학년";
			}
		}
		if (e.getSource() == box5) {
			if(box5.getSelectedItem().toString().equals("1학기")) {
				term1 = "1학기";
			}
			else if(box5.getSelectedItem().toString().equals("2학기")) {
				term1 = "2학기";
			}
			if(box5.getSelectedItem().toString().equals("여름학기")) {
				term1 = "여름학기";
			}
			if(box5.getSelectedItem().toString().equals("겨울학기")) {
				term1 = "학기";
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Subject();
	}

}
