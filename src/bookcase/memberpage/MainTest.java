package bookcase.memberpage;

import bookcase.Member;

public class MainTest {
    public static void main(String[] args) {
        Member member = new Member(10, "a", "a", "a", 10, "a", "a", 10);
        new MemberPage(member).memberPageStart();
    }
}
