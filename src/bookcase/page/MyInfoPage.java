package bookcase.page;

import bookcase.handler.MemberHandler;
import bookcase.object.*;
import bookcase.show.*;
import bookcase.util.*;

public class MyInfoPage implements Show {
	
	Member member;
	public MyInfoPage(Member member) {
		this.member = member;
	}
    private int menuButton = 0;
	
	public void MyInfoEditStrat() {
		while (menuButton != 4) {
		    try {
                showEditMyInfoMenu();
                MemberHandler mh = new MemberHandler();
                menuButton = ScannerUtil.getInputIntegerS(">> 원하시는 메뉴를 선택하세요 : ");
                switch (menuButton) {
                	case 1: // 내 정보 확인하기
                		mh.showMyInfo(member);
                		System.out.println();
                		break;
                    case 2: // 회원 정보 수정
                        mh.updateMember(member);
                        System.out.println();
                        break;
                    case 3: // 회원 탈퇴
                        mh.leaveMember(member);
                        System.out.println();
                        break;
                    case 4: // 이전 단계
                    	System.out.println("[!] 이전 단계로 돌아갑니다.");
    					System.out.println();
                        break;
                    default:
                    	System.out.println("error : 잘못된 입력입니다.");
                        break;
                }
            }catch (NumberFormatException e){
            	System.out.println("error : 숫자로 입력해주세요.");
            }
        }
	}

}
