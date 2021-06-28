package bookcase;

import java.util.*;

import bookcase.crud.*;
import bookcase.object.*;
import bookcase.show.*;
import bookcase.util.*;

public class ReturnBook extends Common implements Show {

	private RentalCRUD rentalCrud = RentalCRUD.getInstance();
	private BookCRUD bookCrud = BookCRUD.getInstance();
	private ArrayList<Using> usingBooks = new ArrayList<>();

	private Using use;
	private int temp = 0;

	public ReturnBook(Member member){
		this.member = member;
	}

	public void returnBook() {
		bookFindChk = false;
		bookList = rentalCrud.getMyRentalList(con, member);
		usingBooks = rentalCrud.getRentalTable(con);
//		showReturnBookPage();

		System.out.print(">> 반납하실 도서명을 입력하세요 : ");
		bName = ScannerUtil.getInputString();

		book = bookCrud.findBook(con, bName);
		bookFindChk = setFindBookCheck(book);
		findBookCode();

		if(!bookFindChk) {
			System.out.println("[!] 반납실패. 다시 확인해주세요.\n");
		} else { // chk = true
			use = usingBooks.get(temp);
			checkUsingbook = setCheckUsingBook(book);
			if(checkUsingbook) {
				deleteUsingBook();
				System.out.println("▶ 반납이 완료되었습니다.\n");
			}else{
				System.out.println("[!] 반납실패. 다시 확인해주세요.\n");
			}
		}
	}
	
	private void findBookCode() {
		if(bookFindChk) {
			for (int i = 0; i < usingBooks.size(); i++) {
				if (book.getBookCode() == usingBooks.get(i).getBookCode()) {
					temp = i;
					bookFindChk = true;
					break;
				} else {
					bookFindChk = false;
				}
			}
		}
	}
	
	public void deleteUsingBook() {
		book.setbUsing("false");
		rentalCrud.ReturnMyBook(con, use);
		bookCrud.updateBook(con, book);
	}
}