package bookcase;

import java.util.*;

import bookcase.crud.*;
import bookcase.object.*;
import bookcase.show.*;
import bookcase.util.*;

public class ReViewAdd extends Common implements Show {

	private ReviewCRUD reviewCrud = ReviewCRUD.getInstance();
	private ViewReviewCRUD viewReviewCrud = ViewReviewCRUD.getInstance();
	private BookCRUD bookCrud = BookCRUD.getInstance();
	
	private ArrayList<ViewReview> viewReviews = new ArrayList<ViewReview>();

	private double rScore; // 별점
	private String rComment; // 한줄평

	public ReViewAdd(Member member) {
		this.member = member;
	}

	public void addingReview() {
		bookList = bookCrud.getBookList(con);
//		showReviewAdd();
		bName = ScannerUtil.getInputStringS(">> 리뷰를 작성하실 도서명을 입력하세요 : ");
		book = bookCrud.findBook(con, bName);
		bookFindChk = setFindBookCheck(book);
		if (bookFindChk) {
			setReviewComment();
		}
	}

	public void setReviewComment() { // 리뷰입력
		reviewCrud.getReviewList(con);
		setScore();
		setComment();
		reviewCrud.insertReview(con, new Review
				(0, member.getMemberCode(), 
						book.getBookCode(), 
						rScore, rComment));
		showReviewAddSuccess();
	}

	private void setScore() { // 평점 주기
		showScoreMenu();
		rScore = ScannerUtil.getInputDoubleS("▶ 평점 : ");
		checkScore();
		System.out.println("[평점 입력 완료]");
	}

	private void checkScore() { // 이상한 평점 값 못 주게 막기
		boolean chk1 = true;
		if (rScore > 5 || rScore < 0) {
			while (chk1) {
				showScoreError();
				rScore = ScannerUtil.getInputDoubleS("▶ 평점 : ");
				if (rScore <= 5) {
					chk1 = false;
					break;
				}
			}
		}
	}

	private void setComment() { // 한줄평 입력하기
		showCommentMenu();
		rComment = ScannerUtil.getInputStringS("▶ 한줄평 : ");
		checkComment();
		System.out.println("[한줄평 입력 완료]");
	}

	private void checkComment() { // 한줄평 사이즈 제한
		boolean chk = true;
		if (rComment.length() > 40) {
			while (chk) {
				showCommentError();
				rComment = ScannerUtil.getInputStringS("▶ 한줄평 : ");
				if (rComment.length() <= 40) {
					chk = false;
					break;
				}
			}
		}
	}
	
	public void showReview() { // 리뷰 조회
		viewReviews = viewReviewCrud.getReviewList(con);
		System.out.println("▶ 작성된 리뷰 목록을 조회합니다");
		System.out.println();
		System.out.println("■■■■■■■■■■■ 리뷰 조회 ■■■■■■■■■■■");
		if(viewReviews.size() > 0) {
			for (int i = 0; i < viewReviews.size(); i++) {
				System.out.println(viewReviews.get(i));
			} 
		} else {
			System.out.println("[!] 작성된 리뷰가 존재하지 않습니다.");
		}
		System.out.println();
	}
	
	public void showBookList() { // 도서 목록
		System.out.println(">> 전체 도서 목록을 출력합니다");
		System.out.println();
		reviewCrud.getReviewList(con);
		bookList = bookCrud.getBookList(con);
		for (Book book : bookList) {
			System.out.println(book);
		}
	}
}
