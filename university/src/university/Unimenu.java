package university;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.text.SimpleAttributeSet;

public class Unimenu extends JFrame implements ActionListener{
	SimpleAttributeSet saset;
	JMenuBar mb;
	JMenu admin1,admin2,admin3,admin4,admin5,stu1,stu2,stu3,pro1,pro2,pro3,pro4;
	JMenuItem ad1,ad2,ad3,ad4,ad5,st1,st2,st3,p1,p2,p3,p4;
	
	char checks = Login.username.charAt(0);
	String check = Character.toString(checks);
	
	Unimenu(){
		setTitle("학사관리시스템: "+Login.username);
		
		setSize(500, 650);
		
		saset = new SimpleAttributeSet();
		mb = new JMenuBar();
		
		if (check.equals("s")) {
			StMenu();
			st1.addActionListener(this);
			st2.addActionListener(this);
			st3.addActionListener(this);
		}
		else if(check.equals("f")) {
			ProMenu();
			
			p1.addActionListener(this);
			p2.addActionListener(this);
			p3.addActionListener(this);
			p4.addActionListener(this);
		}
		else if(check.equals("p")) {
			AdMenu();
			
			ad1.addActionListener(this);
			ad2.addActionListener(this);
			ad3.addActionListener(this);
			ad4.addActionListener(this);
			ad5.addActionListener(this);
		}
		else {
			JOptionPane.showMessageDialog(this, "올바르지 않은 접근입니다.");
			dispose();
		}
		
		
		this.setJMenuBar(mb);
		
		setVisible(true);
		
	}
	
	
	public void AdMenu() {
		admin1 = new JMenu("학생관리");
		admin2 = new JMenu("교수관리");
		admin3 = new JMenu("학과관리");
		admin4 = new JMenu("교과목관리");
		admin5 = new JMenu("사용자등록");
		
		ad1 = new JMenuItem("등록");
		ad2 = new JMenuItem("등록");
		ad3 = new JMenuItem("등록");
		ad4 = new JMenuItem("등록");
		ad5 = new JMenuItem("사용자등록");
		
		admin1.add(ad1);
		admin2.add(ad2);
		admin3.add(ad3);
		admin4.add(ad4);
		admin5.add(ad5);
		
		mb.add(admin1);
		mb.add(admin2);
		mb.add(admin3);
		mb.add(admin4);
		mb.add(admin5);
		
		
	}
	public void StMenu() {
		stu1 = new JMenu("수강관리");
		stu2 = new JMenu("성적관리");
		stu3 = new JMenu("사용자등록");
		
		st1 = new JMenuItem("등록");
		st2 = new JMenuItem("조회");
		st3 = new JMenuItem("사용자등록");
		
		stu1.add(st1);
		stu2.add(st2);
		stu3.add(st3);
		
		mb.add(stu1);
		mb.add(stu2);
		mb.add(stu3);
		
		
	}
	public void ProMenu() {
		pro1 = new JMenu("학생관리");
		pro2 = new JMenu("교과목관리");
		pro3 = new JMenu("성적관리");
		pro4 = new JMenu("사용자등록");
		
		p1 = new JMenuItem("등록");
		p2 = new JMenuItem("등록");
		p3 = new JMenuItem("등록");
		p4 = new JMenuItem("사용자등록");
		
		pro1.add(p1);
		pro2.add(p2);
		pro3.add(p3);
		pro4.add(p4);
		
		mb.add(pro1);
		mb.add(pro2);
		mb.add(pro3);
		mb.add(pro4);
		

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ad1) {
			new Student();
		}
		if (e.getSource() == ad2) {
			new Professor();
		}
		if (e.getSource() == ad3) {
			new Department();
		}
		if (e.getSource() == ad4) {
			new Subject();
		}
		if (e.getSource() == ad5) {
			new Admin();
		}
		if (e.getSource() == st1) {
			new Attended(Login.username);//사용자입력
		}
		if (e.getSource() == st2) {
			new Results_std(Login.username);
		}
		if (e.getSource() == st3) {
			new Admin();
		}

		if (e.getSource() == p1) {
			new Student();
		}
		if (e.getSource() == p2) {
			new Subject();
		}
		if (e.getSource() == p3) {
			new Results_prof(Login.username);
		}
		if (e.getSource() == p4) {
			new Admin();
		}
	}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Unimenu();
	}
*/
}
