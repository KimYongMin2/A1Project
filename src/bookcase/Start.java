package bookcase;

import bookcase.object.Member;

import java.util.Scanner;

public class Start {
    public void startStart(){
        MemberHandler mh = new MemberHandler();
        int menuButton = 0;
        Member member;
        
        Scanner scanner = new Scanner(System.in);

        while (menuButton != 5) {
            System.out.println("1. 회원 로그인");
            System.out.println("2. 관리자 로그인");
            System.out.println("3. 회원가입");
            System.out.println("4. 아이디찾기");
            System.out.println("5. 종료");
            System.out.print("해당 사항을 입력해주세요 : ");
            menuButton = Integer.parseInt(scanner.nextLine());

            switch (menuButton){
                case 1 :
                    //로그인
                	member = mh.login();
                	if(member != null) {
                		new MemberPage(member).memberPageStart();
                	}
                	break;
                    // 완료시 멤버페이지 or 관리자페이지
                    // 일단 멤버페이지로 넘어감
                case 2 :
                    //로그인
                    member = mh.login();
                    // 완료시 멤버페이지 or 관리자페이지
                    // 일단 멤버페이지로 넘어감
                    new ManagerPage().bookManagerStart();
                    break;
                case 3 :
                    //회원가입
                    mh.joinMember();
                    break;
                case 4 :
                    //아이디찾기
                    mh.findingId();
                    break;
                case 5 :
                    //종료
                    System.out.println("종료합니다");
                    break;
            }
        }
    }
}
