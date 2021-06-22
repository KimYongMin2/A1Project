package bookcase.manager;

import java.sql.*;
import java.util.*;

import bookcase.*;

public class BookManager {
	
    private static Connection con = JDBCconnecting.connecting();
	private static BookCRUD bookCrud = BookCRUD.getInstance();
	private static ArrayList<Book> bookList = new ArrayList<Book>();
	
    private String bName, bWriter, bPublisher, bGenre;
    private int bPrice;
    private String bAgeUsing;
    private int temp;
    private int menuButton = 0;
    private boolean findCheck = false;
    private static Scanner scanner = new Scanner(System.in);

    public void bookManagerStart(){
        while (menuButton != 6) {
            System.out.println("--------------------------------------------");
            System.out.println("1. 도서 추가하기 ");
            System.out.println("2. 도서 삭제하기 ");
            System.out.println("3. 도서 수정하기 ");
            System.out.println("4. 전체 책 리스트 조회 ");
            System.out.println("5. 대여중인 책 리스트 조회 ");
            System.out.println("6. 종료");
            System.out.println("--------------------------------------------");

            System.out.print(" 메뉴를 선택해주세요 : ");
            menuButton = Integer.parseInt(scanner.nextLine());
            bookList = bookCrud.getBookList(con);

            switch (menuButton) {
                case 1:
                    // 추가
                    addBook();
                    break;
                case 2:
                    // 삭제
                    deleteBook(bookList);
                    break;
                case 3:
                    // 수정
                    reBook(bookList);
                    break;
                case 4:
                    // 전체 첵 리스트 조회
                    showAllBookList(bookList);
                    break;
                case 5:
                    // 대여중인 책 리스트 조회
                    showUsingBookList();
                    break;
                case 6:
                    System.out.println("종료합니다");
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다");
                    break;
            }
        }
    }


    public void addBook(){
        System.out.print("책 이름을 입력해주세요 : ");
        bName = scanner.nextLine();

        System.out.print("지은이를 입력해주세요 : ");
        bWriter = scanner.nextLine();

        System.out.print("출판사를 입력해주세요 : ");
        bPublisher = scanner.nextLine();

        System.out.print("장르를 입력해주세요 : ");
        bGenre = scanner.nextLine();

        System.out.print("가격을 입력해주세요 : ");
        bPrice = Integer.parseInt(scanner.nextLine());

        String bUsing = "false";

        System.out.println("연령제한이 있습니까?");
        System.out.print("(1) 네 (2) 아니오");
        int chks = Integer.parseInt(scanner.nextLine());
        
        if(chks == 1) {
        	bAgeUsing = "true";
        } else if(chks == 2) {
        	bAgeUsing = "false";
        } else {
        	System.out.println("잘못 입력하셨습니다.");
        }
        
        /***
         * 북테이블 DB와 연결!, 북 테이블에 INSERT시키기
         * @author 민주
         */
		bookCrud.insertBook(con, new Book(0, bName, bWriter, bPublisher,
                bGenre, bPrice, bUsing, bAgeUsing));
		System.out.println("=== 새로운 책을 추가했습니다. ===");

    }

    public void deleteBook(ArrayList<Book> bookList) {	
        findCheck = false;
        
        System.out.print("삭제할 도서명을 입력해주세요 : ");
        String bookName = scanner.nextLine();
        
        if(bookList != null) {
	        for (int i = 0; i < bookList.size(); i++) {
	            if(bookName.equals( bookList.get(i).getbName())){
	                bookCrud.deleteBook(con, bookList.get(i));
	                System.out.println("삭제되었습니다");
	                findCheck = true;
	            }
	        }
	        
	        if(!findCheck) {
	        	System.out.println("해당하는 도서를 찾지 못했습니다.");
	        }
        } else {
        	System.out.println("아직 책이 한권도 없습니다.");
        }
    }

    public void reBook(ArrayList<Book> bookList){

        findCheck = false;
        
        System.out.println("수정할 도서코드 입력해주세요 : ");
        int bookCode = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < bookList.size(); i++) {
            if(bookCode == bookList.get(i).getBookCode()){
                temp = i;
                findCheck = true;
            }
        }
        
        if (findCheck){
            System.out.println();
            System.out.println("--------------------------------------------");
            System.out.println("1. 책이름     2. 지은이     3. 출판사");
            System.out.println("4. 장르       5. 가격      6. 연령제한 여부");
            System.out.println("--------------------------------------------");
            System.out.println("수정할 목록을 설정해주세요 : ");
            menuButton = Integer.parseInt(scanner.nextLine());

            switch (menuButton) {
                case 1:
                    System.out.print("책 이름을 입력하세요 : ");
                    bName = scanner.nextLine();
                    bookList.get(temp).setbName(bName);
                    break;
                case 2:
                    System.out.print("지은이를 입력하세요 : ");
                    bWriter = scanner.nextLine();
                    bookList.get(temp).setbWriter(bWriter);
                    break;
                case 3:
                    System.out.print("출판사를 입력하세요 : ");
                    bPublisher = scanner.nextLine();
                    bookList.get(temp).setbPublisher(bPublisher);
                    break;
                case 4:
                    System.out.print("장르를 입력하세요 : ");
                    bGenre = scanner.nextLine();
                    bookList.get(temp).setbGenre(bGenre);
                    break;
                case 5:
                    System.out.print("가격을 입력하세요 : ");
                    bPrice = Integer.parseInt(scanner.nextLine());
                    bookList.get(temp).setbPrice(bPrice);
                    break;
                case 6:
                    System.out.print("연령제한 여부를 입력하세요 : ");
                    bAgeUsing = scanner.nextLine();
                    bookList.get(temp).setbAgeUsing(bAgeUsing);
                    break;
                default:
                    System.out.print("잘못입력하셨습니다");
                    break;
            }
            
            /**
             * DB랑 연결
             * @author 민주
             */
            bookCrud.updateBook(con, bookList.get(temp)); /*오류*/
            System.out.println("수정 완료되었습니다!");
            
        } else {
            System.out.println("파일을 찾지 못하였습니다.");
        }
    }

    public void showAllBookList(ArrayList<Book> bookList){
        System.out.println("전체 리스트입니다");
        for (Book book : bookList) {
            System.out.println(book);
        }
    }
    /**
     * 전체 책 리스트 조회
		아무것도 없으면 리스트가 없습니다라고 뜨게 수정하기 
     */

    public void showUsingBookList(){
        System.out.println("대여중인 리스트입니다");
        for (Book book : bookList) {
            if(book.getbUsing().equals("true")){
                System.out.println(book);
                System.out.println(book.getBookCode() + book.getbName());
            }
        }
    }
    public ArrayList<Book> getBookList() {
        return bookList;
    }

}
