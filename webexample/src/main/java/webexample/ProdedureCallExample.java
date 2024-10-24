package webexample;
import java.sql.*;
public class ProdedureCallExample {

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
			//매개변수화된 호출문 작성과 callableStatemet 얻기
			String sql = "{call user_create(?, ?, ?, ?, ?, ?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			
			//값 지정 및 리턴타입 지정
			cstmt.setString(1, "summer");
			cstmt.setString(2, "한여름");
			cstmt.setString(3, "12345");
			cstmt.setInt(4, 26);
			cstmt.setString(5, "summeer@mycompant.com");
			cstmt.registerOutParameter(6, Types.INTEGER);
			
			//프로시저 실행 및 리턴값 얻기
			cstmt.execute();
			int rows = cstmt.getInt(6);
			System.out.println("저장된 행 수: "+rows);
			
			cstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}

}
