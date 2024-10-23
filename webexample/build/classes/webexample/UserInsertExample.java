package webexample;
import java.sql.*;
public class UserInsertExample {

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
			String sql = ""+
			"INSERT INTO users(userid, username, userpassword, userage, useremail) "+
					"VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"winter2");
			pstmt.setString(2,"한겨울");
			pstmt.setString(3,"12345");
			pstmt.setInt(4, 25);
			pstmt.setString(5, "winter@mycompany.com");
			
			int rows = pstmt.executeUpdate();
			System.out.println("저장된 행 수"+rows);
			pstmt.close();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn !=null) {
				try {
					//연결끊기
					conn.close();
					System.out.println("연결끊기");
				} catch(SQLException e) {}
			}
		}
	}

}
