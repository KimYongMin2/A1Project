package bookcase;

import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.Date;

import bookcase.crud.*;
import bookcase.object.*;
import bookcase.show.*;
import bookcase.util.*;


public class BookRentPage implements Show {
	
	private  Connection con = JDBCconnecting.connecting();
	private  BookCRUD bookCrud = BookCRUD.getInstance();
	private  RentalCRUD rentalCrud = RentalCRUD.getInstance();
	private  ArrayList<Book> bookList = new ArrayList<Book>();
	
	private boolean chk = false;
	private int menuButton = 0;
	private Member member;
	private Book book;
	private int temp = 0;
	private String bName;

	public BookRentPage(Member member){
		this.member = member;
	}

	public void BookUsingStart() {
		while (menuButton != 2) {
			try {
				showBookUsingMenu();
				menuButton = ScannerUtil.getInputIntegerS(">> 원하시는 메뉴를 선택하세요. : ");
				switch (menuButton) {
				case 1: // 도서 대여 하기: 대여 가능한 도서 중에
					usingBook();
					break;
				case 2: // 종료
					System.out.println("[!] 전 단계로 돌아갑니다.");
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

	public void usingBook(){ //도서 대여하기
		chk = false; // 책확인
		bookList = bookCrud.getBookList(con); // 전체 책 테이블 가져오기
		showRentalBookPage(); //대여 페이지 입니다 라는 문구 출력
		bName = ScannerUtil.getInputStringS(">> 대여할 도서명을 입력해주세요 : ");
		findBook();
		if(chk){
			if(bookList.get(temp).getbUsing().equals("false")) {
				addUsingBook();
			} else { // bUsing = true : 누군가 사용 중
				System.out.println("[!] 이미 대여중인 책입니다.");
			}

		}
	}

	private void findBook() { // 책 리스트에서 책 이름으로 해당 책이 존재하는 지 찾기
		for(int i = 0; i < bookList.size(); i++) {
			if(bName.equals(bookList.get(i).getbName())) {
				temp = i;
				chk = true;
			}
		}
		if(!chk) {
			System.out.println("[!] 해당 도서를 찾지 못하였습니다.");
		} else { // chk = true
			book = bookList.get(temp); // 책이 존재한다면 북 객체에, 해당 책을 담아 놓음
		}
	}

	public void addUsingBook() {
		// 대여리스트에 책 리스트 추가
		// 대여 날짜 오늘로 설정
		Date today = new Date();        
		SimpleDateFormat date = new SimpleDateFormat("yyMMdd");
		String toDay = date.format(today);

		// 대여기간 1주일 기한
		Calendar week = Calendar.getInstance();
		week.add(Calendar.DATE , +7);
		String afterWeek = new java.text.SimpleDateFormat("yyMMdd").format(week.getTime());    	

		Using usingBook = new Using(0, toDay, afterWeek, member.getMemberCode(), book.getBookCode());

		book.setbUsing("true"); // 아까 담아놨던 bookList.get(temp)의 using 값을 true로 바꿈

		rentalCrud.insertRental(con, usingBook); //대여 테이블에 위의 내용 반영
		bookCrud.updateBook(con, book);
		System.out.println("[!] 대여가 완료되었습니다.");
	}
}