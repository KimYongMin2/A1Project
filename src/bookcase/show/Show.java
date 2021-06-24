package bookcase.show;

public interface Show {
	
    default void showStartMenu() {
    	System.out.println("■■■■■  도서대여 서비스 <책꽂이>  ■■■■■");
    	System.out.println("■■■■■■■■■■■ H O M E ■■■■■■■■■■■");
        System.out.println("1. 회원 로그인");
        System.out.println("2. 관리자 로그인");
        System.out.println("3. 회원가입");
        System.out.println("4. 아이디/비밀번호 찾기");
        System.out.println("5. 종료");
        System.out.println("================================");
    }
    

    default void showMemberPageMenu() {
        System.out.println("■■■■■■■■■■■ M E N U ■■■■■■■■■■■");
        System.out.println("1. 도서 조회");
        System.out.println("2. 도서 대여");
        System.out.println("3. 도서 반납");
        System.out.println("4. 도서 리뷰");
        System.out.println("5. 내 정 보");
        System.out.println("6. 로그아웃");
        System.out.println("================================");
    }
    
    
    default void showReBookMenu() {
        System.out.println("■■■■■■■■■■■ 도서 정보 ■■■■■■■■■■■");
        System.out.println("1. 도 서 명      2. 저  자  ");
        System.out.println("3. 출 판 사      4. 장  르  ");
        System.out.println("5. 가    격     6. 연령제한 ");
        System.out.println("================================");
    }

    default void showBookManagerMenu() {
        System.out.println("■■■■■■■■■■■ 도서 관리 ■■■■■■■■■■■");
        System.out.println("1. 도서 추가 ");
        System.out.println("2. 도서 삭제 ");
        System.out.println("3. 도서 수정 ");
        System.out.println("4. 전체 도서 리스트 조회 ");
        System.out.println("5. 대여중인 도서 리스트 조회 ");
        System.out.println("6. 로그아웃");
        System.out.println("================================");
    }

    default void showBookUsingMenu() {
        System.out.println("■■■■■■■■■■■ 대   여 ■■■■■■■■■■■");
        System.out.println("1. 도서 대여하기 ");
        System.out.println("2. 이전으로");
        System.out.println("================================");
    }

    default void showBookReturnMenu() {
        System.out.println("■■■■■■■■■■■ 반   납 ■■■■■■■■■■■");
        System.out.println("1. 도서 반납하기 ");
        System.out.println("2. 이전으로");
        System.out.println("================================");
    }
    
    default void showEditMyInfoMenu() {
    	System.out.println("■■■■■■■■■■■ 마이페이지 ■■■■■■■■■■■");
    	System.out.println("1. 내 정보 조회 ");
    	System.out.println("2. 내 정보 수정");
    	System.out.println("3. 회원 탈퇴");
    	System.out.println("4. 종료");
    	System.out.println("================================");
    }

    default void showBookListPageMenu() {
        System.out.println("■■■■■■■■■■■ 도 서 조 회 ■■■■■■■■■■■");
        System.out.println("1. 내가 대여중인 도서 조회");
        System.out.println("2. 도서 랭킹 조회");
        System.out.println("3. 대여가능한 도서 조회");
        System.out.println("4. 종료");
        System.out.println("================================");
    }

    default void showReviewAddMenu() {
        System.out.println("■■■■■■■■■■■ 리 뷰 작 성 ■■■■■■■■■■■");
        System.out.println("1. 리뷰 작성       2. 리뷰 조회");
        System.out.println("3. 도서 목록    	4. 이전으로");
        System.out.println("================================");
    }


    default void showScoreMenu() {
        System.out.println("■■■■■■■■■■■ 평      점 ■■■■■■■■■■■");
        System.out.println("평점을 입력해주세요");
        System.out.println("평점은 0 ~ 5 점 사이로 소수점 한 자리까지 입력 가능합니다.");
        System.out.println("================================");
    }

    default void showCommentMenu() {
        System.out.println("■■■■■■■■■■■ 한  줄  평 ■■■■■■■■■■■");
        System.out.println("한줄평을 입력해주세요.");
        System.out.println("한줄평은 40자까지 입력 가능합니다.");
        System.out.println("================================");
    }

    default void showScoreError() {
        System.out.println("================================");
        System.out.println("[!] 입력할 수 없는 평점입니다.");
        System.out.println("[!] 평점은 0 ~ 5 점 사이로 소수점 한자리까지 입력 가능합니다.");
        System.out.println("================================");
    }

    default void showCommentError() {
        System.out.println("================================");
        System.out.println("[!] 입력 가능한 글자수를 초과하였습니다.");
        System.out.println("[!] 한줄평은 40자까지 입력 가능합니다.");
        System.out.println("================================");
    }

    default void showReturnBookPage() {
        System.out.println(">> 반납 페이지 입니다");
        System.out.println("================================");
    }

    default void showRentalBookPage() {
        System.out.println(">> 대여 페이지 입니다");
        System.out.println("================================");
    }

    default void showAddBookSuccess() {
        System.out.println("================================");
        System.out.println("▶ 새로운 도서를 추가하였습니다");
    }

}
