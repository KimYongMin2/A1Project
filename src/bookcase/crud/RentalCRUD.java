package bookcase.crud;

import bookcase.object.mUsingBook;
import bookcase.object.Book;
import bookcase.object.Member;
import bookcase.object.Using;
import bookcase.util.*;

import java.sql.*;
import java.util.*;

/***
 * 
 * @author 은경
 *  
 */

public class RentalCRUD {

	/* 싱글톤 처리 */
	public RentalCRUD() {
	}

	private static RentalCRUD rentalCRUD = new RentalCRUD();

	public static RentalCRUD getInstance() {
		return rentalCRUD;
	}
	
	
	// 1. SELECT // 현재 회원들이 대여중인 모든 도서 가져오기: 관리자 메뉴
	/***
	 * 0627 
	 * @author 지원
	 * 회원들이 대여중인 도서리스트 조회 추가
	 * (회원 이름, 대여 일자, 반납일자 join)
	 */
	
		public ArrayList<mUsingBook> mUsingBooks (Connection con) {

			ArrayList<mUsingBook> list = new ArrayList<mUsingBook>();

			Statement stmt = null;
			ResultSet rs = null;

			try {
				stmt = con.createStatement();
				String sql = "SELECT MNAME, BNAME, BWRITER, BPUBLISHER, BGENRE, RENTALDATE, RETURNDATE "
						+ "FROM MEMBER NATURAL JOIN RENTAL NATURAL JOIN BOOK";
				rs = stmt.executeQuery(sql);

				while (rs.next()) {
					list.add(new mUsingBook(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
							rs.getString(5), rs.getString(6), rs.getString(7)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				CloseUtil.close(stmt);
				CloseUtil.close(rs);
			}
			return list;
		}
		

		
	// 1-2. SELECT 
	/***
	 * 내가 대여중인 모든 도서 가져오기 : 회원 메뉴
	 * @author 민주
	 */
		
	public ArrayList<Book> getMyRentalList(Connection con, Member member) {

		ArrayList<Book> list = new ArrayList<Book>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "SELECT BOOKCODE, BNAME, BWRITER, BPUBLISHER, BGENRE, BPRICE, "
					+ "BUSING, BAGEUSING "
					+ "FROM BOOK NATURAL JOIN RENTAL "
					+ "WHERE MEMBERCODE = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member.getMemberCode());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7),  rs.getString(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt);
			CloseUtil.close(rs);
		}
		return list;
	}
	
	
	
	// 1-3. SELECT
	/***
	 * 대여가능한 도서 목록 가져오기 : 회원 메뉴
	 * @author 민주
	 */
	
	public ArrayList<Book> getPossibleList(Connection con) {

		ArrayList<Book> list = new ArrayList<Book>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM BOOK B "
					+ "WHERE NOT EXISTS (SELECT * FROM RENTAL R WHERE B.BOOKCODE = R.BOOKCODE) "
					+ "ORDER BY BOOKCODE";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7),  rs.getString(8)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(stmt);
			CloseUtil.close(rs);
		}
		return list;
	}
	
	
	// 1-4. SELECT: USING Array로 렌탈 테이블 가져오기
	public ArrayList<Using> getRentalTable(Connection con) {

		ArrayList<Using> list = new ArrayList<Using>();

		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM RENTAL";
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				list.add(new Using(rs.getInt(1), rs.getString(2), rs.getString(3), 
						rs.getInt(4), rs.getInt(5)));
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
	public void insertRental(Connection con, Using usingBook){

		PreparedStatement pstmt = null;

		try {
			String insertSql = "INSERT INTO RENTAL VALUES "
					+ "(rental_code_pk.nextval, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(insertSql);

			pstmt.setString(1, usingBook.getRentalDate());
			pstmt.setString(2, usingBook.getReturnDate());
			pstmt.setInt(3, usingBook.getMemberCode());
			pstmt.setInt(4, usingBook.getBookCode());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt);
		}
	}
	
	

	// 4. DELETE 메소드
	public void deleteRental(Connection con, int bCode) {
		
		PreparedStatement pstmt = null;

		try {
			String deleteSql = "DELETE FROM RENTAL WHERE BOOKCODE = ?";
			pstmt = con.prepareStatement(deleteSql);
			pstmt.setInt(1, bCode);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt);
		}
	
	
	}
	
	// 4-1. DELETE
	/***
	 * 내가 대여중인 책을, 렌탈리스트에서 삭제하기
	 * @author 민주
	 */
	public void ReturnMyBook(Connection con, Using use) {

		PreparedStatement pstmt = null;

		try {
			String deleteSql = "DELETE FROM RENTAL WHERE BOOKCODE = ?";
			pstmt = con.prepareStatement(deleteSql);
			pstmt.setInt(1, use.getBookCode());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt);
		}
	}
}



