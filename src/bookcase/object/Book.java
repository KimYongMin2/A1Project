package bookcase.object;

public class Book {
    private int bookCode;
    private String bName;
    private String bWriter;
    private String bPublisher;
    private String bGenre;
    private int bPrice;
    private String bUsing;
    private String bAgeUsing;

    public Book(int bookCode, String bName, 
    		String bWriter, String bPublisher, String bGenre, 
    		int bPrice, String bUsing, String bAgeUsing) {
        this.bookCode = bookCode;
        this.bName = bName;
        this.bWriter = bWriter;
        this.bPublisher = bPublisher;
        this.bGenre = bGenre;
        this.bPrice = bPrice;
        this.bUsing = bUsing;
        this.bAgeUsing = bAgeUsing;
    }

    public int getBookCode() {
        return bookCode;
    }

    public void setBookCode(int bookCode) {
        this.bookCode = bookCode;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getbWriter() {
        return bWriter;
    }

    public void setbWriter(String bWriter) {
        this.bWriter = bWriter;
    }

    public String getbPublisher() {
        return bPublisher;
    }

    public void setbPublisher(String bPublisher) {
        this.bPublisher = bPublisher;
    }

    public String getbGenre() {
        return bGenre;
    }

    public void setbGenre(String bGenre) {
        this.bGenre = bGenre;
    }

    public int getbPrice() {
        return bPrice;
    }

    public void setbPrice(int bPrice) {
        this.bPrice = bPrice;
    }

    public String getbUsing() {
        return bUsing;
    }

    public void setbUsing(String bUsing) {
        this.bUsing = bUsing;
    }

    public String getbAgeUsing() {
        return bAgeUsing;
    }

    public void setbAgeUsing(String bAgeUsing) {
        this.bAgeUsing = bAgeUsing;
    }

	@Override
	public String toString() {
		return "■■■■■■■■■■■ 도서 정보 ■■■■■■■■■■■" +
				"\n▶ 도서 이름 : " + bName +
				"\n▶ 저   자 : " + bWriter +
				"\n▶ 출 판 사 : " + bPublisher +
				"\n▶ 장   르 : " + bGenre +
				"\n▶ 판매 가격 : " + bPrice +
				"\n▶ 도서 코드 : " + bookCode + 
				"\n▶ 대여 여부 : " + bUsing +
				"\n▶ 연령 제한 : " + bAgeUsing;
	}
    
}
