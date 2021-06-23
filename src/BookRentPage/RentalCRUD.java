package BookRentPage;

import java.sql.*;
import java.util.*;

import bookcase.Book;
import bookcase.Member;
import bookcase.Review;
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

	
// 2. INSERT 메소드 : 반환타입: 반영 횟수
 public Using insertRental(Connection con, Using using){
	
	int result = 0;
	PreparedStatement pstmt = null;
	
	try {
		String insertSql = "INSERT INTO RENTAL VALUES "
				+ "(rental_code_pk.nextval, ?, ?, ?, ?)";
		pstmt = con.prepareStatement(insertSql);
		
		pstmt.setString(1, using.getRentalDate());
		pstmt.setString(2, using.getReturnDate());
		pstmt.setInt(3, using.getMemberCode());
		pstmt.setInt(4, using.getBookCode());
			
		result = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	return using;
}


//4. DELETE 메소드: 
	public void deleteRental(Connection con, int bCode) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String deleteSql = "DELETE FROM RENTAL WHERE RENTALCODE = ?";
			pstmt = con.prepareStatement(deleteSql);
			pstmt.setInt(1, bCode);
			result = pstmt.executeUpdate();

		} catch (SQLException e) { // 보통 예외 던지지 않고, 여기서 처리함
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}



