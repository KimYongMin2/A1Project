package bookcase;

import java.util.ArrayList;

import bookcase.crud.BookCRUD;
import bookcase.crud.RentalCRUD;
import bookcase.object.Book;
import bookcase.object.Member;
import bookcase.object.mUsingBook;
import bookcase.show.Show;
import bookcase.util.Common;
import bookcase.util.ScannerUtil;

public class ManagerHandler extends Common implements Show {

	private BookCRUD bookCrud = BookCRUD.getInstance();
	private RentalCRUD rentalCrud = RentalCRUD.getInstance();
	private int chkAge;

	
	
	public void addBook(){
		bName = ScannerUtil.getInputStringS("▶ 도서명 : ");
		if (checkSameBookName()) return;
		bWriter = ScannerUtil.getInputStringS("▶ 저 자 : ");
		bPublisher = ScannerUtil.getInputStringS("▶ 출판사 : ");
		bGenre = ScannerUtil.getInputStringS("▶ 장 르 : ");
		bPrice = ScannerUtil.getInputIntegerS("▶ 가 격 : ");
		String bUsing = "false";
		System.out.println("▶ 연령제한 여부 : ");
		chkAge = ScannerUtil.getInputIntegerS("(1) 네 (2) 아니오 : ");
		setAgeUsing();
		bookCrud.insertBook(con, new Book(0, bName, bWriter, bPublisher,
				bGenre, bPrice, bUsing, bAgeUsing));
		showAddBookSuccess();

	}

	private boolean checkSameBookName() {
		for (int i = 0; i < bookList.size(); i++) {
			if (bName.equals(bookList.get(i).getbName())){
				System.out.println("[i] 이미 존재하는 도서입니다");
				return true;
			}
		}
		return false;
	}

	private void setAgeUsing() {
		if(chkAge == 1) {
			bAgeUsing = "true";
		} else if(chkAge == 2) {
			bAgeUsing = "false";
		} else {
			System.out.println("error : 잘못된 입력입니다.");
		}
	}

	public void deleteBook(ArrayList<Book> bookList) {	
		bookFindChk = false;
		bName = ScannerUtil.getInputStringS(">> 삭제할 도서명을 입력해주세요 : ");
		if(bookList == null) {
			System.out.println("[!] 현재 삭제할 도서가 존재하지 않습니다.");
		} else {
			book = findBook(bookList, bName);
			bookFindChk = setFindBookCheck(book);
			if(bookFindChk) {
				bookCrud.deleteBook(con, book);
				System.out.println("▶ 도서가 삭제되었습니다.\n");
			} else {
				System.out.println("[!] 해당하는 도서를 찾지 못했습니다.");
			}
		}
	}


	public void reBook(ArrayList<Book> bookList){
		bookFindChk = false;

		bName = ScannerUtil.getInputStringS(">> 수정하실 도서명을 입력하세요. : ");

		book = findBook(bookList, bName);
		bookFindChk = setFindBookCheck(book);

		if (bookFindChk) {
			showReBookMenu();
			menuButton = ScannerUtil.getInputIntegerS(">> 수정하실 데이터를 선택하세요 : ");

			switch (menuButton) {
			case 1:
				bName = ScannerUtil.getInputStringS("▶ 도서명 : ");
				if (checkSameBookName()) return;
				book.setbName(bName);
				break;
			case 2:
				bWriter = ScannerUtil.getInputStringS("▶ 저 자 : ");
				book.setbWriter(bWriter);
				break;
			case 3:
				bPublisher = ScannerUtil.getInputStringS("▶ 출판사 : ");
				book.setbPublisher(bPublisher);
				break;
			case 4:
				bGenre = ScannerUtil.getInputStringS("▶ 장 르 : ");
				book.setbGenre(bGenre);
				break;
			case 5:
				bPrice = ScannerUtil.getInputIntegerS("▶ 가 격 : ");
				book.setbPrice(bPrice);
				break;
			case 6:
				menuButton = 0;
				System.out.println("▶ 연령제한 여부");
				chkAge = ScannerUtil.getInputIntegerS("(1) 네 (2) 아니오 : ");
				setAgeUsing();
				book.setbAgeUsing(bAgeUsing);
				break;
			default:
				System.out.println("error : 잘못된 입력입니다.");
				break;
			}

			bookCrud.updateBook(con, book);
			System.out.println("▶ 수정이 완료되었습니다.\n");

		} else {
			System.out.println("[!] 수정할 데이터가 존재하지 않습니다.");
		}
	}

	public void showAllBookList(ArrayList<Book> bookList){
		System.out.println("\n>> 전체 도서 리스트를 조회합니다.");
		System.out.println("■■■■■■■■■■■ 도서 정보 ■■■■■■■■■■■");
		for (Book book : bookList) {
			System.out.println(book);
		}
		System.out.println();
	}
	

	public void showmUsingBookList(ArrayList<mUsingBook> mUsingBooks) {
		mUsingBooks = rentalCrud.mUsingBooks(con);
		System.out.println("\n>> 현재 대여 상태인 도서 리스트를 조회합니다.");
		System.out.println("■■■■■■■■■■■ 대여 도서 목록 ■■■■■■■■■■■");
		if(mUsingBooks.size() > 0) {
			for (int i = 0; i < mUsingBooks.size(); i++) {
				System.out.println(mUsingBooks.get(i));
			} 
		} else {
			System.out.println("[!] 대여 상태인 도서 존재하지 않습니다.");
		}
		System.out.println();
	}
//		for (BUsingbook busingBook : BUsingBook) {
//			if(book.getbUsing().equals("true")) {
//				System.out.println(book);
//			}
//		}
//		System.out.println();
//	}

	public void showAllMember(ArrayList<Member> members) {
		System.out.println("\n>> 전체 회원 리스트를 출력합니다.");
		System.out.println("■■■■■■■■■■■ 회원 리스트 ■■■■■■■■■■■");
		for(Member member: members) {
			if(member.getId().equals("admin")) { // 관리자 제외
				continue;
			}
			System.out.println(member);
		}
	System.out.println();
	}
	
}

