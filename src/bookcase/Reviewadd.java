package bookcase;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import bookcase.Book;
import bookcase.Member;

public class Reviewadd {

	String rComment; // 한줄평
	double rScore; // 별점
	Member member;
	Book book;
	
	public Reviewadd(Member member) {
		this.member = member;
	}

	// ArrayList<String> reviewList = new ArrayList ;
	// ArrayList<>(Arrays.asList(Member.getmemberCode, Book.getbookCode, RComment,
	// Rscore));

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// 리뷰입력
		System.out.println("=====한줄평을 입력해주세요.=====");
		String rComment = sc.nextLine();

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
		double rScore = sc.nextDouble();

		boolean chk1 = true;
		if (rScore > 5 || rScore < 0) {
			while (chk1) {
				System.out.println("[!]입력할 수 없는 별점입니다.");
				System.out.println("별점은 0 ~ 5 점 사이로 소수점 한 자리까지 입력 가능합니다.");
				rScore = sc.nextDouble();

				if (rScore <=5) {
					chk = false;
					break;
				}
			}
		}
		System.out.println("별점이 입력되었습니다.");
	}
	
}
// 멤버코드 -> 받아서 (memberCode)
// 책코드 -> 책 검색 (bookCode)

// review class
// 멤버코드get-책코드get-(String한줄평-float평점: 사용자가 직접입력)
//
// List<review> 만들어서 삽입
