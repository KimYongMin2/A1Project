package bookcase.memberpage;

import bookcase.Member;
import bookcase.MemberHandler;

import java.util.Scanner;

public class Start {
    public void startStart(){
        MemberHandler mh = new MemberHandler();
        int menuButton = 0;
        Member member;
        
        Scanner scanner = new Scanner(System.in);

        while (menuButton != 4) {
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 아이디찾기");
            System.out.println("4. 종료");
            System.out.print("해당 사항을 입력해주세요 : ");
            menuButton = Integer.parseInt(scanner.nextLine());

            switch (menuButton){
                case 1 :
                    //로그인
                    member = mh.login();
                    // 완료시 멤버페이지 or 관리자페이지
                    // 일단 멤버페이지로 넘어감
                    new MemberPage(member).memberPageStart();
                    break;
                case 2 :
                    //회원가입
                    mh.joinMember();
                    break;
                case 3 :
                    //아이디찾기
                    mh.findingId();
                    break;
                case 4 :
                    //종료
                    System.out.println("종료합니다");
                    break;
            }
        }
    }
}
