

-- 테이블 값 날림
DELETE FROM REVIEW;
DELETE FROM RENTAL;
DELETE FROM BOOK;
DELETE FROM MEMBER;

-- 테이블 드랍
DROP TABLE MEMBER;
DROP TABLE BOOK;
DROP TABLE RENTAL;
DROP TABLE REVIEW;

-- 시퀀스 드랍
DROP SEQUENCE MEMBER_CODE_PK;
DROP SEQUENCE RENTAL_CODE_PK;
DROP SEQUENCE REVIEW_CODE_PK;
DROP SEQUENCE BOOK_CODE_PK;

-- 테이블 생성
CREATE TABLE Member (
	MemberCode NUMBER(4) NOT NULL CONSTRAINT MEMBER_CODE_PK PRIMARY KEY, /* 회원코드 */
	ID VARCHAR(15) NOT NULL, /* 회원아이디 */
	Password VARCHAR(20) NOT NULL, /* 비밀번호 */
	MName VARCHAR(50) NOT NULL, /* 이름 */
	Age INTEGER NOT NULL, /* 나이 */
	PhoneNum VARCHAR(30) NOT NULL, /* 전화번호 */
	Email VARCHAR(40) DEFAULT 'non@non.com' /* 이메일 */
);

create table BOOK (
BookCode number(4) constraint BOOK_code_pk primary key,
BName varchar2(50) constraint BOOK_BName_nn not null,
BWriter varchar2(50) constraint BOOK_BWriter_nn not null,
BPublisher varchar2(50) constraint BOOK_BPublisher_nn not null,
BGenre varchar2(50) constraint BOOK_BGenre_nn not null,
BPrice integer constraint BOOK_BPrice_nn not null,
BUsing Varchar2(50),
BAgeUsing Varchar2(50)
);

Create Table Rental (
	Rentalcode Number(4) Constraint Rental_Code_Pk Primary Key, /* 대여코드 */
	Rentaldate Date Default Sysdate Constraint Retal_Rentaldate_Nn Not Null, /* 대여날짜 */
	Returndate Date Default Sysdate+7 Constraint Retal_Returndate_Nn Not Null, /* 반납날짜 */
	Membercode Number(4) Not Null Constraint Rental_Membercode_Fk References Member (Membercode), /* 회원코드 */
	Bookcode Number(4) Not Null Constraint Rental_Bookcode_Fk References Book (Bookcode) /* 도서코드 */
);

CREATE TABLE Review (
	ReviewCode NUMBER(4) CONSTRAINT REVIEW_CODE_PK PRIMARY KEY, /* 리뷰코드 */
	MemberCode NUMBER(4) NOT NULL CONSTRAINT REVIEW_MEMBERCODE_FK REFERENCES MEMBER(MemberCode), /* 회원코드 */
	BookCode NUMBER(4) NOT NULL CONSTRAINT REVIEW_BOOKCODE_FK REFERENCES BOOK(BookCode), /* 도서코드 */
	RScore INTEGER, /* 평점 */
	RComment VARCHAR2(100) /* 한줄평 */
);

-- 시퀀스 생성
CREATE SEQUENCE MEMBER_CODE_PK
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE RENTAL_CODE_PK
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE REVIEW_CODE_PK
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE BOOK_CODE_PK
START WITH 1
INCREMENT BY 1;



