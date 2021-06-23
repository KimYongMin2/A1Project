package bookcase;

import java.sql.*;

import bookcase.object.*;
import bookcase.show.*;
import bookcase.util.*;

public class MyInfoPage implements Show {
	
	Member member;
	public MyInfoPage(Member member) {
		this.member = member;
	}
	
	private static Connection con = JDBCconnecting.connecting();
	MemberHandler mh = new MemberHandler();
	
    private int menuButton = 0;
	
	void MyInfoEditStrat() {
		while (menuButton != 3) {
			showEditMyInfoMenu();
            setMenuButton("메뉴를 선택해주세요");

            switch (menuButton) {
                case 1: //회원 정보 수정
                    mh.updateMember(member);
                    break;
                case 2: //회원 탈퇴
                	mh.leaveMember(member);
                    break;
                case 3:
                    System.out.println("종료합니다");
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다");
                    break;
            }
        }
	}
	
    private void setMenuButton(String s) {
        System.out.print(s);
        menuButton = ScannerUtil.getInputInteger();
    }
}
