package bookcase;

import java.sql.*;
import java.util.*;

public class ReviewCRUD {
	
	/*싱글톤 처리*/
	private ReviewCRUD() {}
	private static ReviewCRUD reviewCrud = new ReviewCRUD(); 
	public static ReviewCRUD getInstance() {
		return reviewCrud;
	}
	// 1. SELECT //모든 리뷰 가져오기
	ArrayList<Review> getReviewList(Connection con) {
				
		ArrayList<Review> list = new ArrayList<Review>();
				
		Statement stmt = null;
		ResultSet rs = null;
				
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM REVIEW ORDER BY ReviewCode";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				list.add(new Review(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						rs.getDouble(4), rs.getString(5)));
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
		Review insertReview(Connection con, Review review){
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			try {
				String insertSql = "INSERT INTO REVIEW VALUES "
						+ "(review_code_pk.nextval, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(insertSql);
				
				pstmt.setInt(1, review.getMemberCode());
				pstmt.setInt(2, review.getBookCode());
				pstmt.setDouble(3, review.getrScore());
				pstmt.setString(4, review.getrComment());
					
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
			return review;
		}

}

