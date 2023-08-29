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

public class Department extends JFrame implements ActionListener {
	JPanel panel1, panel2, panel3, panel4;
	JLabel label1, label2, label3, label4;
	Font font;
	JComboBox box1;
	JTextField text1, text2, text3, text4, text5;
	JButton button1, button2, but1, but2, but3, but4;

	DefaultTableModel dtm;
	JTable table;
	JScrollPane jsp;

	PreparedStatement pstmt;
	ConnectDb connect;
	ResultSet rs;
	int srow = -1;
	String cate = null;

	Department() {
		setTitle("학과관리: "+Login.username);
		setSize(500, 650);
		setLayout(null);

		panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(0, 0, 500, 50);
		panel1.setBackground(Color.black);
		label1 = new JLabel("학과 관리");
		label1.setLayout(null);
		label1.setBounds(200, 0, 100, 50);
		label1.setForeground(Color.white);
		font = new Font("돋움", Font.BOLD, 15);
		label1.setFont(font);

		panel1.add(label1);

		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBounds(0, 60, 485, 200);
		panel2.setBorder(new TitledBorder(new LineBorder(Color.gray, 1), "입력"));

		label2 = new JLabel("학과코드");
		label2.setBounds(15, 30, 70, 30);
		label3 = new JLabel("학과명");
		label3.setBounds(180, 30, 70, 30);
		label4 = new JLabel("전공명");
		label4.setBounds(330, 30, 70, 30);

		text1 = new JTextField();
		text1.setBounds(75, 35, 90, 20);
		text2 = new JTextField();
		text2.setBounds(225, 35, 90, 20);
		text3 = new JTextField();
		text3.setBounds(375, 35, 90, 20);

		panel2.add(label2);
		panel2.add(label3);
		panel2.add(label4);

		panel2.add(text1);
		panel2.add(text2);
		panel2.add(text3);

		panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.setBounds(0, 260, 480, 60);
		panel3.setBackground(Color.pink);

		box1 = new JComboBox();
		box1.setBounds(10, 20, 135, 20);
		box1.addItem("카테고리 선택");
		box1.addItem("학과코드");
		box1.addItem("학과명");
		box1.addItem("전공명");

		text4 = new JTextField();
		text4.setBounds(160, 20, 120, 20);

		button1 = new JButton("조회");
		button1.setBounds(310, 20, 70, 20);
		button2 = new JButton("전체조회");
		button2.setBounds(390, 20, 85, 20);

		panel3.add(box1);
		panel3.add(text4);
		panel3.add(button1);
		panel3.add(button2);

		DepartTables();

		panel4 = new JPanel();
		panel4.setLayout(null);
		panel4.setBounds(0, 530, 500, 80);

		but1 = new JButton("등록");
		but1.setBounds(5, 20, 110, 40);
		but2 = new JButton("수정");
		but2.setBounds(125, 20, 110, 40);
		but3 = new JButton("삭제");
		but3.setBounds(245, 20, 110, 40);
		but4 = new JButton("종료");
		but4.setBounds(365, 20, 110, 40);

		panel4.add(but1);
		panel4.add(but2);
		panel4.add(but3);
		panel4.add(but4);

		add(jsp);
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);

		connect = new ConnectDb();

		table.addMouseListener(new MyAdapter());

		button1.addActionListener(this);
		button2.addActionListener(this);
		but1.addActionListener(this);
		but2.addActionListener(this);
		but3.addActionListener(this);
		but4.addActionListener(this);
		box1.addActionListener(this);

		setVisible(true);
	}

	void DepartTables() {
		String[] head = { "학과코드", "학과명", "전공명" };
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
		jsp.setBounds(0, 320, 485, 220);
	}

	public void getListAll() {
		dtm.setRowCount(0);// 0번행부터 addRow가능
		try {
			String sql = "SELECT * FROM department";
			pstmt = connect.conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String code = rs.getString(1);
				String department = rs.getString(2);
				String major = rs.getString(3);

				Object[] rowData = { code, department, major };
				dtm.addRow(rowData);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int Regist(String code, String department, String major) {
		
		
		
		int result = 0;//등록성공여부를 확인하려고 성공하면 1 실패하면 0

		
		String sql = "SELECT * FROM department where code='"+text1.getText()+"'";
		try {
			
			
			pstmt = connect.conn.prepareStatement(sql);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				JOptionPane.showMessageDialog(this, "이미 등록된 학과 입니다");
				text1.setEditable(true);
				text1.requestFocus();
				text1.selectAll();
				return 0;
			}
			sql = "INSERT INTO department(code,department,major) VALUES(?,?,?)";
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.setString(2, department);
			pstmt.setString(3, major);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int Edit(String code, String department, String major) {
		int result = 0;
		String sql = "UPDATE department SET department=?, major=? WHERE code=?";

		try {
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(3, code);
			pstmt.setString(1, department);
			pstmt.setString(2, major);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public int deleteDb(String code) {
		int result = 0; // 기본값 0 지정
		// executeUpdate를 사용해야함 이때 0,1반환
		String sql = "DELETE FROM department WHERE code=?";

		try {
			pstmt = connect.conn.prepareStatement(sql);
			pstmt.setString(1, code);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void Search() {
		if (cate == null) {
			JOptionPane.showMessageDialog(this, "카테고리를 선택하세요");
		} else {
			String sql = "SELECT * FROM department WHERE " + cate + "=?";
			try {
				pstmt = connect.conn.prepareStatement(sql);
				pstmt.setString(1, text4.getText());

				rs = pstmt.executeQuery(); // 값 불러오기, 레코드단위로 반환

				if (!rs.next()) {
					JOptionPane.showMessageDialog(this, "레코드가 없습니다.");
				} else {
					rs = pstmt.executeQuery();
					while (rs.next()) {
						String code = rs.getString(1);
						String department = rs.getString(2);
						String major = rs.getString(3);

						Object[] sear = { code, department, major };
						dtm.addRow(sear);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		String code, department, major;

		code = text1.getText();
		department = text2.getText();
		major = text3.getText();

		if (e.getSource() == box1) {
			if ((String) box1.getSelectedItem() == "학과코드") {
				cate = "code";

			}
			if ((String) box1.getSelectedItem() == "학과명") {
				cate = "department";

			}
			if ((String) box1.getSelectedItem() == "전공명") {
				cate = "major";

			}
		}

		if (e.getSource() == button1) {
			dtm.setNumRows(0);
			Search();
		}
		if (e.getSource() == button2) {
			getListAll();
		}

		if (e.getSource() == but1) {
			
			if (text1.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "학과코드를 입력하세요");
				return;
			} else if (text2.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "학과를 입력하세요");
				return;
			} else if (text3.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "전공을 입력하세요");
				return;
			} else {
				int result = Regist(code, department, major);
				if (result != 0) {
					System.out.println("입력 성공");
					getListAll();
					text1.setText(null);
					text2.setText(null);
					text3.setText(null);
				}
			}
		}
		if (e.getSource() == but2) {
			
			if (srow == -1) {
				JOptionPane.showMessageDialog(null, "Empty");
			} else {
				int result = Edit(code, department, major); //성공하면 1 실패하면 0
				if (result != 0) {
					System.out.println("수정 성공");
					getListAll();
					text1.setText(null);
					text2.setText(null);
					text3.setText(null);
					text1.setEditable(true);
					

				}

			}
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

			String code = (String) table.getValueAt(srow, 0);
			String department = (String) table.getValueAt(srow, 1);
			String major = (String) table.getValueAt(srow, 2);

			text1.setText(code);
			text2.setText(department);
			text3.setText(major);

			text1.setEditable(false);

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Department();
	}

}
