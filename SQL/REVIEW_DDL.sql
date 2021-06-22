
/* 리뷰 */
CREATE TABLE Review (
	ReviewCode NUMBER(4) CONSTRAINT REVIEW_CODE_PK PRIMARY KEY, /* 리뷰코드 */
	MemberCode NUMBER(4) NOT NULL CONSTRAINT REVIEW_MEMBERCODE_FK REFERENCES MEMBER(MemberCode), /* 회원코드 */
	BookCode NUMBER(4) NOT NULL CONSTRAINT REVIEW_BOOKCODE_FK REFERENCES BOOK(BookCode), /* 도서코드 */
	RScore INTEGER, /* 평점 */
	RComment VARCHAR2(100) /* 한줄평 */
);

COMMENT ON TABLE Review IS '리뷰';
COMMENT ON COLUMN Review.ReviewCode IS '리뷰코드';
COMMENT ON COLUMN Review.MemberCode IS '회원코드';
COMMENT ON COLUMN Review.BookCode IS '도서코드';
COMMENT ON COLUMN Review.RScore IS '평점';
COMMENT ON COLUMN Review.RComment IS '한줄평';
