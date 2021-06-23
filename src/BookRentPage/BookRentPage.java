package BookRentPage;

import bookcase.Book;
import bookcase.Member;
import bookcase.Using;
import bookcase.manager.BookManager;
import bookcase.show.Show;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BookRentPage implements Show {
    static Scanner scanner = new Scanner(System.in);
    private int menuButton = 0;
    private Member member;
    private Book book;
    private List<Book> books = new ArrayList<>();
    private int temp = 0;
    private int bookcode=0;
    private List<Using> usingBooks = new ArrayList<>();

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
            showBookUsingMenu();

            System.out.print(" 메뉴를 선택해주세요 : ");
            menuButton = Integer.parseInt(scanner.nextLine());

            switch (menuButton) {
                case 1:
                    // 대여
                	findBook();
                    addUsingBook();
                    break;
                case 2:
                    // 종료
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다");
                    break;
            }
        }
        
    }


    public void findBook(){
        // 책확인
    	boolean chk = false;
    	books = new BookManager().getBookList();
    	while(!chk) {
			System.out.println("============= 대여 페이지 입니다 ============");
			System.out.println("대여하려는 책 이름을 작성해주세요 : ");
        	String bName = scanner.nextLine();
        	
        	for(int i = 0; i < books.size(); i++) {
	            if(bName.equals(books.get(i).getbName())) {
	                temp = i;
	                chk = true;
	            }
	            
	        }		 
        	if(!chk) {
        		System.out.println("원하시는 책을 찾지 못했습니다.");
        		break;
        	}
        	else {
        		if(books.get(temp).getbUsing().equals("true")) {
        			book = books.get(temp);
        		}
        		else {
        			System.out.println("이미 대여중인 책입니다.");
        		}
        		
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
        
        
    	Using usingBook = new Using(book.getBookCode(), member.getMemberCode(), toDay, afterWeek, 0);
    	
    	usingBooks.add(usingBook);
    	book.setbUsing("true");
    }
}