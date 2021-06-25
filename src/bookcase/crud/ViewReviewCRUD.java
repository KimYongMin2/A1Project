package bookcase.crud;

import java.sql.*;
import java.util.*;

import bookcase.object.*;
import bookcase.util.*;

public class ViewReviewCRUD {
	/*싱글톤 처리*/
	private ViewReviewCRUD() {}
	private static ViewReviewCRUD viewReviewCrud = new ViewReviewCRUD(); 
	public static ViewReviewCRUD getInstance() {
		return viewReviewCrud;
	}
	
	// 1. SELECT //사용자가 리뷰를 볼 때, 책정보와 평점, 한줄평을 한 번에 볼 수 있게 처리
	public ArrayList<ViewReview> getReviewList(Connection con) {
				
		ArrayList<ViewReview> list = new ArrayList<ViewReview>();
				
		Statement stmt = null;
		ResultSet rs = null;
				
		try {
			stmt = con.createStatement();
			String sql = "SELECT BNAME, BWRITER, RSCORE, RCOMMENT "
					+ "FROM BOOK NATURAL JOIN REVIEW";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				list.add(new ViewReview(rs.getString(1), rs.getString(2), rs.getInt(3),
						rs.getString(4)));
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(stmt);
			CloseUtil.close(rs);
		}			
		return list;
	}
}
