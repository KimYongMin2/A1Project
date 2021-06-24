package bookcase;

import java.sql.*;
import java.util.*;

import bookcase.crud.BookCRUD;
import bookcase.object.Book;
import bookcase.show.Show;
import bookcase.util.CommonFunction;
import bookcase.util.JDBCconnecting;
import bookcase.util.ScannerUtil;

public class ManagerPage implements Show {
	
    private static Connection con = JDBCconnecting.connecting();
	private static BookCRUD bookCrud = BookCRUD.getInstance();
	private static ArrayList<Book> bookList = new ArrayList<Book>();
	
    private String bName, bWriter, bPublisher, bGenre;
    private int bPrice;
    private String bAgeUsing;
    private int temp;
    private int menuButton = 0;
    private boolean findCheck = false;

    public void bookManagerStart(){
        while (menuButton != 6) {
            try {
                showBookManagerMenu();
                menuButton = CommonFunction.setMenuButton(">> 원하시는 메뉴를 선택하세요 : ", menuButton);
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
                    	System.out.println("[!] 전 단계로 돌아갑니다.");
                        break;
                    default:
                    	System.out.println("error : 잘못된 입력입니다.");
                        break;
                }
            } catch (NumberFormatException e){
            	System.out.println("error : 숫자로 입력해주세요.");
            }
        }
    }

    public void addBook(){
        System.out.print("▶ 도서명 : ");
        bName = ScannerUtil.getInputString();
        for (int i = 0; i < bookList.size(); i++) {
            if (bName.equals(bookList.get(i).getbName())){
                System.out.println("이미 존재하는 도서입니다");
                return;
            }
        }

        System.out.print("▶ 저 자 : ");
        bWriter = ScannerUtil.getInputString();

        System.out.print("▶ 출판사 : ");
        bPublisher = ScannerUtil.getInputString();

        System.out.print("▶ 장 르 : ");
        bGenre = ScannerUtil.getInputString();

        System.out.print("▶ 가 격 : ");
        bPrice = ScannerUtil.getInputInteger();

        String bUsing = "false";

        System.out.println("▶ 연령제한 여부 : ");
        System.out.print("(1) 네 (2) 아니오 : ");
        int chks = ScannerUtil.getInputInteger();

        if(chks == 1) {
        	bAgeUsing = "true";
        } else if(chks == 2) {
        	bAgeUsing = "false";
        } else {
        	System.out.println("error : 잘못된 입력입니다.");
        }

        /***
         * 북테이블 DB와 연결!, 북 테이블에 INSERT시키기
         * @author 민주
         */
		bookCrud.insertBook(con, new Book(0, bName, bWriter, bPublisher,
                bGenre, bPrice, bUsing, bAgeUsing));
		System.out.println("================================");
		System.out.println("▶ 새로운 도서를 추가하였습니다.\n");

    }

    public void deleteBook(ArrayList<Book> bookList) {	
        findCheck = false;
        
        System.out.print(">> 삭제할 도서명을 입력해주세요 : ");
        String bookName = ScannerUtil.getInputString();
        
        if(bookList != null) {
            findBook(bookList, bookName);
            if(findCheck){
                bookCrud.deleteBook(con, bookList.get(temp));
                System.out.println("▶ 도서가 삭제되었습니다.\n");
            }else{
	        	System.out.println("[!] 해당하는 도서를 찾지 못했습니다.");
	        }
        } else {
        	System.out.println("[!] 현재 삭제할 도서가 존재하지 않습니다.");
        }
    }


    public void reBook(ArrayList<Book> bookList){

        findCheck = false;
        
        System.out.println(">> 수정하실 도서명을 입력하세요. : ");
        String bName = ScannerUtil.getInputString();

        findBook(bookList, bName);
        if (findCheck){
            showReBookMenu();
            menuButton = CommonFunction.setMenuButton(">> 수정하실 데이터를 선택하세요 : ", menuButton);

            switch (menuButton) {
                case 1:
                	System.out.print("▶ 도서명 : "); // 도서명 중복 금지
                    bName = ScannerUtil.getInputString();
                    for (int i = 0; i < bookList.size(); i++) {
                        if (bName.equals(bookList.get(i).getbName())){
                            System.out.println("이미 존재하는 도서의 이름입니다");
                            break;
                        }
                    }
                    bookList.get(temp).setbName(bName);
                    break;
                case 2:
                	System.out.print("▶ 저 자 : ");
                    bWriter = ScannerUtil.getInputString();
                    bookList.get(temp).setbWriter(bWriter);
                    break;
                case 3:
                	System.out.print("▶ 출판사 : ");
                    bPublisher = ScannerUtil.getInputString();
                    bookList.get(temp).setbPublisher(bPublisher);
                    break;
                case 4:
                	System.out.print("▶ 장 르 : ");
                    bGenre = ScannerUtil.getInputString();
                    bookList.get(temp).setbGenre(bGenre);
                    break;
                case 5:
                	System.out.print("▶ 가 격 : ");
                    bPrice = ScannerUtil.getInputInteger();
                    bookList.get(temp).setbPrice(bPrice);
                    break;
                case 6: 
                	System.out.println("▶ 연령제한 여부");
                    System.out.println("(1) true (2) false : ");
                    int chks = ScannerUtil.getInputInteger();

                    if(chks == 1) {
                    	bAgeUsing = "true";
                    } else if(chks == 2) {
                    	bAgeUsing = "false";
                    }
                    menuButton = 0;
                    break;
                default:
                	System.out.println("error : 잘못된 입력입니다.");
                    break;
            }
            
            /**
             * DB랑 연결
             * @author 민주
             */
            bookCrud.updateBook(con, bookList.get(temp)); /*오류*/
//            System.out.println("▶ 수정이 완료되었습니다.\n");
            
        } else {
            System.out.println("[!] 수정할 데이터가 존재하지 않습니다.");
        }
    }



    private void findBook(ArrayList<Book> bookList, String bookName) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookName.equals(bookList.get(i).getbName())) {
                temp = i;
                findCheck = true;
            }
        }
    }

    public void showAllBookList(ArrayList<Book> bookList){
        System.out.println(">> 도서 리스트를 출력합니다.");
        for (Book book : bookList) {
            System.out.println(book);
            System.out.println();
        }
    }
    /**
     * 전체 책 리스트 조회
		아무것도 없으면 리스트가 없습니다라고 뜨게 수정하기 
     */

    public void showUsingBookList() {
    	System.out.println(">> 현재 대여 상태인 도서 리스트를 출력합니다.");
        for (Book book : bookList) {
            if(book.getbUsing().equals("true")) {
              System.out.println(book);
              System.out.println();
              // System.out.println(book.getBookCode() + book.getbName());

            }
        }
    }
}

//    public ArrayList<Book> getBookList() {
//        return bookList;
//    }
