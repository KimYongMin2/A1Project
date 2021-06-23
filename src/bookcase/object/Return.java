package bookcase.object;

public class Return {
	
	/***
	 * @author 민주
	 */
	private String bName;
	private String bWriter;
	private String bPublisher;
	private String bGenre;
	private String rentalDate;
	private String returnDate;
	
	public Return(String bName, String bWriter, String bPublisher, String bGenre, String rentalDate,
			String returnDate) {
		this.bName = bName;
		this.bWriter = bWriter;
		this.bPublisher = bPublisher;
		this.bGenre = bGenre;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
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

	public String getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "== 대여한 책 목록 ==\n"
				+"책이름: " + bName 
				+"\n저자:" + bWriter 
				+"\n출판사: " + bPublisher 
				+"\n장르: " + bGenre
				+"\n대출일자: " + rentalDate 
				+"\n반납예정일자: " + returnDate 
				+"\n===============";
	}
	
	
	
}
