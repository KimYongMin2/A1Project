package bookcase.crud;

import java.sql.*;
import java.util.*;

import bookcase.object.*;
import bookcase.util.*;

public class BookCRUD {
	/*싱글톤 처리*/
	private BookCRUD() {}
	private static BookCRUD bookCrud = new BookCRUD();
	public static BookCRUD getInstance() {
		return bookCrud;
	}

	
	// 1. SELECT //전체 책 가져오기
	public ArrayList<Book> getBookList(Connection con) {

		ArrayList<Book> list = new ArrayList<Book>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM BOOK ORDER BY BOOKCODE";
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				list.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), 
					rs.getString(8)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(stmt);
			CloseUtil.close(rs);
		}

		return list;
	}
	
	
	// 1-2. SELECT 메소드: 별점 상위 5개 책 리스트 가져오기
	/**
	 * @author 지원
	 * 0627
	 * 쿼리문 수정
	 */
	public ArrayList<Book> getBestBookList(Connection con) {

		ArrayList<Book> list = new ArrayList<Book>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			String sql = "SELECT BOOKCODE, BNAME, BWRITER, BPUBLISHER, BGENRE, BPRICE, BUSING, BAGEUSING "
					+ "FROM BOOK NATURAL JOIN REVIEW "
					+ "WHERE BOOKCODE = BOOKCODE AND ROWNUM <=5 "
					+ "GROUP BY BOOKCODE, BNAME, BWRITER, BPUBLISHER, BGENRE, BPRICE, BUSING, BAGEUSING "
					+ "ORDER BY AVG(RSCORE) DESC";
			
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				list.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3),
					rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7), 
					rs.getString(8)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(stmt);
			CloseUtil.close(rs);
		}

		return list;
	}

	
	
	// 2. INSERT 메소드
	public void insertBook(Connection con, Book book){

		PreparedStatement pstmt = null;

		try {
			String insertSql = "INSERT INTO BOOK VALUES "
					+ "(BOOK_CODE_PK.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(insertSql);

			pstmt.setString(1, book.getbName());
			pstmt.setString(2, book.getbWriter());
			pstmt.setString(3, book.getbPublisher());
			pstmt.setString(4, book.getbGenre());
			pstmt.setInt(5, book.getbPrice());
			pstmt.setString(6, book.getbUsing());
			pstmt.setString(7, book.getbAgeUsing());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt);
		}
	}

	
	// 3. UPDATE 메소드
	public void updateBook(Connection con, Book book) {

		PreparedStatement pstmt = null;

		try {
			String updateSql = "UPDATE BOOK SET BNAME = ?, BWRITER = ?, "
					+ "BPUBLISHER = ?, BGENRE = ?, BPRICE = ?"
					+ ", BUSING = ?, BAGEUSING = ? WHERE BOOKCODE = ?";
			pstmt = con.prepareStatement(updateSql);

			pstmt.setString(1, book.getbName());
			pstmt.setString(2, book.getbWriter());
			pstmt.setString(3, book.getbPublisher());
			pstmt.setString(4, book.getbGenre());
			pstmt.setInt(5, book.getbPrice());
			pstmt.setString(6, book.getbUsing());
			pstmt.setString(7, book.getbAgeUsing());
			pstmt.setInt(8, book.getBookCode());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt);
		}
	}
	

	// 4. DELETE 메소드
	public void deleteBook(Connection con, Book book) {
		PreparedStatement pstmt = null;

		try {
			String deleteSql = "DELETE FROM BOOK WHERE BOOKCODE = ?";
			pstmt = con.prepareStatement(deleteSql);
			pstmt.setInt(1, book.getBookCode());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt);
		}
	}
}
