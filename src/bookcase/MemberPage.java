package bookcase;

import bookcase.object.Member;
import bookcase.show.Show;
import bookcase.util.CommonFunction;

public class MemberPage implements Show {
    private int menuButton = 0;
    private Member member;
    public MemberPage(Member member){
        this.member = member;
    }

    public void memberPageStart(){
        while (menuButton != 6) {
            try {
                showMemberPageMenu();
                CommonFunction.setMenuButton("해당 메뉴를 선택해주세요 : ", menuButton);

                switch (menuButton) {
                    case 1:
                        // 도서목록 검색
                        new ShowBookListPage(member).showBookListStart();
                        break;
                    case 2:
                        // 도서 대여
                        new BookRentPage(member).BookUsingStart();
                        break;
                    case 3:
                        // 도서 반납
                        new ReturnBookPage(member).BookReturnStart();
                        break;
                    case 4:
                        // 리뷰작성
                        new ReviewAddPage(member).reviewAddStart();
                        break;
                    case 5:
                        // 내정보
                        new MyInfoPage(member).MyInfoEditStrat();
                        break;
                    case 6:
                        // 종료
                        System.out.println("종료합니다");
                        break;
                    default:
                        System.out.println("잘못입력하셨습니다");
                        break;
                }
            }catch (NumberFormatException e){
                System.out.println("숫자로 입력해주세요");
            }
        }
    }
}
