package bookcase;

import java.sql.*;
import java.util.*;

import bookcase.crud.BookCRUD;
import bookcase.crud.RentalCRUD;
import bookcase.object.Book;
import bookcase.object.Member;
import bookcase.object.Using;
import bookcase.show.*;
import bookcase.util.CommonFunction;
import bookcase.util.JDBCconnecting;
import bookcase.util.ScannerUtil;

public class ReturnBookPage implements Show {
    private boolean chk = false;
    
    private static BookCRUD bookCrud = BookCRUD.getInstance();
    private static Connection con = JDBCconnecting.connecting();
    private static RentalCRUD rentalCrud = RentalCRUD.getInstance();
    
    private ArrayList<Book> bookList = new ArrayList<Book>();
    private ArrayList<Using> usingBooks = new ArrayList<>();
   
    private int menuButton = 0;
    private Member member;
    private Book book;
    private int temp = 0;
    private int bookcode=0;

    private String bName;

    public ArrayList<Using> getUsingBooks() {
        return usingBooks;
    }

    public void setUsingBooks(List<Using> usingBooks) {
        usingBooks = usingBooks;
    }

    public ReturnBookPage(Member member){
        this.member = member;
    }

    public void BookReturnStart() {
        while (menuButton != 2) {
            try {
                showBookReturnMenu();
                menuButton = CommonFunction.setMenuButton("해당사항을 선택해주세요", menuButton);

                switch (menuButton) {
                    case 1:
                        // 반납
                        returnBook();
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


    public void returnBook(){
        // 책확인
        chk = false;
        bookList = rentalCrud.getMyRentalList(con, member);
        showReturnBookPage();

        System.out.println("반납하려는 책 이름을 작성해주세요 : ");
        bName = ScannerUtil.getInputString();

        findBook();

        if(!chk) {
            System.out.println("원하시는 책을 찾지 못했습니다.");
        }
        else { // chk = true
            book = bookList.get(temp);
            if(book.getbUsing().equals("true")) {
                deleteUsingBook();
                System.out.println("반납이 완료되었습니다.");
            }else{
                System.out.println("반납 불가능한 책입니다");
            }
        }
    }

    private void findBook() {
        for(int i = 0; i < bookList.size(); i++) {
            if(bName.equals(bookList.get(i).getbName())) {
                temp = i;
                chk = true;
            }
        }
    }

    public void deleteUsingBook() {
        book.setbUsing("false");
        rentalCrud.ReturnMyBook(con, member);
        bookCrud.updateBook(con, book);
    }
}