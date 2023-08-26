package university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConnectDb {
	Connection conn;
	
	static String driver = "com.mysql.jdbc.Driver";
	static String user = "root";
	static String pass = "1111";
	static String dbURL = "jdbc:mysql://localhost:3306/unidb?severTimezone=Asia/Seoul";
	
	ConnectDb() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MySQL 드라이버 검색 성공...");
			
			conn = DriverManager.getConnection(dbURL, user, pass);
			System.out.println("MySQL 연결 성공...");
			
		}catch(ClassNotFoundException e) {
			System.out.println("MySQL 드라이버 검색 오류");
			
		}catch(SQLException e) {
			System.out.println("MySQL 연결 객체 생성 실패" + e);
			System.exit(0);
		}
	}


}


