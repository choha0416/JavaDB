package webexample;
import java.sql.*;
public class FuctionCallExample {

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
			//매개변수화된 호출문 작성과 callableStatement 얻기
			String sql = "{? = call user_login(?, ?)}";
			CallableStatement cstmt = conn.prepareCall(sql);
			//값 지정 및 리턴 타입 지정
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setString(2, "fall");
			cstmt.setString(3, "12345");
			
			//함수 실행 및 리턴값 얻기
			cstmt.execute();
			int result = cstmt.getInt(1);
			
			cstmt.close();
			//로그인 경과 스위치 익스프레션 사용
			String message = switch(result) {
			case 0 -> "로그인 성공";
			case 1 -> "비밀번호가 틀림";
			default -> "아이디가 존재하지 않음";
			};
			System.out.println(message);
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
