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
		reviewCrud.insertReview(con, new Review(0, 1, 1, 4.0, "aaa"));
	}
}
