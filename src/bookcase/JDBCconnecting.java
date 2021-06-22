package bookcase;

import java.sql.*;

public class JDBCconnecting {
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "USER1";
	private String password = "tiger";
	
	private Connection connecting() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
