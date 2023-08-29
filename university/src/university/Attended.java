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

public class Attended extends JFrame implements ActionListener{
	JPanel panel1,panel2,panel3,panel4;
	JLabel label1, label2, label3, label4;
	Font font;
	JComboBox box1;
	JTextField text1,text2,text3,text4,text5, text6;
	JButton button1, button2, but3, but4;
	
	
	DefaultTableModel dtm,dtm1;
	JTable table,table1;
	JScrollPane jsp,jsp1;
	
	
	PreparedStatement pstmt;
	ResultSet rs, rs1,rs2,rs3;
	ConnectDb connect = new ConnectDb();
	
	String deptcate = null;String cate = null;
	String deptcode=null;String deptname=null;
	
	int srow = -1;
	int srow1 = -1;
	
	String AttendCode =null;
	
	Attended(String std) {
		setTitle("수강신청: "+Login.username);
		setSize(700, 650);
		setLayout(null);

		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(0, 0, 700, 50);
		panel1.setBackground(Color.black);
		label1 = new JLabel("수강신청");
		label1.setLayout(null);
		label1.setBounds(300, 0, 200, 50);
		label1.setForeground(Color.white);
		font = new Font("돋움", Font.BOLD, 15);
		label1.setFont(font);
		
		panel1.add(label1);
		
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(0,60,685,100);
		panel2.setBorder(new TitledBorder(new LineBorder(Color.gray, 1), "입력"));
		
		label2 = new JLabel("학생코드");
		label2.setBounds(45, 30, 70, 30);
		label3 = new JLabel("이름");
		label3.setBounds(230, 30, 70, 30);
		label4 = new JLabel("학과");
		label4.setBounds(420, 30, 70, 30);
		
		text1 = new JTextField();
		text1.setText(std);
		text1.setBounds(110, 35, 90, 20);
		text2 = new JTextField();
		text2.setBounds(270, 35, 90, 20);
		text3= new JTextField();
		text3.setBounds(460, 35, 90, 20);
		
		panel2.add(label2);
		panel2.add(label3);
		panel2.add(label4);
		
		panel2.add(text1);
		panel2.add(text2);
		panel2.add(text3);
		
		
		panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(0,160, 700, 60);
		panel3.setBackground(Color.pink);
		
		box1 = new JComboBox();
		box1.setBounds(30,20,135,20);
		box1.addItem("개설학과 선택");
		Major(); 
		
		box1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				deptcate = (String) box1.getSelectedItem();

				try {
					String sqls =  "SELECT * FROM department where department = ?";
					pstmt = connect.conn.prepareStatement(sqls);
					pstmt.setString(1, deptcate);
					
					rs = pstmt.executeQuery();

					while (rs.next()) {
						deptcode = rs.getString(1);
						deptname = rs.getString(2);
						text4.setText(deptcode);
						text5.setText(deptname);
					}
				} catch (SQLException a) {
					a.printStackTrace();
				}
				
				
				
			}

		});
		
		text4 = new JTextField();
		text4.setBounds(180,20,120,20);
		
		text5 = new JTextField();
		text5.setBounds(330,20,120,20);
		
		button1 = new JButton("조회");
		button1.setBounds(490,20,70,20);
		button2 = new JButton("전체조회");
		button2.setBounds(580,20,85,20);
		
		panel3.add(box1);
		panel3.add(text4);
		panel3.add(text5);
		panel3.add(button1);
		panel3.add(button2);
		
		
		SubjectTables();
		AttendTables();
		
		panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setBounds(0,530,700,80);
		
		
		but3 = new JButton("삭제");
		but3.setBounds(445,20,110,40);
		but4 = new JButton("종료");
		but4.setBounds(565,20,110,40);
		
		panel4.add(but3);
		panel4.add(but4);
		
		add(jsp);
		add(jsp1);
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		
		getListAllAttended();
		getListAllSubject();
		DeptName();
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		but3.addActionListener(this);
		but4.addActionListener(this);
		
		table1.addMouseListener(new AttendMyAdapter());
		table.addMouseListener(new SubjectMyAdapter());
		
		setVisible(true);
		
	}
	void DeptName() {
		String student1 = text1.getText();

		try {
			String sqls =  "SELECT * FROM student where code = ?";
			pstmt = connect.conn.prepareStatement(sqls);
			pstmt.setString(1, student1);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String name1=rs.getString(2);
				String dept1=rs.getString(10);
				text2.setText(name1);
				text3.setText(dept1);
			}
		} catch (SQLException a) {
			a.printStackTrace();
		}
		
		String dept3 = text3.getText();
		
		try {
			String sqls =  "SELECT * FROM department where code = ?";
			pstmt = connect.conn.prepareStatement(sqls);
			pstmt.setString(1, dept3);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String deptnames=rs.getString(2);
				text3.setText(deptnames);
			}
			
			
		}catch (SQLException a) {
			a.printStackTrace();
		}
		
	}
	
	void Major() {

		try {
			String sql = "SELECT * FROM department";
			pstmt = connect.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String dept = rs.getString(2);
				box1.addItem(dept);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	void SubjectTables() {
		String[] head = {"교과목코드","교과목명","개설학과","수업시수","담당교수","학점"};
		String[][] row = {};
		
		dtm = new DefaultTableModel(row, head) {// 삭제,추가가 가능
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table = new JTable(dtm);
		jsp = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		table.setRowHeight(20);
		jsp.setBorder(new TitledBorder(new LineBorder(Color.gray, 1), "교과목 테이블"));
		jsp.setBounds(0,220,685,150);
	}
	
	void AttendTables() {
		String[] head = {"교과목코드","교과목명","개설학과","수업시수","담당교수","학점"};
		String[][] row = {};
		
		dtm1 = new DefaultTableModel(row, head) {// 삭제,추가가 가능
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table1 = new JTable(dtm1);
		jsp1 = new JScrollPane(table1);
		table1.setRowHeight(20);
		jsp1.setBorder(new TitledBorder(new LineBorder(Color.gray, 1), "수강신청 테이블"));
		jsp1.setBounds(0,370,685,170);
	}
	
	public void getListAllSubject() {
		dtm.setRowCount(0);// 0번행부터 addRow가능
		try {
			String sql = "SELECT * FROM subject left OUTER JOIN (SELECT * FROM attended where std_code=?) as att on (subject.code = att.subj_code)";
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(1,  Login.username);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String subjcode = rs.getString(1);
				String subject = rs.getString(2);
				String deptcode = rs.getString(3);
				String year = rs.getString(4);
				String grade = rs.getString(5);
				String term = rs.getString(6);
				String time = rs.getString(7);
				String profcode = rs.getString(8);
				String credit = rs.getString(9);
				
				String att_subj_code = rs.getString(13);
				
				if(att_subj_code !=null) {
					continue;
				}
				String deptname="", profname="";
				String sql1 = "SELECT * FROM department where code =?";
				try {
					pstmt = connect.conn.prepareStatement(sql1);
					pstmt.setString(1, deptcode);
					rs1 = pstmt.executeQuery();

					while (rs1.next()) {
						 deptname = rs1.getString(2);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				String sql2 = "SELECT * FROM professor where code =?";
				try {
					pstmt = connect.conn.prepareStatement(sql2);
					pstmt.setString(1, profcode);
					rs1 = pstmt.executeQuery();

					while (rs1.next()) {
						 profname = rs1.getString(2);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				

				Object[] rowData = {subjcode,subject,deptname,time,profname,credit };
				dtm.addRow(rowData);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	public void getListAllAttended() {
		String subjname = null;
		String deptname = null;
		String time = null;
		String credit = null;
		String profname = null;
		String deptcode1 = null;
		dtm1.setRowCount(0);// 0번행부터 addRow가능
		try {
			String sql = "SELECT * FROM attended where std_code=?";
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(1, Login.username);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String profcode = rs.getString(3);
				String subjcode = rs.getString(4);

				String sql1 = "SELECT * FROM subject where code=?";
				try {
					pstmt = connect.conn.prepareStatement(sql1);
					pstmt.setString(1, subjcode);
					rs1 = pstmt.executeQuery();
					while (rs1.next()) {
						subjname = rs1.getString(2);
						deptcode1 = rs1.getString(3);
						String year = rs1.getString(4);
						time = rs1.getString(7);
						credit = rs1.getString(9);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				String sql2 = "SELECT * FROM professor where code=?";
				try {
					pstmt = connect.conn.prepareStatement(sql2);
					pstmt.setString(1, profcode);
					rs2 = pstmt.executeQuery();
					while (rs2.next()) {
						profname = rs2.getString(2);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				String sql3 = "SELECT * FROM department where code =?";
				try {
					pstmt = connect.conn.prepareStatement(sql3);
					pstmt.setString(1, deptcode1);
					rs3 = pstmt.executeQuery();

					while (rs3.next()) {
						 deptname = rs3.getString(2);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Object[] rowData = { subjcode, subjname, deptname, time, profname, credit };
				dtm1.addRow(rowData);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public void Search() {
		String deptname2 =null;
		String profname2 =null;
		String subcode2 =null;
		String subname2	=null;
		String time2 =null;
		String credit2 =null;
		String dept_code2 =null;
		String prof_code2 =null;
		dtm.setRowCount(0);
		
		try {
			if((text4.getText().length()==0)||(text5.getText().length()==0)) {
				JOptionPane.showMessageDialog(this, "조회할 내용을 입력하세요");
				text4.requestFocus();
				return;
			}
			
			String sqle = "SELECT * FROM subject where dept_code=?";
			pstmt = connect.conn.prepareStatement(sqle);
			pstmt.setString(1, text4.getText());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				subcode2 = rs.getString(1);
				subname2 = rs.getString(2);
				dept_code2 = rs.getString(3);
				time2 = rs.getString(7);
				prof_code2 = rs.getString(8);
				credit2 = rs.getString(9);
				String sql1 = "SELECT * FROM department where code =?";
				try {
					pstmt = connect.conn.prepareStatement(sql1);
					pstmt.setString(1, dept_code2);
					rs1 = pstmt.executeQuery();

					while (rs1.next()) {
						deptname2 = rs1.getString(2);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				String sql2 = "SELECT * FROM professor where code =?";
				try {
					pstmt = connect.conn.prepareStatement(sql2);
					pstmt.setString(1, prof_code2);
					rs1 = pstmt.executeQuery();

					while (rs1.next()) {
						profname2 = rs1.getString(2);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				Object[] rowData = { subcode2, subname2, deptname2, time2, profname2, credit2};
				dtm.addRow(rowData);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void deleteDb() {
		int result = 0; // 기본값 0 지정
		// executeUpdate를 사용해야함 이때 0,1반환
		String sql = "DELETE FROM attended WHERE subj_code=?";

		try {
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(1, AttendCode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		getListAllSubject();
		getListAllAttended();
		
		String sql2 = "DELETE FROM results WHERE subj_code=?";

		try {
			pstmt = connect.conn.prepareStatement(sql2);
			pstmt.setString(1, AttendCode);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	class AttendMyAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			srow = table1.getSelectedRow();

			AttendCode = (String) table1.getValueAt(srow, 0);
			
			

		}
	}

	class SubjectMyAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			srow = table.getSelectedRow();//과목테이블
			//srow1 = table1.getSelectedRow();//수강신청테이블
			String subjcode = null;//과목코드
			String deptcode = null;//학과코드
			String year = null;//년도
			String profcode = null;//교수코드
			String grade = null;
			String term = null;
			String scode = (String) table.getValueAt(srow, 0);
			//String acode = (String) table1.getValueAt(srow1, 0);
			String stdcode = text1.getText();//학생코드
			
			
			String Query = "SELECT * FROM attended where subj_code='" + scode + "'";
			try {
				pstmt = connect.conn.prepareStatement(Query);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					JOptionPane.showMessageDialog(null, "이미 등록된 코드 입니다");
					text4.requestFocus();
					return;
				}

			} catch (SQLException ee) {
				ee.printStackTrace();
			}
		

				String subsql = "SELECT * FROM subject where code=?";
				try {
					pstmt = connect.conn.prepareStatement(subsql);
					pstmt.setString(1, scode);
					rs1 = pstmt.executeQuery();

					while (rs1.next()) {
						// stdcode,profcode,subjcode,year
						subjcode = rs1.getString(1);
						deptcode = rs1.getString(3);
						year = rs1.getString(4);
						profcode = rs1.getString(8);
						grade = rs1.getString(5);
						term = rs1.getString(6);

					}

					String sql2 = "Insert INTO attended(std_code,prof_code,subj_code,year) VALUES(?,?,?,?)";

					pstmt = connect.conn.prepareStatement(sql2);
					pstmt.setString(1, stdcode);
					pstmt.setString(2, profcode);
					pstmt.setString(3, subjcode);
					pstmt.setString(4, year);

					pstmt.executeUpdate();
					
					String query2 = "Insert INTO results(indexno,prof_code,std_code,subj_code,year,grade,"
							+ "term,middle,fainal,report,attended,added,sum,grade_value) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					pstmt = connect.conn.prepareStatement(query2);
					pstmt.setString(1, null);
					pstmt.setString(2, profcode);
					pstmt.setString(3, stdcode);
					pstmt.setString(4, subjcode);
					pstmt.setString(5, year);
					pstmt.setString(6, grade);
					pstmt.setString(7, term);
					pstmt.setString(8, "0");
					pstmt.setString(9, "0");
					pstmt.setString(10, "0");
					pstmt.setString(11, "0");
					pstmt.setString(12, "0");
					pstmt.setString(13, "0");
					pstmt.setString(14, "0");
					
					pstmt.executeUpdate();


				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				getListAllAttended();
				getListAllSubject();
				
				//성적테이블에 학생코드,과목코드,년도,학년,수업시수
				
				
			}

		}

	

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			dtm.setNumRows(0);
			Search();
		}
		if (e.getSource() == button2) {
			dtm.setNumRows(0);
			getListAllSubject();
		}
		if (e.getSource() == but3) {
			deleteDb();
		}
		if (e.getSource() == but4) {
			dispose();
		}
		
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Attended("s001");
	}*/

}
