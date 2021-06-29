package bookcase.util;

import java.sql.*;
import java.util.*;
import bookcase.object.*;

public class Common {

	protected static Connection con = JDBCconnecting.connecting();
	protected static List<Book> bookList = new ArrayList<>();
	protected int menuButton = 0;
	protected Member member;
	protected Book book;
	protected boolean bookFindChk = false;
	protected boolean checkUsingBook;
	protected int bookCode, bPrice;
	protected String bName, bWriter, bPublisher, bGenre, bAgeUsing;

	protected boolean setFindBookCheck(Book book) {
		bookFindChk = false;
		if(book == null) {
			bookFindChk = false;
			System.out.println("[!] 해당 도서를 찾지 못하였습니다.");
		}else {
			bookFindChk = true;
		}
		return bookFindChk;
	}

	protected boolean setCheckUsingBook(Book book) {
		if(bookFindChk){
			if(book.getbUsing().equals("false")) {
				checkUsingBook = false;
			} else {
				checkUsingBook = true;
			}
		}
		return checkUsingBook;
	}
}
