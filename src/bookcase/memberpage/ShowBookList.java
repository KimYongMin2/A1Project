package bookcase.memberpage;

import bookcase.Book;
import bookcase.Member;

import java.util.Scanner;

public class ShowBookList {
    static Scanner scanner = new Scanner(System.in);
    private int menuButton = 0;
    private Member member;
    private Book book;

    public ShowBookList(Member member){
        this.member = member;
    }
    public void showBookListStart() {
        while (menuButton != 4) {
            System.out.println("------------------------");
            System.out.println("1. 내가 대여중인 도서 보기");
            System.out.println("2. 별점랭킹 추천 조회");
            System.out.println("3. 대여가능한 도서 보기");
            System.out.println("4. 종료");
            System.out.println("------------------------");

            System.out.print("해당하는 메뉴를 선택해주세요 : ");
            menuButton = Integer.parseInt(scanner.nextLine());

            switch (menuButton) {
                case 1:
                    // 내가 대여중인 도서 보기 (반납기한도)
                    showMyUsingBook();
                    break;
                case 2:
                    // 별점랭킹 추천 조회
                    showStarBook();
                    break;
                case 3:
                    // 대여가능한 도서 조회
                    showCanUsingBook();
                    break;
                case 4:
                    // 종료
                    System.out.println("종료합니다");
                    break;
                default:
                    System.out.println("잘못입력하셨습니다");
                    break;
            }
        }
    }

    public void showMyUsingBook(){
        System.out.println("내가 대여중인 도서입니다");
    }

    public void showStarBook(){
        System.out.println("별점랭킹입니다");
    }

    public void showCanUsingBook(){
        System.out.println("대여가능한 도서입니다.");
    }

}
