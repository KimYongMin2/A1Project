package bookcase.object;

public class ViewReview {
	String bName;
	String bWriter;
	private double rScore;
	private String rComment;
	
	public ViewReview(String bName, String bWriter, double rScore, String rComment) {
		this.bName = bName;
		this.bWriter = bWriter;
		this.rScore = rScore;
		this.rComment = rComment;
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
	@Override
	public String toString() {
		return "▶ 도서 이름 : " + bName
				+"\n▶ 저   자 : " + bWriter
				+"\n▶ 한줄평 : " + rComment
				+"\n▶ 평점 : " + rScore +
				"\n================================";
	}
	
	
}
