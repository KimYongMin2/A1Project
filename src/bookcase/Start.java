package bookcase;

import bookcase.object.*;
import bookcase.show.*;
import bookcase.util.*;

public class Start implements Show {
    public void startStart(){
        MemberHandler mh = new MemberHandler();
        int menuButton = 0;
        Member member;

        while (menuButton != 5) {
            try {
                showStartMenu();
                menuButton = CommonFunction.setMenuButton("해당사항을 입력해주세요 : ",menuButton);

                switch (menuButton) {
                    case 1:
                        //로그인
                        member = mh.login();
                        if (member != null) {
                            new MemberPage(member).memberPageStart();
                        }
                        break;
                    // 완료시 멤버페이지 or 관리자페이지
                    // 일단 멤버페이지로 넘어감
                    case 2:
                        //로그인
                        member = mh.login();
                        if (member != null) {
                            new ManagerPage().bookManagerStart();
                        }
                        // 완료시 멤버페이지 or 관리자페이지
                        // 일단 멤버페이지로 넘어감
                        break;
                    case 3:
                        //회원가입
                        mh.joinMember();
                        break;
                    case 4:
                        //아이디찾기
                        mh.findingId();
                        break;
                    case 5:
                        //종료
                        System.out.println("종료합니다");
                        break;
                }
            }catch (NumberFormatException e){
                System.out.println("숫자로 입력해주세요");
            }
        }
    }
}
