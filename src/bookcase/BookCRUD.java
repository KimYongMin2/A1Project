package bookcase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BookCRUD {
	
	/*싱글톤 처리*/
	private BookCRUD() {}
	private static BookCRUD bookCrud = new BookCRUD();
	public static BookCRUD getInstance() {
		return bookCrud;
	}
	
	// 1. SELECT
		ArrayList<Book> getMemberList(Connection con) {
			
			ArrayList<Book> list = new ArrayList<Book>();
			
			Statement stmt = null;
			ResultSet rs = null;
			
			try {
				stmt = con.createStatement();
				String sql = "SELECT * FROM BOOK ORDER BY MemberCode";
				rs = stmt.executeQuery(sql);
				
				while(rs.next()){
					list.add(new Book(rs.getInt(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getInt(6), rs.getBoolean(7), rs.getBoolean(8)));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if(stmt != null) {
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
		Book insertBook(Connection con, Book book){
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			try {
				String insertSql = "INSERT INTO BOOK VALUES "
						+ "(BOOK_CODE_PK.NEXTVAL, ?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(insertSql);
				
				pstmt.setString(1, book.getbName());
				pstmt.setString(2, book.getbWriter());
				pstmt.setString(3, book.getbPublisher());
				pstmt.setString(4, book.getbGenre());
				pstmt.setInt(5, book.getbPrice());
					
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
			return book;
		}

		// 3. UPDATE 메소드: 반환타입: 반영횟수
		void updateBook(Connection con, Book book) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			try {
				String updateSql = "UPDATE BOOK SET BNAME = ?, BWRITER = ?, BPUBLISHER = ?, BGENRE = ?, BPRICE = ?"
						+ "WHERE BOOKCODE = ?";
				pstmt = con.prepareStatement(updateSql);
		
				pstmt.setString(1, book.getbName());
				pstmt.setString(2, book.getbWriter());
				pstmt.setString(3, book.getbPublisher());
				pstmt.setString(4, book.getbGenre());
				pstmt.setInt(5, book.getbPrice());
				pstmt.setInt(6, book.getBookCode());
					
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
		
		// 4. DELETE 메소드: 반환타입: 반영횟수
		void deleteBook(Connection con, Book book) {
			int result = 0;
			PreparedStatement pstmt = null;

			try {
				String deleteSql = "DELETE FROM MEMBER WHERE BOOKCODE = ?";
				pstmt = con.prepareStatement(deleteSql);
				pstmt.setInt(1, book.getBookCode());
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
