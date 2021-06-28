package bookcase.util;

import java.sql.*;

public class JDBCconnecting {
	
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "USER1";
	private static String password = "tiger";
	
	public static Connection connecting() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
