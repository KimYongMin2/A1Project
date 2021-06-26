package bookcase.object;

public class Review {
	private int reviewCode;
	private int memberCode;
	private int bookCode;
	private double rScore;
	private String rComment;
	
	public Review(int reviewCode, int memberCode, int bookCode, double rScore, String rComment) {
		super();
		this.reviewCode = reviewCode;
		this.memberCode = memberCode;
		this.bookCode = bookCode;
		this.rScore = rScore;
		this.rComment = rComment;

	}

	public int getReviewCode() {
		return reviewCode;
	}

	public void setReviewCode(int reviewCode) {
		this.reviewCode = reviewCode;
	}

	public int getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(int memberCode) {
		this.memberCode = memberCode;
	}

	public int getBookCode() {
		return bookCode;
	}

	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}

	public double getrScore() {
		return rScore;
	}

	public void setrScore(double rScore) {
		this.rScore = rScore;
	}

	public String getrComment() {
		return rComment;
	}

	public void setrComment(String rComment) {
		this.rComment = rComment;
	}	
    
}