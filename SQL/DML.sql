INSERT INTO MEMBER VALUES(MEMBER_CODE_PK.NEXTVAL, 'admin', 'admin', '관리자', 00, '010-1234-5678', 'manager@gmail.com');
INSERT INTO MEMBER VALUES(MEMBER_CODE_PK.NEXTVAL, 'cheoljung', 'cjfwnd123', '김철중', 24, '010-1234-1234', 'cheoljung@gmail.com');
INSERT INTO MEMBER VALUES(MEMBER_CODE_PK.NEXTVAL, 'user1', 'tiger', '김진성', 55, '010-1231-1231', null);

INSERT INTO BOOK VALUES(BOOK_CODE_PK.NEXTVAL, '자바의 정석', '남궁성', '도우출판', 'IT/컴퓨터', 27000, 'false', 'false');
INSERT INTO BOOK VALUES(BOOK_CODE_PK.NEXTVAL, '아프니까 청춘이다', '김난도', '썸앤파커스', '시/에세이', 14000, 'false', 'false');
INSERT INTO BOOK VALUES(BOOK_CODE_PK.NEXTVAL, '7년의 밤', '정유정', '은행나무', '소설', 14000, 'false', 'false');
INSERT INTO BOOK VALUES(BOOK_CODE_PK.NEXTVAL, '데미안', '헤르만 헤세', '더 스토리', '소설', 11000, 'false', 'false');
INSERT INTO BOOK VALUES(BOOK_CODE_PK.NEXTVAL, '의식이라는 꿈', '대니얼 데닛', '바다 출판사', '교양과학', 15000, 'false', 'false');

INSERT INTO REVIEW VALUES(REVIEW_CODE_PK.NEXTVAL, 2, 1, 3.5, '도움이 되었습니다.');
INSERT INTO REVIEW VALUES(REVIEW_CODE_PK.NEXTVAL, 2, 3, 5, '재밌게 읽었어요.');
INSERT INTO REVIEW VALUES(REVIEW_CODE_PK.NEXTVAL, 3, 4, 2, '재미 없어요.');
