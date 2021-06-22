package BookRentPage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Rental {
		private int RentalCode;
		private String RentalDate;
		private String ReturnDate;
		private int MemberCode;
		private int BookCode;
		
	
		public Rental(int rentalCode, String rentalDate, String returnDate, int memberCode, int bookCode) {
			RentalCode = rentalCode;
			RentalDate = rentalDate;
			ReturnDate = returnDate;
			MemberCode = memberCode;
			BookCode = bookCode;
		}
		
		
	

		public int getRentalCode() {
			return RentalCode;
		}
		public void setRentalCode(int rentalCode) {
			RentalCode = rentalCode;
		}
		public String getRentalDate() {
			return RentalDate;
		}
		public void setRentalDate(String rentalDate) {
			RentalDate = rentalDate;
		}
		public String getReturnDate() {
			return ReturnDate;
		}
		public void setReturnDate(String returnDate) {
			ReturnDate = returnDate;
		}
		public int getMemberCode() {
			return MemberCode;
		}
		public void setMemberCode(int memberCode) {
			MemberCode = memberCode;
		}
		public int getBookCode() {
			return BookCode;
		}
		public void setBookCode(int bookCode) {
			BookCode = bookCode;
		}
	
		
		
		@Override
		public String toString() {
			return "Rental [RentalCode=" + RentalCode + ", RentalDate=" + RentalDate + ", ReturnDate=" + ReturnDate
					+ ", MemberCode=" + MemberCode + ", BookCode=" + BookCode + "]";
		}

=
		
}
