package bookcase.crud;

import java.sql.*;
import java.util.*;

import bookcase.object.*;
import bookcase.util.*;

public class ReviewCRUD {

	/*싱글톤 처리*/
	private ReviewCRUD() {}
	private static ReviewCRUD reviewCrud = new ReviewCRUD(); 
	public static ReviewCRUD getInstance() {
		return reviewCrud;
	}

	// 1. SELECT //모든 리뷰 가져오기
	public ArrayList<Review> getReviewList(Connection con) {

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
			CloseUtil.close(stmt);
			CloseUtil.close(rs);
		}

		return list;
	}

	// 2. INSERT 메소드
	public void insertReview(Connection con, Review review){

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
			CloseUtil.close(pstmt);
		}
	}

	// 3. DELETE // 탈퇴 할 때 작성자가 적은 모든 리뷰 날리기
	public void deleteReview(Connection con, Member member) {
		PreparedStatement pstmt = null;
		try {
			String deleteSql = "delete from review where membercode = ?";
			pstmt = con.prepareStatement(deleteSql);

			pstmt.setInt(1,member.getMemberCode());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt);
		}
	}
}

