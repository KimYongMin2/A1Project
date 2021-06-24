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

	private boolean chk = false;
	private static Connection con = JDBCconnecting.connecting();
	private static BookCRUD bookCrud = BookCRUD.getInstance();
	private static RentalCRUD rentalCrud = RentalCRUD.getInstance();
	private static ArrayList<Book> bookList = new ArrayList<Book>();
	private int menuButton = 0;
	private Member member;
	private Book book;
	private int temp = 0;
	private int bookcode=0;
	private List<Using> usingBooks = new ArrayList<>();
	private String bName;

	public BookRentPage(Member member){
		this.member = member;
	}

	public void BookUsingStart() {
		while (menuButton != 2) {
			try {
				showBookUsingMenu();

				System.out.print(">> 원하시는 메뉴를 선택하세요. : ");
				menuButton = ScannerUtil.getInputInteger();

				switch (menuButton) {
				case 1:
					// 대여
					usingBook();
					break;
				case 2:
					// 종료
					System.out.println("[!] 전 단계로 돌아갑니다.");
					break;
				default:
					System.out.println("error : 잘못된 입력입니다.");
					break;
				}
			}catch (NumberFormatException e){
				System.out.println("error : 숫자로 입력해주세요");
			}
		}

	}


	public void usingBook(){
		// 책확인
		chk = false;
		bookList = bookCrud.getBookList(con);

		showRentalBookPage();

		System.out.println(">> 대여할 도서명을 입력해주세요 : ");
		bName = ScannerUtil.getInputString();

		findBook();
		if(!chk) {
			System.out.println("[!] 해당 도서를 찾지 못하였습니다.");
		} else { // chk = true
			book = bookList.get(temp);
			if(bookList.get(temp).getbUsing().equals("false")) {
				System.out.println("[!] 대여가 완료되었습니다.");
				addUsingBook();
			} else { // bUsing = true
				System.out.println("[!] 이미 대여중인 책입니다.");
			}

		}
	}

	private void findBook() {
		for(int i = 0; i < bookList.size(); i++) {
			if(bName.equals(bookList.get(i).getbName())) {
				temp = i;
				chk = true;
			}
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

		usingBooks.add(usingBook);
		book.setbUsing("true");

		rentalCrud.insertRental(con, usingBook);
		bookCrud.updateBook(con, book);
	}
}