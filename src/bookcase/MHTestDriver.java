package bookcase;

import java.util.*;

public class MHTestDriver {
	
	
	/**
	 * MemberHandler의 테스트 환경입니다!...
	 * 
	 * 구현 후 삭제 예정입니다..
	 * 
	 * @author 민주
	 */
	
	public static void main(String[] args) {
		try {
			
			Scanner kb = new Scanner(System.in);
			MemberHandler mh = new MemberHandler();
			Member member = null;
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			while(true) {
				System.out.println("=============");
				System.out.println("1. 회원가입");
				System.out.println("2. 로그인");
				System.out.println("3. 아이디찾기");
				System.out.println("4. 회원탈퇴");
				System.out.println("5. 종료");
				System.out.println("=============");
				int menu = Integer.parseInt(kb.nextLine());
				switch (menu) {
				case 1: 
					mh.joinMember();
					break;
				case 2: 
					member = mh.login();
					break;
				case 3: 
					mh.findingId();
					break;
				case 4:
					mh.leaveMember(member);
					break;
				case 5:
					System.exit(0);
				default:
					throw new IllegalArgumentException("Unexpected value");
				}
			} 
			
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
