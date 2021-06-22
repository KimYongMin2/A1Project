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
				System.out.println("=============");
				System.out.println("1. select");
				System.out.println("2. insert");
				System.out.println("3. 종료");
				System.out.println("=============");
				int menu = Integer.parseInt(kb.nextLine());
				switch (menu) {
				case 2: 
					rh.joinReview();
					break;
//				case 2: 
//					review = rh.();
//					break;
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

	
