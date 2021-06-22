package bookcase.memberpage;

import BookRentPage.BookRentPage;
import bookcase.Member;

import java.util.Scanner;

public class MemberPage {
    static Scanner scanner = new Scanner(System.in);
    private int menuButton = 0;
    private Member member;
    public MemberPage(Member member){
        this.member = member;
    }

    public void memberPageStart(){
        while (menuButton != 6) {
            System.out.println("--------------------------------------");
            System.out.println("1. 도서목록 검색");
            System.out.println("2. 도서대여");
            System.out.println("3. 도서반납");
            System.out.println("4. 리뷰작성");
            System.out.println("5. 내정보보기");
            System.out.println("6. 종료");
            System.out.println("--------------------------------------");

            System.out.print("해당 메뉴를 선택해주세요 : ");
            menuButton = Integer.parseInt(scanner.nextLine());

            switch (menuButton) {
                case 1:
                    // 도서목록 검색
                    new ShowBookList(member).showBookListStart();
                    break;
                case 2:
                    // 도서 대여
                    new BookRentPage(member).BookUsingStart();
                    break;
                case 3:
                    // 도서 반납
                    break;
                case 4:
                    // 리뷰작성
                    break;
                case 5:
                    // 내정보
                    break;
                case 6:
                    // 종료
                    System.out.println("종료합니다");
                    break;
                default:
                    System.out.println("잘못입력하셨습니다");
                    break;
            }
        }
    }

}
