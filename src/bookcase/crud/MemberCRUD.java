package bookcase.crud;

import java.sql.*;
import java.util.*;

import bookcase.object.*;
import bookcase.util.*;

public class MemberCRUD {
	/*싱글톤 처리*/
	private MemberCRUD() {}
	private static MemberCRUD memberCrud = new MemberCRUD(); 
	public static MemberCRUD getInstance() {
		return memberCrud;
	}
	// 1. SELECT
	public static ArrayList<Member> getMemberList(Connection con) {
		ArrayList<Member> list = new ArrayList<Member>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			String sql = "SELECT * FROM MEMBER ORDER BY MemberCode";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				list.add(new Member(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getInt(5), rs.getString(6), rs.getString(7)));
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
	public Member insertMember(Connection con, Member member){

		PreparedStatement pstmt = null;
		
		try {
			String insertSql = "INSERT INTO MEMBER VALUES "
					+ "(MEMBER_CODE_PK.NEXTVAL, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(insertSql);
			
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassWord());
			pstmt.setString(3, member.getmName());
			pstmt.setInt(4, member.getAge());
			pstmt.setString(5, member.getPhoneNum());
			pstmt.setString(6, member.getEmail());
				
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt);
		}
		return member;
	}

	// 3. UPDATE 메소드
	public void updateMember(Connection con, Member member) {

		PreparedStatement pstmt = null;
		
		try {
			String updateSql = "UPDATE MEMBER SET ID = ?, PASSWORD = ?, MNAME = ?, AGE = ?, PHONENUM = ?, EMAIL = ?"
					+ "WHERE MEMBERCODE = ?";
			pstmt = con.prepareStatement(updateSql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassWord());
			pstmt.setString(3, member.getmName());
			pstmt.setInt(4, member.getAge());
			pstmt.setString(5, member.getPhoneNum());
			pstmt.setString(6, member.getEmail());
			pstmt.setInt(7, member.getMemberCode());
				
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt);
		}
	}
	
	// 4. DELETE 메소드
	public void deleteMember(Connection con, Member member) {
		PreparedStatement pstmt = null;

		try {
			String deleteSql = "DELETE FROM MEMBER WHERE MEMBERCODE = ?";
			pstmt = con.prepareStatement(deleteSql);
			pstmt.setInt(1, member.getMemberCode());
			pstmt.executeUpdate();

		} catch (SQLException e) { // 보통 예외 던지지 않고, 여기서 처리함
			e.printStackTrace();
		} finally {
			CloseUtil.close(pstmt);
		}
	}
}
