package bookcase.memberpage;

import BookRentPage.BookRentPage;
import bookcase.Book;
import bookcase.Member;
import bookcase.Using;
import bookcase.manager.BookManager;

import java.util.List;
import java.util.Scanner;

public class ReturnBook {
    static Scanner scanner = new Scanner(System.in);
    private int menuButton = 0;
    private Member member;
    private Book book;
    private List<Using> usingBooks;
    private List<Book> books;
    private int temp;

    public ReturnBook(Member member){
        this.member = member;
    }

    public void returnBookStart(){
        // 책찾기

    }

    public void findBook(){
        // 책확인
        boolean check = false;
        while (!check)
        System.out.print("반납하려는 책 이름을 작성해주세요 : ");
        String bName = scanner.nextLine();
        books = new BookManager().getBookList();

        for(int i =0; i <books.size(); i++) {
            if(bName.equals(books.get(i).getbName())) {
                temp = i;
                check = true;
            }
        }
        if (!check){
            System.out.println("찾지못하였습니다.");
        }else {
            book = books.get(temp);
        }
    }

    public void returnBook(){
        int bCode = book.getBookCode();
        int memberCode = member.getMemberCode();
        usingBooks = new BookRentPage(member).getUsingBooks();

        for (int i = 0; i < usingBooks.size(); i++) {
            if(memberCode == usingBooks.get(i).getMemberCode() && bCode == usingBooks.get(i).getBookCode()){
                temp = i;
            }
        }
        usingBooks.remove(temp);
    }



}
