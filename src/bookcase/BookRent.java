package bookcase;

import java.text.*;
import java.util.*;

import bookcase.crud.*;
import bookcase.object.*;
import bookcase.show.*;
import bookcase.util.*;


public class BookRent extends Common implements Show {
	
	private BookCRUD bookCrud = BookCRUD.getInstance();
	private RentalCRUD rentalCrud = RentalCRUD.getInstance();

	public BookRent(Member member){
		this.member = member;
	}

	public void usingBook(){ //도서 대여하기
		bookList = bookCrud.getBookList(con); // 전체 책 테이블 가져오기
		bookFindChk = false;
		
		showRentalBookPage(); //대여 페이지 입니다 라는 문구 출력
		
		bName = ScannerUtil.getInputStringS(">> 대여할 도서명을 입력해주세요 : ");
		
		book = bookCrud.findBook(con, bName);
		bookFindChk = setFindBookCheck(book);
		if(bookFindChk){
			checkUsingbook = setCheckUsingBook(book);
			if(!checkUsingbook) {
				addUsingBook();
			} else { // bUsing = true : 누군가 사용 중
				System.out.println("[!] 이미 대여중인 책입니다.");
			}
		}
	}

	
	public void addUsingBook() {
		// 대여리스트에 책 리스트 추가
		// 대여 날짜 오늘로 설정
		
		/***
		 * @author 지원
		 * SQL쿼리문 수정
		 */

		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();

		Date date = cal.getTime();
		String toDay = sdf.format(date);
		
		// 대여기간 1주일 기한
		cal.add(Calendar.DATE, 7);
		String afterWeek = sdf.format(cal.getTime());
		
		
		Using usingBook = new Using(0, toDay, afterWeek, member.getMemberCode(), book.getBookCode());

		book.setbUsing("true"); // 아까 담아놨던 bookList.get(temp)의 using 값을 true로 바꿈

		rentalCrud.insertRental(con, usingBook); //대여 테이블에 위의 내용 반영
		bookCrud.updateBook(con, book);
		System.out.println("▶ 대여가 완료되었습니다.\n");
		
	}
}
