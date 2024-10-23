package webexample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnecrionExample {

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
			System.out.println("연결성공");
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
