package bookcase;

import java.util.*;

public class reviewtest {

	public static void main(String[] args) {
		
		
		try {
			Scanner kb = new Scanner(System.in);
			reviewHandler rh = new reviewHandler();
			Review review = null;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			while(true) {
				System.out.println("======================");
				System.out.println("1. 리뷰리스트 전체보기");
				System.out.println("2. 리뷰 작성");
				System.out.println("3. 종료");
				System.out.println("======================");
				int menu = Integer.parseInt(kb.nextLine());
				switch (menu) {
				case 1: 
					rh.showReview();
					break;
				case 2: 
					rh.joinReview();
					break;
				case 3: 
					System.exit(0);
					break;
				}	
		} 
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}

}
}

	
