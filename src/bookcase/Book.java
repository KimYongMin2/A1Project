package bookcase;

public class Book {
    private int bookCode;
    private String bName;
    private String bWriter;
    private String bPublisher;
    private String bGenre;
    private int bPrice;
    private boolean bUsing;
    private boolean bAgeUsing;

    public Book(int bookCode, String bName, String bWriter, String bPublisher, String bGenre, int bPrice, boolean bUsing, boolean bAgeUsing) {
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

    public boolean isbUsing() {
        return bUsing;
    }

    public void setbUsing(boolean bUsing) {
        this.bUsing = bUsing;
    }

    public boolean isbAgeUsing() {
        return bAgeUsing;
    }

    public void setbAgeUsing(boolean bAgeUsing) {
        this.bAgeUsing = bAgeUsing;
    }

}
