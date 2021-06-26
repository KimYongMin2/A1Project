package bookcase.crud;

import java.sql.*;
import java.util.*;

import bookcase.object.*;
import bookcase.util.*;

/***
 * @author 민주
 */

public class ReturnCRUD {
	
	private ReturnCRUD() {}
	private static ReturnCRUD returnCrud = new ReturnCRUD();
	public static ReturnCRUD getInstance() {
		return returnCrud;
	}

	// 1. SELECT //리턴테이블과 책테이블 조인해서 원하는 값 뽑기 (내가 대여한 책정보, 대여날짜, 반납날짜 얻기
	public ArrayList<Return> getReturnList(Connection con, Member member) {

		ArrayList<Return> list = new ArrayList<Return>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			String sql = "SELECT BNAME, BWRITER, BPUBLISHER, BGENRE, RENTALDATE, RETURNDATE "
					+ "FROM BOOK NATURAL JOIN RENTAL NATURAL JOIN MEMBER "
					+ "WHERE MEMBERCODE = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member.getMemberCode());
			rs = pstmt.executeQuery();

			while(rs.next()){
				list.add(new Return(rs.getString(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt);
			CloseUtil.close(rs);
		}

		return list;
	}
}
	