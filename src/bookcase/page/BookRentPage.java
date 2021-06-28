package bookcase.page;

import bookcase.handler.BookRent;
import bookcase.object.*;
import bookcase.show.*;
import bookcase.util.*;


public class BookRentPage extends Common implements Show {
	
	private Member member;

	public BookRentPage(Member member){
		this.member = member;
	}

	public void BookUsingStart() {
		while (menuButton != 2) {
			try {
				BookRent bookRent = new BookRent(member);
				showBookUsingMenu();
				menuButton = ScannerUtil.getInputIntegerS(">> 원하시는 메뉴를 선택하세요. : ");
				switch (menuButton) {
				case 1: // 도서 대여 하기: 대여 가능한 도서 중에
					bookRent.usingBook();
					break;
				case 2: // 이전 단계
					System.out.println("[!] 이전 단계로 돌아갑니다.");
					System.out.println();
					break;
				default:
					System.out.println("error : 잘못된 입력입니다.");
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("error : 숫자로 입력해주세요");
			}
		}
	}
}