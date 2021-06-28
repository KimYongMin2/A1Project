package bookcase.page;

import bookcase.object.Member;
import bookcase.show.Show;
import bookcase.util.Common;
import bookcase.util.ScannerUtil;

public class MemberPage extends Common implements Show {
    private int menuButton = 0;
    private Member member;
    public MemberPage(Member member){
        this.member = member;
    }

    public void memberPageStart(){
        while (menuButton != 6) {
            try {
                showMemberPageMenu();
                menuButton = ScannerUtil.getInputIntegerS(">> 원하시는 메뉴를 선택하세요 : ");
                System.out.println();
                
                switch (menuButton) {
                    case 1:
                        // 도서 목록 검색
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
                        // 내 정보
                        new MyInfoPage(member).MyInfoEditStrat();
                        break;
                    case 6:
                        // 이전 단계
                    	System.out.println("▶ 로그아웃이 완료되었습니다!");
                    	System.out.println();
                        break;
                    default:
                    	System.out.println("error : 잘못된 입력입니다.");
                        break;
                }
            }catch (NumberFormatException e){
            	System.out.println("error : 숫자로 입력해주세요.\n");
            }
        }
    }
}
