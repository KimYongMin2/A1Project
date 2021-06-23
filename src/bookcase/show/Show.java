package bookcase.show;

public interface Show {
    default void showReBookMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("1. 책이름     2. 지은이     3. 출판사");
        System.out.println("4. 장르       5. 가격      6. 연령제한 여부");
        System.out.println("--------------------------------------------");
    }

    default void showBookManagerMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("1. 도서 추가하기 ");
        System.out.println("2. 도서 삭제하기 ");
        System.out.println("3. 도서 수정하기 ");
        System.out.println("4. 전체 책 리스트 조회 ");
        System.out.println("5. 대여중인 책 리스트 조회 ");
        System.out.println("6. 종료");
        System.out.println("--------------------------------------------");
    }

    default void showBookUsingMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("1. 도서 대여하기 ");
        System.out.println("2. 종료");
        System.out.println("--------------------------------------------");
    }

    default void showBookReturnMenu() {
        System.out.println("--------------------------------------------");
        System.out.println("1. 도서 반납하기 ");
        System.out.println("2. 종료");
        System.out.println("--------------------------------------------");
    }
    
    default void showEditMyInfoMenu() {
    	 System.out.println("--------------------------------------------");
         System.out.println("1. 내 정보 수정하기 ");
         System.out.println("2. 회원 탈퇴");
         System.out.println("3. 종료");
         System.out.println("--------------------------------------------");
    }
}
