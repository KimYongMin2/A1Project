package BookRentPage;

import java.sql.*;
import java.util.*;

import bookcase.Book;
import bookcase.Using;

/***
 * 
 * @author 은경
 *
 */

public class RentalCRUD {

	/* 싱글톤 처리 */
	private RentalCRUD() {
	}

	private static RentalCRUD rentalCRUD = new RentalCRUD();

	public static RentalCRUD getInstance() {
		return rentalCRUD;
	}

	// 1. SELECT
	public ArrayList<Book> getRentalList(Connection con) {

		ArrayList<Book> list = new ArrayList<Book>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			String sql = "SELECT bookCode, bName, bWriter, bPublisher, bGenre, bPrice, bUsing, bAgeUsing FROM BOOK b join RENTAL r ON b.BOOKCODE = r.BOOKCODE";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7),  rs.getString(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}

	//2. INSERT 메소드 

