package bookcase.page;

import bookcase.*;
import bookcase.object.*;
import bookcase.show.*;
import bookcase.util.*;

public class ReviewAddPage implements Show {
	
	private int menuButton;
	private Member member;
	
	public ReviewAddPage(Member member) {
		this.member = member;
		menuButton = 0;
	}

	public void reviewAddStart() {
		while (menuButton != 4) {
			try {
				ReViewAdd reViewAdd = new ReViewAdd(member);
				showReviewAddMenu();
				menuButton = ScannerUtil.getInputIntegerS(">> 원하시는 메뉴를 선택하세요 : ");
				switch (menuButton) {
				case 1: // 리뷰 작성
					reViewAdd.addingReview();
					break;
				case 2: // 리뷰 조회
					reViewAdd.showReview();
					break;
				case 3: // 도서 목록
					reViewAdd.showBookList();
					break;
				case 4: // 이전 단계
					System.out.println("[!] 이전 단게로 돌아갑니다.");
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
