package bookcase;

import java.sql.*;
import java.util.*;

import bookcase.crud.*;
import bookcase.object.*;
import bookcase.show.*;
import bookcase.util.*;

public class ReviewAddPage implements Show {
	
	//
	private static Connection con = JDBCconnecting.connecting();
	private ReviewCRUD reviewCrud = ReviewCRUD.getInstance();
	private BookCRUD bookCrud = BookCRUD.getInstance();
	private ViewReviewCRUD viewReviewCrud = ViewReviewCRUD.getInstance();
	private ArrayList<Review> reviews = new ArrayList<Review>();
	private ArrayList<Book> books = new ArrayList<>();
	private ArrayList<ViewReview> viewReviews = new ArrayList<ViewReview>();
	//
	
	private double rScore; // 별점
	private String rComment; // 한줄평
	private Member member;
	private Book book;
	private int temp;
	private int menuButton;
	private boolean chkFindBook;
	public List<Review> getReviewList() {
		return reviews;
	}

	public void setReviewList(ArrayList<Review> reviewList) {
		this.reviews = reviewList;
	}

	public ReviewAddPage(Member member) {
		this.member = member;
		menuButton = 0;
	}

	public void reviewAddStart() {
		while (menuButton != 4) {
			try {
				reviews = reviewCrud.getReviewList(con); //오라클에서 리뷰 테이블 전체 받음
				books = bookCrud.getBookList(con); //오라클에서 북 테이블 전체 받음
				viewReviews = viewReviewCrud.getReviewList(con);
				showReviewAddMenu();
				menuButton = CommonFunction.setMenuButton(">> 원하시는 메뉴를 선택하세요 : ", menuButton);

				switch (menuButton) {
				case 1:
					findBook();
					if (chkFindBook) {
						setReviewComment();
						System.out.println("▶ 리뷰 작성이 완료되었습니다!");
						System.out.println("==========================");
						System.out.println();
					}
					break;
				case 2:
					System.out.println("▶ 작성된 리뷰 목록을 출력합니다");
					for (int i = 0; i < viewReviews.size(); i++) {
						System.out.println(viewReviews.get(i));
						System.out.println();
					} if (viewReviews != null) { // 작성된 리뷰가 존재하지 않을시 안내문 출력 추가 - 지원
						System.out.println("[!] 작성된 리뷰가 존재하지 않습니다.");
					}
					break;
				case 3:
					System.out.println(">> 전체 도서 목록을 출력합니다");
					System.out.println();
					reviews = reviewCrud.getReviewList(con);
					books = bookCrud.getBookList(con);
					for (Book book : books) {
						System.out.println(book);
					}
					break;
				case 4:
					System.out.println("[!] 종료합니다");
					break;
				default:
					System.out.println("error : 잘못된 입력입니다.");
					break;
				}

			}catch (NumberFormatException e){
				System.out.println("error : 숫자로 입력해주세요.");
			}
		}
	}

	public void setReviewComment() { // 리뷰입력
		reviews = reviewCrud.getReviewList(con);
		setScore();
		setComment();

		/**
		 * 리뷰 DB에 넣게 처리
		 */
	
		reviewCrud.insertReview(con, new Review
				(0, member.getMemberCode(), 
						book.getBookCode(), 
						rScore, rComment));

	}

	private void setScore() {
		showScoreMenu();
		System.out.print("▶ 평점 : ");
		rScore = ScannerUtil.getInputDouble();
		checkScore();
		System.out.println("[평점 입력 완료]");
	}

	private void checkScore() {
		boolean chk1 = true;
		if (rScore > 5 || rScore < 0) {
			while (chk1) {
				showScoreError();
				System.out.print("▶ 평점 : ");
				rScore = ScannerUtil.getInputDouble();
				if (rScore <= 5) {
					chk1 = false;
					break;
				}
			}
		}
	}
	
	
	private void setComment() {
		showCommentMenu();
		rComment = ScannerUtil.getInputStringS("▶ 한줄평 : ");
		checkComment();

		System.out.println("[한줄평 입력 완료]");
	}
	
	private void checkComment() {
		boolean chk = true;
		if (rComment.length() > 40) {
			while (chk) {
				showCommentError();
				System.out.print("▶ 한줄평 : ");
				rComment = ScannerUtil.getInputString();
				if (rComment.length() <= 40) {
					chk = false;
					break;
				}
			}
		}
	}

	public void findBook() {// 책확인
		reviews = reviewCrud.getReviewList(con);
		books = bookCrud.getBookList(con);
		chkFindBook = false;
		while (!chkFindBook) {
			System.out.println("================================");
			System.out.print(">> 리뷰를 작성하실 도서명을 입력하세요 : ");
			String bName = ScannerUtil.getInputString();
			

			for (int i = 0; i < books.size(); i++) {
				if (bName.equals(books.get(i).getbName())) {
					temp = i;
					chkFindBook = true;
				}
			}
			if (!chkFindBook) {
				System.out.println("[!] 해당 도서를 찾을 수 없습니다.");
				break;
			} else {
				book = books.get(temp);
			}
		}
	}
}

// 멤버코드 -> 받아서 (memberCode)
// 책코드 -> 책 검색 (bookCode)

// review class
// 멤버코드get-책코드get-(String한줄평-float평점: 사용자가 직접입력)
//
// List<review> 만들어서 삽입
