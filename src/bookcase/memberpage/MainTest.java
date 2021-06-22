package bookcase.memberpage;

import bookcase.Member;
import bookcase.manager.BookManager;

public class MainTest {
    public static void main(String[] args) {
 //       Member member = new Member(10, "a", "a", "a", 10, "a", "a", 10);
        BookManager bookManager = new BookManager();
        bookManager.bookManagerStart();
 //       new MemberPage(member).memberPageStart();


    }
}
