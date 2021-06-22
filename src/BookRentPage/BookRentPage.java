package BookRentPage;

import bookcase.Book;
import bookcase.Member;
import bookcase.Using;
import bookcase.manager.BookManager;

import java.util.List;
import java.util.Scanner;

public class BookRentPage {
    static Scanner scanner = new Scanner(System.in);
    private int menuButton = 0;
    private Member member;
    private Book book;
    private List<Book> books;
    private int temp = 0;
    private int bookcode=0;
    private List<Using> UsingBooks;

    public List<Using> getUsingBooks() {
        return UsingBooks;
    }

    public void setUsingBooks(List<Using> usingBooks) {
        UsingBooks = usingBooks;
    }

    public BookRentPage(Member member){
        this.member = member;
    }

    public void BookUsingStart() {
        findBook();
        addUsingBook();
    }
    public void findBook(){
        // 책확인
    	boolean chk = false;
    	books = new BookManager().getBookList();
    	while(chk) {
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
        	}
        	else {
        		if(!books.get(temp).isbUsing()) {
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
    	Using usingBook = new Using(book.getBookCode(), member.getMemberCode(), "20210101", "20210622", 0);
    	
    	UsingBooks.add(usingBook);
    	book.setbUsing(true);
    }
}