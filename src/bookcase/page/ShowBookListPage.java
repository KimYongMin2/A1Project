package bookcase.page;

import bookcase.*;
import bookcase.object.*;
import bookcase.show.*;
import bookcase.util.*;

public class ShowBookListPage implements Show {

	private int menuButton = 0;
	private Member member;

	public ShowBookListPage(Member member){
		this.member = member;
	}
	public void showBookListStart() {
		while (menuButton != 4) {
			try {
				ShowBookList showBookList = new ShowBookList();
				showBookListPageMenu();
				menuButton = ScannerUtil.getInputIntegerS(">> 원하시는 메뉴를 선택하세요 : ");
				
				switch (menuButton) {
					case 1: // 내가 대여중인 도서 보기 (반납기한도)
						showBookList.showMyUsingBook(member);
						break;
					case 2: // 별점랭킹 추천 조회
						showBookList.showStarBook();
						break;
					case 3: // 대여가능한 도서 조회
						showBookList.showCanUsingBook();
						break;
					case 4: // 이전 단계
						System.out.println("[!] 이전 단계로 돌아갑니다.");
						System.out.println();
						break;
					default:
						System.out.println("error : 잘못된 입력입니다.");
						break;
				}
			} catch (NumberFormatException e){
                System.out.println("error : 숫자로 입력해주세요. \n");
			}
		}
	}
}
