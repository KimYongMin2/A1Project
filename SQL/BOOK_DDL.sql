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