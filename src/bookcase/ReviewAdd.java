package bookcase;

import java.sql.*;
import java.util.*;

public class ReviewAdd {
	
	//
	private static Connection con = JDBCconnecting.connecting();
	private static ReviewCRUD reviewCrud = ReviewCRUD.getInstance();
	private static BookCRUD bookCrud = BookCRUD.getInstance();
	private ArrayList<Review> reviews = new ArrayList<Review>();
	private ArrayList<Book> books = new ArrayList<>();
	//
	
	private String rComment; // 한줄평
	private double rScore; // 별점
	private Member member;
	private Book book;
	private int temp;
	private int menuButton;
	
	private Scanner sc = new Scanner(System.in);

	public List<Review> getReviewList() {
		return reviews;
	}

	public void setReviewList(ArrayList<Review> reviewList) {
		this.reviews = reviewList;
	}

	public ReviewAdd(Member member) {
		this.member = member;
		menuButton = 0;
	}

	public void reviewAddStart() {
		while (menuButton != 4) {
			try {
				reviews = reviewCrud.getReviewList(con); //오라클에서 리뷰 테이블 전체 받음
				books = bookCrud.getBookList(con); //오라클에서 북 테이블 전체 받음
				System.out.println("1. 리뷰입력    2. 리스트보기     3. 책목록    4. 종료");
				System.out.print("해당 메뉴를 선택해주세요 : ");
				String inputString = sc.nextLine();
				menuButton = Integer.parseInt(inputString);

				switch (menuButton) {
				case 1:
					findBook();
					setReviewComment();
					System.out.println("리뷰 작성이 완료되었습니다");
					break;
				case 2:
					System.out.println("리뷰 목록을 출력합니다");
					for (int i = 0; i < reviews.size(); i++) {
						System.out.println(reviews.get(i));
					}
					break;
				case 3:
					System.out.println("전체 책 목록을 출력합니다");
					for (Book book : books) {
						System.out.println(book);
					}
					break;
				case 4:
					System.out.println("종료합니다");
					break;
				default:
					System.out.println("잘못 입력하셨습니다");
					break;
				}

			}catch (NumberFormatException e){
				System.out.println("숫자");
			}
		}
	}

	public void setReviewComment() {// 리뷰입력
		reviews = reviewCrud.getReviewList(con);
		System.out.println("=====한줄평을 입력해주세요.=====");
		rComment = sc.nextLine();

		boolean chk = true;
		if (rComment.length() > 40) {
			while (chk) {
				System.out.println("[!]한줄평은 40자까지 입력 가능합니다.");
				System.out.println("=====한줄평을 입력해주세요.=====");
				rComment = sc.nextLine();
				if (rComment.length() <= 40) {
					chk = false;
					break;
				}
			}
		}
		System.out.println("한줄평이 입력되었습니다.");

		System.out.println("=====별점을 입력해주세요.=====");
		System.out.println("별점은 0 ~ 5 점 사이로 소수점 한 자리까지 입력 가능합니다.");
		rScore = sc.nextDouble();

		boolean chk1 = true;
		if (rScore > 5 || rScore < 0) {
			while (chk1) {
				System.out.println("[!]입력할 수 없는 별점입니다.");
				System.out.println("별점은 0 ~ 5 점 사이로 소수점 한 자리까지 입력 가능합니다.");
				rScore = sc.nextDouble();
				if (rScore <= 5) {
					chk1 = false;
					break;
				}
			}
		}
		System.out.println("별점이 입력되었습니다.");

		/**
		 * 리뷰 DB에 넣게 처리
		 */
		reviewCrud.insertReview(con, new Review
				(0, member.getMemberCode(), 
						book.getBookCode(), 
						rScore, rComment));
		System.out.println("작성 완료되었습니다!"); 

	}

	public void findBook() {// 책확인
		reviews = reviewCrud.getReviewList(con);
		books = bookCrud.getBookList(con);
		boolean check = false;
		while (!check) {
			for (Book book : books) {
				System.out.println(book);
			}
			System.out.print("리뷰하려는 책 이름을 작성해주세요 : ");
			String bName = sc.nextLine();
			

			for (int i = 0; i < books.size(); i++) {
				if (bName.equals(books.get(i).getbName())) {
					temp = i;
					check = true;
				}
			}
			if (!check) {
				System.out.println("찾지못하였습니다.");
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
