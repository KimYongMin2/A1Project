package bookcase.manager;

import bookcase.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookManager {
    private List<Book> bookList = new ArrayList<>();
    private int bookCode;
    private String bName;
    private String bWriter;
    private String bPublisher;
    private String bGenre;
    private int bPrice;
    private boolean bUsing;
    private boolean bAgeUsing;
    private int menuButton;
    private int temp;
    private boolean findCheck = false;
    private static Scanner scanner = new Scanner(System.in);
    public void bookManagerStart(){
        System.out.println("--------------------------------------------");
        System.out.println("1. 도서 추가하기 ");
        System.out.println("2. 도서 삭제하기 ");
        System.out.println("3. 도서 수정하기 ");
        System.out.println("4. 전체 책 리스트 조회 ");
        System.out.println("5. 대여중인 책 리스트 조회 ");
        System.out.println("--------------------------------------------");

        System.out.print(" 메뉴를 선택해주세요 : ");
        menuButton = Integer.parseInt(scanner.nextLine());

        switch (menuButton){
            case 1 :
                // 추가
                addBook();
                break;
            case 2 :
                // 삭제
                deleteBook(bookList);
                break;
            case 3 :
                // 수정
                reBook();
                break;
            case 4 :
                // 전체 첵 리스트 조회
                showAllBookList();
                break;
            case 5 :
                // 대여중인 책 리스트 조회
                showUsingBookList();
                break;
        }
    }


    public void addBook(){
        System.out.print("도서코드를 입력해주세요 : ");
        bookCode = Integer.parseInt(scanner.nextLine());

        System.out.print("책 이름을 입력해주세요 : ");
        bName = scanner.nextLine();

        System.out.print("책 이름을 입력해주세요 : ");
        bWriter = scanner.nextLine();

        System.out.print("책 이름을 입력해주세요 : ");
        bPublisher = scanner.nextLine();

        System.out.print("책 이름을 입력해주세요 : ");
        bGenre = scanner.nextLine();

        System.out.print("도서코드를 입력해주세요 : ");
        bPrice = Integer.parseInt(scanner.nextLine());

        bUsing = false;

        System.out.println("연련제한이 있습니까?");
        System.out.print("True    False : ");
        bAgeUsing = Boolean.parseBoolean(scanner.nextLine());

        Book book = new Book(bookCode, bName, bWriter, bPublisher,
                bGenre, bPrice, bUsing, bAgeUsing);
        bookList.add(book);

    }

    public void deleteBook(List<Book> bookList){
        System.out.print("삭제할 도서코드를 입력해주세요 : ");
        bookCode = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < bookList.size(); i++) {
            if(bookCode == bookList.get(i).getBookCode()){
                temp = i;
                findCheck = true;
            }
        }
        if (findCheck == true){
            bookList.remove(temp);
            System.out.println("삭제되었습니다");
        }else {
            System.out.println("파일을 찾지 못하였습니다.");
        }
    }

    public void reBook(){

    }

    public void showAllBookList(){
        System.out.println("전체 리스트입니다");
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    public void showUsingBookList(){
        System.out.println("대여중인 리스트입니다");
        for (Book book : bookList) {
            if(book.isbUsing() == true){
                System.out.println(book);
            }
        }
    }

}
