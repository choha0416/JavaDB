package webexample;
import java.sql.*;
public class BoardDeleteExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			//jdbc드라이버 등록
			Class.forName("oracle.jdbc.OracleDriver");
			//연결
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe",
					"system",
					"oracle"
			);
			//System.out.println("연결성공");
			
			String sql = "DELETE FROM boards WHERE bwriter=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "winter");
			
			int rows = pstmt.executeUpdate();
			System.out.println("삭제된 행 수"+rows);
			
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn !=null) {
				try {
					conn.close();
				} catch(SQLException e) {}
			}
		}
	}

}
