package bookcase.page;

import java.sql.*;
import java.util.*;

import bookcase.crud.*;
import bookcase.handler.ManagerHandler;
import bookcase.object.*;
import bookcase.show.*;
import bookcase.util.*;

public class ManagerPage implements Show {
	
    private Connection con = JDBCconnecting.connecting();
	private BookCRUD bookCrud = BookCRUD.getInstance();
	private MemberCRUD memberCrud = MemberCRUD.getInstance();
	
	private ArrayList<Book> bookList = new ArrayList<Book>();
	private ArrayList<Member> memberList = new ArrayList<Member>();
	private ArrayList<mUsingBook> mUsingBooks = new ArrayList<mUsingBook>();
	
	
	private int menuButton = 0;

    public void bookManagerStart(){
        while (menuButton != 7) {
            try {
                showBookManagerMenu();
                ManagerHandler managerHandler = new ManagerHandler();
                menuButton = ScannerUtil.getInputIntegerS(">> 원하시는 메뉴를 선택하세요 : ");
                bookList = bookCrud.getBookList(con);
                memberList = memberCrud.getMemberList(con);

                switch (menuButton) {
                    case 1: // 도서 추가
                    	managerHandler.addBook();
                        break;
                    case 2: // 도서 삭제
                    	managerHandler.deleteBook(bookList);
                        break;
                    case 3: // 도서 수정
                    	managerHandler.reBook(bookList);
                        break;
                    case 4: // 전체 첵 리스트 조회
                    	managerHandler.showAllBookList(bookList);
                        break;
                    case 5: // 대여중인 책 리스트 조회
                    	managerHandler.showmUsingBookList(mUsingBooks);
                        break;
                    case 6:	// 회원리스트 조회 
                    	managerHandler.showAllMember(memberList);
                    	break;
                    case 7: // 뒤로가기
                    	System.out.println("\n▶ 관리자 로그아웃이 완료되었습니다.");
                    	System.out.println();
                        break;
                    default:
                    	System.out.println("error : 잘못된 입력입니다.\n ");
                        break;
                }
            } catch (NumberFormatException e){
            	System.out.println("error : 숫자로 입력해주세요.\n");
            }
        }
    }
}