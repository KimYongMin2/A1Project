

/* 대여 */
Create Table Rental (
	Rentalcode Number(4) Constraint Rental_Code_Pk Primary Key, /* 대여코드 */
	Rentaldate Date Default Sysdate Constraint Retal_Rentaldate_Nn Not Null, /* 대여날짜 */
	Returndate Date Default Sysdate+7 Constraint Retal_Returndate_Nn Not Null, /* 반납날짜 */
	Membercode Number(4) Not Null Constraint Rental_Membercode_Fk References Member (Membercode), /* 회원코드 */
	Bookcode Number(4) Not Null Constraint Rental_Bookcode_Fk References Book (Bookcode) /* 도서코드 */
);

COMMENT ON TABLE Rental IS '대여';
COMMENT ON COLUMN Rental.RentalCode IS '대여코드';
COMMENT ON COLUMN Rental.RentalDate IS '대여날짜';
COMMENT ON COLUMN Rental.ReturnDate IS '반납날짜';
COMMENT ON COLUMN Rental.MemberCode IS '회원코드';
COMMENT ON COLUMN Rental.BookCode IS '도서코드';

drop table RENTAL;

commit;