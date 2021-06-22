package bookcase;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

public class reviewHandler {
	Scanner kb = new Scanner(System.in);
		
	private static Connection con = JDBCconnecting.connecting();
	private static ReviewCRUD reviewCrud = ReviewCRUD.getInstance();
	private static ArrayList<Review> reviews = new ArrayList<Review>();
	
	public void joinReview() {
		// test 위해 임의로 넣은 값
		
		System.out.println("[평점을 입력해주세요]");
		double rStore = Double.parseDouble(kb.nextLine());
		
		System.out.println("[한줄평을 입력해주세요]");
		String rcomment = kb.nextLine();
		
		reviewCrud.insertReview(con, new Review(0, 2, 4, rStore, rcomment));
	}
	
	public void showReview() {
		reviews = reviewCrud.getReviewList(con);
		
		for(Review review : reviews) {
			System.out.println(review);
		}
	}
}
