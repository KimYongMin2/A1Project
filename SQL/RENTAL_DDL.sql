
/* 대여 */
CREATE TABLE Rental (
	RentalCode NUMBER(4) CONSTRAINT RENTAL_CODE_PK PRIMARY KEY, /* 대여코드 */
	RentalDate DATE DEFAULT SYSDATE CONSTRAINT RETAL_RENTALDATE_NN NOT NULL, /* 대여날짜 */
	RetrunDate DATE DEFAULT SYSDATE+7 CONSTRAINT RETAL_RETRUNDATE_NN NOT NULL, /* 반납날짜 */
	MemberCode NUMBER(4) NOT NULL constraint RENTAL_MemberCode_fk REFERENCES MEMBER (MemberCode), /* 회원코드 */
	BookCode NUMBER(4) NOT NULL constraint RENTAL_BookCode_fk REFERENCES BOOK (BookCode) /* 도서코드 */
);

COMMENT ON TABLE Rental IS '대여';
COMMENT ON COLUMN Rental.RentalCode IS '대여코드';
COMMENT ON COLUMN Rental.RentalDate IS '대여날짜';
COMMENT ON COLUMN Rental.RetrunDate IS '반납날짜';
COMMENT ON COLUMN Rental.MemberCode IS '회원코드';
COMMENT ON COLUMN Rental.BookCode IS '도서코드';
