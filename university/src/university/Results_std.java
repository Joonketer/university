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

import university.Results_prof.MyAdapter;

public class Results_std extends JFrame implements ActionListener{
	JPanel panel1,panel2,panel3,panel4;
	JLabel label1, label2, label3, label4, label5, label6;
	Font font;
	JComboBox box1,box2,box3,box4;
	JTextField text1,text2,text3,text4,text5;
	JButton button2, but4;
	
	int srow = -1;
	
	DefaultTableModel dtm;
	JTable table;
	JScrollPane jsp;
	
	PreparedStatement pstmt;
	ConnectDb connect = new ConnectDb();
	ResultSet rs,rs1,rs2;
	
	String grade0 = null;
	String subcate = null;
	String subcode = null;
	String cate = null;
	
	Results_std(String s) {
		setTitle("성적관리: "+Login.username);
		setSize(700,650);
		setLayout(null);

		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(0, 0, 700, 50);
		panel1.setBackground(Color.black);
		label1 = new JLabel("성적 관리");
		label1.setLayout(null);
		label1.setBounds(300, 0, 100, 50);
		label1.setForeground(Color.white);
		font = new Font("돋움", Font.BOLD, 15);
		label1.setFont(font);
		
		panel1.add(label1);
		
		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(0,60,685,200);
		panel2.setBorder(new TitledBorder(new LineBorder(Color.gray, 1), "입력"));
		
		label2 = new JLabel("학생코드");
		label2.setBounds(30,20,70,20);
		label3 = new JLabel("학생이름");
		label3.setBounds(230,20,70,20);
		label4 = new JLabel("개설년도");
		label4.setBounds(460,20,70,20);
		label5 = new JLabel("개설학년");
		label5.setBounds(30,60,70,20);
		label6 = new JLabel("개설학기");
		label6.setBounds(230,60,70,20);
		
		
		text1 = new JTextField();
		text1.setBounds(110,20,100,20);
		text1.setText(s);
		text2 = new JTextField();
		text2.setBounds(310,20,100,20);
		
		
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
		box3.addItem("1학년");
		box3.addItem("2학년");
		box3.addItem("3학년");
		box3.addItem("4학년");
		
		box4 = new JComboBox();
		box4.setBounds(310,60,100,20);
		box4.addItem("1학기");
		box4.addItem("2학기");
		box4.addItem("여름학기");
		box4.addItem("겨울학기");
		
		panel2.add(label2);
		panel2.add(label3);
		panel2.add(label4);
		panel2.add(label5);
		panel2.add(label6);
		
		panel2.add(text1);
		panel2.add(text2);
		
		panel2.add(box2);
		panel2.add(box3);
		panel2.add(box4);
		
		
		panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(0,250, 680, 70);
		panel3.setBackground(Color.pink);
		
		box1 = new JComboBox();
		box1.setBounds(30,25,135,20);
		box1.addItem("교과목 선택");
		box1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				subcate = (String) box1.getSelectedItem();

				try {
					String sqls = "SELECT * FROM subject where subject = ?";
					pstmt = connect.conn.prepareStatement(sqls);
					pstmt.setString(1, subcate);

					rs = pstmt.executeQuery();

					while (rs.next()) {
						subcode = rs.getString(1);
						text4.setText(subcode);
						cate = subcode;
					}
				} catch (SQLException a) {
					a.printStackTrace();
				}

			}

		});
		
		
		
		text4 = new JTextField();
		text4.setBounds(200,25,250,20);
		
		button2 = new JButton("조회");
		button2.setBounds(560,25,85,20);
		
		panel3.add(box1);
		panel3.add(text4);
		
		panel3.add(button2);
		
		
		RsstdTables();
		Sub();
		
		panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setBounds(0,530,700,80);
		
		but4 = new JButton("종료");
		but4.setBounds(565,20,110,40);
		
		panel4.add(but4);
		
		add(jsp);
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		
		getListAll();
		DeptName();
		
		button2.addActionListener(this);
		but4.addActionListener(this);
		
		table.addMouseListener(new MyAdapter());
		
		setVisible(true);
		
	}
	
	void Sub() {
		
		try {
			String sql = "SELECT * FROM attended WHERE std_code=?";
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(1, text1.getText());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String subcode = rs.getString(4);
				
				try {
					String sql1 = "SELECT * FROM subject WHERE code=?";
					pstmt = connect.conn.prepareStatement(sql1);
					pstmt.setString(1, subcode);
					rs1 = pstmt.executeQuery();

					while (rs1.next()) {
						String subject = rs1.getString(2);
						box1.addItem(subject);
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
				text2.setText(name1);
			}
		} catch (SQLException a) {
			a.printStackTrace();
		}
				
	}
	
	
	void RsstdTables() {
		String[] head = {"교과목명","출석","레포트","중간","기말","가산점","합계","학점","과목코드"};
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
		String subjname=null;
		dtm.setRowCount(0);// 0번행부터 addRow가능
		try {
			String sql = "SELECT * FROM results where std_code=?";
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(1,text1.getText());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String subj_code = rs.getString(4);
				String middle= rs.getString(8);
				String fainal = rs.getString(9);
				String report = rs.getString(10);
				String attended = rs.getString(11);
				String add = rs.getString(12);
				String sum = rs.getString(13);
				String grade_value = rs.getString(14);
				
				String sql2="SELECT * FROM subject where code =?";
				try {
				pstmt = connect.conn.prepareStatement(sql2);
				pstmt.setString(1,subj_code);
				rs1 = pstmt.executeQuery();
				while(rs1.next()) {
					subjname=rs1.getString(2);
				}
				
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
				Object[] rowData = { subjname,attended,report,middle,fainal,add,sum, grade_value,subj_code};
				dtm.addRow(rowData);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	public void Search() {
		String subjname = null;
		String std_name = null;
		
			String sql = "SELECT * FROM results WHERE subj_code=?";
			try {
				pstmt = connect.conn.prepareStatement(sql);
				pstmt.setString(1, cate);

				rs = pstmt.executeQuery(); // 값 불러오기, 레코드단위로 반환

				if (!rs.next()) {
					JOptionPane.showMessageDialog(this, "레코드가 없습니다.");
				} else {
					rs = pstmt.executeQuery();

					while (rs.next()) {
						String subj_code = rs.getString(4);
						String middle= rs.getString(8);
						String fainal = rs.getString(9);
						String report = rs.getString(10);
						String attended = rs.getString(11);
						String add = rs.getString(12);
						String sum = rs.getString(13);
						String grade_value = rs.getString(14);
						
						String sql2="SELECT * FROM subject where code =?";
						try {
						pstmt = connect.conn.prepareStatement(sql2);
						pstmt.setString(1,subj_code);
						rs1 = pstmt.executeQuery();
						while(rs1.next()) {
							subjname=rs1.getString(2);
						}
						
						}catch (SQLException e1) {
							e1.printStackTrace();
						}
						Object[] rowData = { subjname,attended,report,middle,fainal,add,sum, grade_value,subj_code};
						dtm.addRow(rowData);

					}
				} }catch (SQLException e) {
					e.printStackTrace();
				}
			}
	

	
	class MyAdapter extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			srow = table.getSelectedRow();

			String std_code = (String) table.getValueAt(srow, 0);
			String std_name = (String) table.getValueAt(srow, 1);
			String chul = (String) table.getValueAt(srow, 2);
			String report = (String) table.getValueAt(srow, 3);
			String mid = (String) table.getValueAt(srow, 4);
			String last = (String) table.getValueAt(srow, 5);
			String add = (String) table.getValueAt(srow, 6);
			Integer sum = Integer.parseInt(chul) + Integer.parseInt(report) + Integer.parseInt(mid)
					+ Integer.parseInt(last) + Integer.parseInt(add);
			table.setValueAt(sum.toString(), srow, 7);
			String subjcode = (String) table.getValueAt(srow, 8);
			try {
				String sql0 = "SELECT * FROM results where subj_code = ?";
				pstmt = connect.conn.prepareStatement(sql0);
				pstmt.setString(1, subjcode);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					String year0 = rs.getString(5);
					String grade0 = rs.getString(6);
					String term0 = rs.getString(7);

					box2.setSelectedItem(year0);
					box3.setSelectedItem(grade0);
					box4.setSelectedItem(term0);
					
				}
				
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button2) {
			if((String)box1.getSelectedItem() == "교과목 선택") {
				cate = null;
				
			}
			if (cate == null) {
				JOptionPane.showMessageDialog(this, "카테고리를 선택하세요");
			}
			else if(text4.getText().length()==0) {
				getListAll();
			}
			else {
				dtm.setNumRows(0);
				Search();
			}
		}

		if (e.getSource() == but4) {
			dispose();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Results_std(Login.username);
	}

}
