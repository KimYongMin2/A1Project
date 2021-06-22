create table BOOK (
BookCode number(4) constraint BOOK_code_pk primary key,
BName varchar(50) constraint BOOK_BName_nn not null,
BWriter varchar(50) constraint BOOK_BWriter_nn not null,
BPublisher varchar(50) constraint BOOK_BPublisher_nn not null,
BGenre varchar(10) constraint BOOK_BGenre_nn not null,
BPrice integer constraint BOOK_BPrice_nn not null 
);

CREATE SEQUENCE BOOK_CODE_PK
START WITH 1
INCREMENT BY 1;

ALTER TABLE BOOK
MODIFY (BGenre varchar(50));

ALTER TABLE BOOK ADD(bUsing VARCHAR2(50));

ALTER TABLE BOOK ADD(bAgeUsing VARCHAR2(50));


-- 지원 / BOOK 컬럼 comment 추가
COMMENT ON COLUMN BOOK.BookCode IS '도서코드';
COMMENT ON COLUMN BOOK.BName IS '도서명';
COMMENT ON COLUMN BOOK.BWriter IS '저자';
COMMENT ON COLUMN BOOK.BPublisher IS '출판사';
COMMENT ON COLUMN BOOK.BGenre IS '장르';
COMMENT ON COLUMN BOOK.BPrice IS '가격';
