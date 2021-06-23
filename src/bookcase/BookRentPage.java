package bookcase;

import bookcase.crud.BookCRUD;
import bookcase.crud.RentalCRUD;
import bookcase.object.Book;
import bookcase.object.Member;
import bookcase.object.Using;
import bookcase.show.Show;
import bookcase.util.JDBCconnecting;
import bookcase.util.ScannerUtil;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public List<Using> getUsingBooks() {
        return usingBooks;
    }

    public void setUsingBooks(List<Using> usingBooks) {
        usingBooks = usingBooks;
    }

    public BookRentPage(Member member){
        this.member = member;
    }

    public void BookUsingStart() {
    	while (menuButton != 2) {
    	    try {
                showBookUsingMenu();

                System.out.print(" 메뉴를 선택해주세요 : ");
                menuButton = ScannerUtil.getInputInteger();

                switch (menuButton) {
                    case 1:
                        // 대여
                        findBook();
                        break;
                    case 2:
                        // 종료
                        break;
                    default:
                        System.out.println("잘못 입력하셨습니다");
                        break;
                }
            }catch (NumberFormatException e){
                System.out.println("숫자로 입력해주세요");
            }
        }
        
    }


    public void findBook(){
        // 책확인
    	chk = false;
        bookList = bookCrud.getBookList(con);

        showRentalBookPage();

        System.out.println("대여하려는 책 이름을 작성해주세요 : ");
        bName = ScannerUtil.getInputString();
        	
        for(int i = 0; i < bookList.size(); i++) {
            if(bName.equals(bookList.get(i).getbName())) {
                temp = i;
	            chk = true;
                book = bookList.get(temp);
            }
        }
        if(!chk) {
        	System.out.println("원하시는 책을 찾지 못했습니다.");
        }
        else { // chk = true
        	if(bookList.get(temp).getbUsing().equals("false")) {
        		System.out.println("대여가 완료되었습니다.");
        	    addUsingBook();
        	}
        	else { // bUsing = true
        		System.out.println("이미 대여중인 책입니다.");
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