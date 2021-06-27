package bookcase.object;

/***
 * 
 * @author 지원 (0627)
 * 매니저 관리페이지 내 "현재 대여중인 도서" 목록 수정
 * 기존 : bUsing 값이 true인 BOOK 테이블 조회
 * 변경 : BOOK 테이블 +  MEMBER, RENTAL 테이블 조인
 * (BOOK : 도서 정보 / MEMBER : 회원 이름 / (RENTAL : 대여 일자, 반납 일자)
 *
 */

public class mUsingBook {
	private String mName;
	private String bName;
	private String bWriter;
	private String bPublisher;
	private String bGenre;
	private String rentalDate;
	private String returnDate;
    
   
	public mUsingBook(String mName, String bName, String bWriter, String bPublisher, String bGenre, String rentalDate,
			String returnDate) {
		this.mName = mName;
		this.bName = bName;
		this.bWriter = bWriter;
		this.bPublisher = bPublisher;
		this.bGenre = bGenre;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
	}


	public String getmName() {
		return mName;
	}


	public void setmName(String mName) {
		this.mName = mName;
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
		return "▶ 회원 이름 : " + mName +
				"\n▶ 도서 이름 : " + bName +
				"\n▶ 저   자 : " + bWriter +
				"\n▶ 출 판 사 : " + bPublisher +
				"\n▶ 장   르 : " + bGenre +
				"\n▶ 대여 일자 : " + rentalDate +
				"\n▶ 반납예정일 : " + returnDate +
				"\n================================";
	}
    
}
