package university;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Act {
	PreparedStatement pstmt;
	ConnectDb connect;
	ResultSet rs;
	
	public int Regist(String code, String department, String major) {
		connect = new ConnectDb();
		
		int result = 0;

		String sql = "INSERT INTO department(code,department,major) VALUES(?,?,?)";

		try {
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
}
