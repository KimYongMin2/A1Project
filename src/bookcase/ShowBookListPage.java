package bookcase;

import bookcase.crud.BookCRUD;
import bookcase.crud.RentalCRUD;
import bookcase.object.Book;
import bookcase.object.Member;
import bookcase.object.Using;
import bookcase.show.Show;
import bookcase.util.JDBCconnecting;
import bookcase.util.ScannerUtil;

import java.sql.*;
import java.util.*;

public class ShowBookListPage implements Show {

	private static Connection con = JDBCconnecting.connecting();
	private static BookCRUD bookCrud  = BookCRUD.getInstance();
	private static RentalCRUD rentalCrud  = RentalCRUD.getInstance();
	
	private static ArrayList<Book> books = new ArrayList<Book>();
	private static ArrayList<Using> usings = new ArrayList<Using>();
	private int menuButton = 0;
	private Member member;

	public ShowBookListPage(Member member){
		this.member = member;
	}
	public void showBookListStart() {
		while (menuButton != 4) {
			try {
				showBookListPageMenu();

				System.out.print("해당사항을 선택해주세요 : ");
				menuButton = ScannerUtil.getInputInteger();

				switch (menuButton) {
					case 1:
						// 내가 대여중인 도서 보기 (반납기한도)
						showMyUsingBook();
						break;
					case 2:
						// 별점랭킹 추천 조회
						showStarBook();
						break;
					case 3:
						// 대여가능한 도서 조회
						showCanUsingBook();
						break;
					case 4:
						// 종료
						System.out.println("종료합니다");
						break;
					default:
						System.out.println("잘못입력하셨습니다");
						break;
				}
			}catch (NumberFormatException e){
				System.out.println("숫자로 입력해주세요");
			}
		}
	}

	public void showMyUsingBook(){
		books = rentalCrud.getMyRentalList(con, member);
		if (books.isEmpty()) { // 수정 : 리스트 비어있는지 확인
			System.out.println("아직 대여하신 도서가 한 권도 없습니다");
		} else {
			System.out.println("내가 대여중인 도서입니다");
			for(Book book:books) {
				System.out.println(book);
			}
		}
	}

	public void showStarBook(){
		books = bookCrud.getBestBookList(con);
		System.out.println("별점랭킹입니다");
		if(books.isEmpty()) {
			System.out.println("아직 남겨진 별점이 하나도 없습니다");
		} else {
			System.out.println("별점 랭킹 상위 5가지 책입니다");
			for(Book book:books) {
				System.out.println(book);
			}
		}
	}

	public void showCanUsingBook(){
		books = rentalCrud.getPossibleList(con);
		
		if(books.isEmpty()) {
			System.out.println("대여가능한 도서가 하나도 없습니다");
		} else {
			for(Book book:books) {
				System.out.println("대여가능한 도서 목록을 출력합니다.");
				System.out.println(book);
			}
		}
	}

}
