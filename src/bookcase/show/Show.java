package bookcase.show;

public interface Show {
    default void showReBookMenu() {
        System.out.println("--------------------------------------------------------");
        System.out.println("1. 책이름     2. 지은이     3. 출판사");
        System.out.println("4. 장르       5. 가격      6. 연령제한 여부");
        System.out.println("--------------------------------------------------------");
    }

    default void showBookManagerMenu() {
        System.out.println("--------------------------------------------------------");
        System.out.println("1. 도서 추가하기 ");
        System.out.println("2. 도서 삭제하기 ");
        System.out.println("3. 도서 수정하기 ");
        System.out.println("4. 전체 책 리스트 조회 ");
        System.out.println("5. 대여중인 책 리스트 조회 ");
        System.out.println("6. 종료");
        System.out.println("--------------------------------------------------------");
    }

    default void showBookUsingMenu() {
        System.out.println("--------------------------------------------------------");
        System.out.println("1. 도서 대여하기 ");
        System.out.println("2. 종료");
        System.out.println("--------------------------------------------------------");
    }

    default void showBookReturnMenu() {
        System.out.println("--------------------------------------------------------");
        System.out.println("1. 도서 반납하기 ");
        System.out.println("2. 종료");
        System.out.println("--------------------------------------------------------");
    }
    
    default void showEditMyInfoMenu() {
        System.out.println("--------------------------------------------------------");
         System.out.println("1. 내 정보 수정하기 ");
         System.out.println("2. 회원 탈퇴");
         System.out.println("3. 내 정보 보기");
         System.out.println("4. 종료");
        System.out.println("--------------------------------------------------------");
    }

    default void showStartMenu() {
        System.out.println("--------------------------------------------------------");
        System.out.println("1. 회원 로그인");
        System.out.println("2. 관리자 로그인");
        System.out.println("3. 회원가입");
        System.out.println("4. 아이디찾기");
        System.out.println("5. 종료");
        System.out.println("--------------------------------------------------------");
    }

    default void showBookListPageMenu() {
        System.out.println("--------------------------------------------------------");
        System.out.println("1. 내가 대여중인 도서 보기");
        System.out.println("2. 별점랭킹 추천 조회");
        System.out.println("3. 대여가능한 도서 보기");
        System.out.println("4. 종료");
        System.out.println("--------------------------------------------------------");
    }

    default void showReviewAddMenu() {
        System.out.println("--------------------------------------------------------");
        System.out.println("1. 리뷰입력    2. 리스트보기     3. 책목록    4. 종료");
        System.out.println("--------------------------------------------------------");
    }


    default void showScoreMenu() {
        System.out.println("--------------------------------------------------------");
        System.out.println("별점을 입력해주세요");
        System.out.println("별점은 0 ~ 5 점 사이로 소수점 한 자리까지 입력 가능합니다.");
        System.out.println("--------------------------------------------------------");
    }

    default void showCommentMenu() {
        System.out.println("--------------------------------------------------------");
        System.out.println("한줄평을 입력해주세요");
        System.out.println("한줄평은 40자리까지 입력 가능합니다");
        System.out.println("--------------------------------------------------------");
    }

    default void showScoreError() {
        System.out.println("--------------------------------------------------------");
        System.out.println("[!]입력할 수 없는 별점입니다.");
        System.out.println("별점은 0 ~ 5 점 사이로 소수점 한 자리까지 입력 가능합니다.");
        System.out.println("--------------------------------------------------------");
    }

    default void showCommentError() {
        System.out.println("--------------------------------------------------------");
        System.out.println("[!]한줄평은 40자까지 입력 가능합니다.");
        System.out.println("=====한줄평을 입력해주세요.=====");
        System.out.println("--------------------------------------------------------");
    }
}
