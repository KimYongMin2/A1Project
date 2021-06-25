package bookcase;

import java.sql.*;
import java.util.*;

import bookcase.crud.*;
import bookcase.object.*;
import bookcase.show.*;
import bookcase.util.*;

public class ManagerPage implements Show {
	
    private static Connection con = JDBCconnecting.connecting();
	private static BookCRUD bookCrud = BookCRUD.getInstance();
	private static MemberCRUD memberCrud = MemberCRUD.getInstance();
	private ArrayList<Book> bookList = new ArrayList<Book>();
	private ArrayList<Member> memberList = new ArrayList<Member>();
	
    private String bName, bWriter, bPublisher, bGenre;
    private int bPrice;
    private String bAgeUsing;
    private int temp;
    private int menuButton = 0;
    private boolean findCheck = false;
    private int chkAge;

    public void bookManagerStart(){
        while (menuButton != 6) {
            try {
                showBookManagerMenu();
                menuButton = ScannerUtil.getInputIntegerS(">> 원하시는 메뉴를 선택하세요 : ");
                bookList = bookCrud.getBookList(con);
                memberList = memberCrud.getMemberList(con);

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
                    	// 회원리스트 조회 
                    	showAllMember(memberList);
                    	break;
                    case 7:
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
                System.out.println("이미 존재하는 도서입니다");
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
        findCheck = false;

        bName = ScannerUtil.getInputStringS(">> 삭제할 도서명을 입력해주세요 : ");
        
        if(bookList != null) {
            findBook();
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
        
        bName = ScannerUtil.getInputStringS(">> 수정하실 도서명을 입력하세요. : ");
        findBook();

        if (findCheck){
            showReBookMenu();
            menuButton = ScannerUtil.getInputIntegerS(">> 수정하실 데이터를 선택하세요 : ");

            switch (menuButton) {
                case 1:
                    bName = ScannerUtil.getInputStringS("▶ 도서명 : ");
                    if (checkSameBookName()) return;
                    bookList.get(temp).setbName(bName);
                    break;
                case 2:
                    bWriter = ScannerUtil.getInputStringS("▶ 저 자 : ");
                    bookList.get(temp).setbWriter(bWriter);
                    break;
                case 3:
                    bPublisher = ScannerUtil.getInputStringS("▶ 출판사 : ");
                    bookList.get(temp).setbPublisher(bPublisher);
                    break;
                case 4:
                    bGenre = ScannerUtil.getInputStringS("▶ 장 르 : ");
                    bookList.get(temp).setbGenre(bGenre);
                    break;
                case 5:
                    bPrice = ScannerUtil.getInputIntegerS("▶ 가 격 : ");
                    bookList.get(temp).setbPrice(bPrice);
                    break;
                case 6:
                    menuButton = 0;
                	System.out.println("▶ 연령제한 여부");
                    chkAge = ScannerUtil.getInputIntegerS("(1) 네 (2) 아니오 : ");
                    setAgeUsing();
                    bookList.get(temp).setbAgeUsing(bAgeUsing);
                    break;
                default:
                	System.out.println("error : 잘못된 입력입니다.");
                    break;
            }
            
            bookCrud.updateBook(con, bookList.get(temp));
            System.out.println("▶ 수정이 완료되었습니다.\n");
            
        } else {
            System.out.println("[!] 수정할 데이터가 존재하지 않습니다.");
        }
    }



    private void findBook() {
        for (int i = 0; i < bookList.size(); i++) {
            if (bName.equals(bookList.get(i).getbName())) {
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
    
    public void showAllMember(ArrayList<Member> members) {
    	System.out.println(">>전체 회원 리스트를 출력합니다.");
    	for(Member member: members) {
    		if(member.getId().equals("admin")) { // 관리자 제외
    			continue;
    		}
    		System.out.println(member);
    		System.out.println();
    	}
    }
}


