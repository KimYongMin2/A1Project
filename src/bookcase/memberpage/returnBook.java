package bookcase.memberpage;

import BookRentPage.BookRentPage;
import bookcase.Book;
import bookcase.Member;
import bookcase.Using;
import bookcase.manager.BookManager;

import java.util.List;
import java.util.Scanner;

public class returnBook {
    static Scanner scanner = new Scanner(System.in);
    private int menuButton = 0;
    private Member member;
    private Book book;
    private List<Using> usingBooks;
    private int temp;

    public returnBook(Member member){
        this.member = member;
    }

    public void returnBookStart(){
        // 책찾기

    }
}
