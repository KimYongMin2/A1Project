package bookcase.crud;

import bookcase.object.Book;
import bookcase.object.Member;
import bookcase.object.Using;

import java.sql.*;
import java.util.*;

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

	// 1. SELECT // 대여중인 모든 도서 가져오기: 관리자가 사용
	public ArrayList<Book> getRentalList(Connection con) {

		ArrayList<Book> list = new ArrayList<Book>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			String sql = "SELECT b.bookCode, b.bName, b.bWriter, b.bPublisher, "
					+ "b.bGenre, b.bPrice, b.bUsing, b.bAgeUsing "
					+ "FROM BOOK b join RENTAL r ON b.BOOKCODE = r.BOOKCODE";
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

	// 1-2. SELECT // 내가 대여중인 모든 도서 가져오기
	public ArrayList<Book> getMyRentalList(Connection con, Member member) {

		ArrayList<Book> list = new ArrayList<Book>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT * FROM BOOK WHERE BOOKCODE = "
					+ "(SELECT BOOKCODE FROM RENTAL WHERE MEMBERCODE = ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member.getMemberCode());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7),  rs.getString(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
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
	
	// 1-3. 대여가능한 도서 목록 가져오기
	public ArrayList<Book> getPossibleList(Connection con) {

		ArrayList<Book> list = new ArrayList<Book>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM BOOK B "
					+ "WHERE NOT EXISTS (SELECT * FROM RENTAL R WHERE B.BOOKCODE = R.BOOKCODE)";
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

	// 2. INSERT 메소드
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

	//4. DELETE 메소드
	public void deleteRental(Connection con, int bCode) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
			String deleteSql = "DELETE FROM RENTAL WHERE BOOKCODE = ?";
			pstmt = con.prepareStatement(deleteSql);
			pstmt.setInt(1, bCode);
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
	}
}



