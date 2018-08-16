-- select * from emp;
-- 주석

CREATE  TABLE  student
(
    id char(4),
    name varchar2(10),
    kor number(3),
    eng number(3),
    total number(3),
    avg number(5,2)
    -- [] : 여러개 쓴다는 얘기
    -- 학번 id char(4) 이름 name varchar2(10) 국어 kor number(3) 영어 eng number(3) 총점 total number(3) 평균 avg number(5,2)
    -- 자릿수 3개니까 범위는 -999 ~ +999
    -- number(전체자리수, 소수점자리수)
);

DESC student;

-- (1) 수학 컬럼 추가 : 추가하면 뒤에 있음 이거 못바꿈
ALTER TABLE student ADD ( math number(3) );

-- (2) 총점 컬럼 삭제
ALTER TABLE student DROP ( total );

-- (3) 평균 컬럼을 소수점 1자리 변경
ALTER TABLE student MODIFY ( avg number(4,1) );

DESC student; -- 구조를 보는 것

-- DML
-- 레코드 입력 (data 입력)
INSERT INTO student ( id, name, kor, eng, math )
-- database의 문자열은 무조건 small quotation
VALUES ( '1001', '홍길동', 80, 100, 50 );

INSERT INTO student ( id, name, kor, eng, math )
VALUES ( '1001', '홍길동동동', 80, 100, 50 );

INSERT INTO student ( id, name, kor, eng, math )
VALUES ( '1001', 'abcdefghij', 80, 100, 50 );

SELECT * FROM student; -- data를 보는 것

-- 1002 학번의 홍길자학생의 국어, 영어, 점수는 80, 70을 입력하기
INSERT INTO student ( id, name, kor, eng )
VALUES ( '1002', '홍길자', 80, 70);

-- 레코드 삭제
DELETE FROM student WHERE name = 'abcdefghij';

-- 레코드 수정
UPDATE student SET avg = ( kor + eng + nvl( math,0 )) / 3;
-- 한번 null 인 건 연산이 null이 된다. null인 경우는 0으로 대치 필요
-- 홍길자 학생의 수학점수를 90으로 입력 : update
rollback;

UPDATE student SET math = 90 WHERE name = '홍길자';
SELECT * FROM student;

desc emp;

SELECT * FROM emp;

CREATE TABLE emp_copy
    AS SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno
    FROM emp;
    
SELECT * FROM emp_copy;

-- 1. 사원번호가 7788인 사원의 부서를 10번으로 변경
UPDATE emp_copy SET deptno = 10 WHERE empno = 7788;
-- 2. 사원번호가 7788인 사원의 이름을 '홍슈퍼'로 변경하고 급여를 3500으로 변경
UPDATE emp_copy SET ename = '홍슈퍼', sal = 3500 WHERE empno = 7788;
-- 3. 모든 부서원의 보너스를 300으로 인상
-- null 값 처리!!!
UPDATE emp_copy SET comm = nvl(comm, 0) + 300;
-- 4. 사원번호가 7499인 사원의 번호를 삭제
DELETE FROM emp_copy WHERE empno = 7499;
-- 5. 입사일자가 81년 6월 1일 이전인 사원의 정보를 삭제
DELETE FROM emp_copy WHERE hiredate < '81/06/01';

SELECT * FROM emp_copy;
SELECT * FROM emp;

-- 사번 8000, 본인이름, 월급 10000 입력
INSERT INTO emp ( empno, ename, sal )
VALUES ( 8000, '이주영', 10000 );
-- 모든 column 입력 시 column 생략 가능, 하지만 입력하는 습관 들이기

-- INSERT INTO emp ( empno, ename, sal ) VALUES ( 8000, '홍길동', 10000 );
-- 무결성 제약 조건에 위배됩니다. -> 기본키 중복 : 사번은 유일한 값이어야 한다. 겹치면 안 됨

-- INSERT INTO emp ( ename, sal ) VALUES ( '홍길자', 20000 );
-- 기본키를 안 줬기 때문에 오류 발생

INSERT INTO emp ( empno, ename, deptno )
VALUES ( 9000, '홍동동', 60 ); -- 존재하지 않는 deptno

CREATE  TABLE  info_tab
(
    name varchar2(8), --이름
    id char(14), --주민번호
    tel varchar2(20), -- 전화번호
    sex varchar2(10), -- 성별
    age number(3), -- 나이 -> 자리수 지정 안하면 기본이 10자리
    home varchar2(14), -- 출신지
    CONSTRAINT pk_info_tel PRIMARY KEY ( tel ),
    CONSTRAINT uq_info_id UNIQUE ( id )
);

-- 이름을 필수사항으로 설정 ( not null 속성 )
ALTER TABLE info_tab MODIFY ( name varchar2(8) NOT NULL );
-- 성별을 '남자', '여자'만 입력되도록 설정 ( check 속성 )
ALTER TABLE info_tab
ADD CONSTRAINT ck_info_sex CHECK ( sex IN ( '남자', '여자' ) );
-- 출신지에 입력값이 없으면 기본값 '서울' 설정 ( default 속성 )
ALTER TABLE info_tab MODIFY ( home varchar2(14) DEFAULT '서울');

INSERT INTO info_tab ( name, tel, id, sex, age, home)
VALUES ('홍길동', '02-555-5556', '880808-1234568', '남자', '27', '인천');

SELECT * FROM info_tab;

INSERT INTO info_tab ( name, tel )
VALUES ('김길자', '02-222-2222');

INSERT INTO info_tab ( name, tel, sex )
VALUES ('남길자', '02-333-3333', '여자');

-- ------------------------------------------------------------------------

INSERT INTO info_tab( name, tel, id )
VALUES('갑순이', '02-777-4444', '990909-1234567'); -- O

INSERT INTO info_tab( name, tel, id, sex, age, home )
VALUES('갑갑이', '03-555-5555', '880808-1234567', '남자', 27, '경기'); -- 주민번호 중복

INSERT INTO info_tab( name, tel, id, sex, age, home )
VALUES('홍길동', '03-031-0310', '900909-1234567', '남성', 27, '경기'); -- 성별 (남자)

INSERT INTO info_tab( name, id ) VALUES('홍길자', '550505-1234567'); -- tel이 primary key

INSERT INTO info_tab( tel, id )VALUES('031-777-7777', '700707-1234567'); -- name은 not null

INSERT INTO info_tab( name, tel, id ) VALUES('갑동이', '031-000-0000','700707-1234567'); -- O

SELECT * FROM info_tab;

/*
검색
SELECT 내가 지금 보고싶은 컬럼들을 쭉 기술
FROM 테이블명
WHERE 검색할 때 필요한 조건 기술;

SELECT * (모든 컬럼 다 보겠다) FROM emp;
-> emp에 있는 컬럼 조건없이 다 보겠다는 뜻

비 프로그래머쪽은 != 와 <> 같은 의미임
like -> 검색할 때 한 단어에 해당하는 거 다 검색하는 것
홍이라는 글자가 들어가있는것 다 검색해 주세요
문자열 연결 (+) -> ||
*/

-- 모든 사원의 사원명과 급여, 급여와 보너스를 더한 합계
SELECT ename, sal, sal + nvl(comm, 0) AS total_sal -- 별칭을 부여해야 쓸 수 있음!!! 습관 들일 것, 생략해도 알아봐야 함
FROM emp;

-- 사원의 이름과 업무를 연결하여 'STAFF'으로 출력
SELECT ename || '   ' || job AS staff FROM emp;

-- 연습문제
-- 1. 20번 부서에서 근무하는 사원의 사원번호, 이름, 부서번호 출력
SELECT empno, ename, deptno
FROM emp
WHERE deptno = 20;

-- 2. 입사일이 82/01/01에서 82/06/01인 사원의 사원번호, 이름, 입사일을 출력
SELECT empno, ename, hiredate
FROM emp
WHERE hiredate between '82/01/01' and '82/06/01';

-- 3. 담당업무가 salesman, clerk인 사원들의 이름과 업무를 출력
SELECT ename, job
FROM emp
WHERE job = 'SALESMAN' or job = 'CLERK'; -- 값은 명령어가 아니기 때문에 대소문자 구별한다
-- WHERE job IN ('SALESMAN', 'CLERK'); -- 3개 이상이면 IN 연산자 쓰는 것이 좋다.

-- 4. 업무가 president이고 급여가 1500이상이거나 업무가 salesman인 사원의 정보를 출력
SELECT *
FROM emp
WHERE job = 'PRESIDENT' and (sal >=1500 or job = 'SALESMAN');

-- 5. 업무가 president 또는 salesman이고 급여가 1500이상인 사원의 정보를 출력
SELECT *
FROM emp
WHERE (job = 'PRESIDENT' or job = 'SALESMAN') and (sal >= 1500);
-- WHERE (job IN ('PRESIDENT', 'SALESMAN')) and sal >= 1500;

-- 6. 커미션(comm)이 없는 사원의 이름, 급여, 커미션을 출력
SELECT ename, sal, comm
FROM emp
WHERE comm IS NULL or comm = 0;

-- 7. 사원명, 급여, 커미션, 총급여(급여 + 커미션)을 출력
SELECT ename, sal, comm, sal + nvl(comm, 0) as total_salary
FROM emp;

-- 연습문제
-- 1. sawon 테이블을 생성하세요. sabun은 6자리 숫자, sawon_name은 최대 한글 5자리, ubmu는 최대 한글 10자리,
--    weolgub는 정수형 8자리와 소수점 2자리, buseo는 숫자 3자리
-- 2. 위의 테이블에서 sabun을 기본키로 설정
CREATE TABLE sawon
(
 sabun number(6),
 sawon_name varchar2(10),
 ubmu varchar2(20),
 weolgub number(10,2),
 buseo number(3),
 CONSTRAINT pk_sawon_sabun PRIMARY KEY ( sabun )
);
-- 3. 최대 한글 10자리의 jiyeok 컬럼을 추가하되 NULL 값이 입력되지 않도록 지정
ALTER TABLE sawon ADD ( jiyeok varchar2(10) NOT NULL );
-- 4. weolgub 컬럼은 정수형 7자리로 변경
ALTER TABLE sawon MODIFY ( weolgub number(7) );
-- 5. ubmu 컬럼은 '개발', '유지보수', '관리'만 데이터 값으로 입력되도록 지정
ALTER TABLE sawon
ADD CONSTRAINT ck_sawon_ubmu CHECK ( ubmu IN ( '개발', '유지보수', '관리' ) );
-- 6. ubmu 컬럼에 데이터가 입력이 안 될 경우 디폴트값으로 '개발'을 설정
ALTER TABLE sawon MODIFY ( ubmu varchar2(20) DEFAULT '개발');
-- 7. buseo 테이블을 생성하세요
--    buseo_no는 숫자 3자로 이루어진 번호로 기본키, buseo_name은 최대 한글 10자리
CREATE TABLE buseo
(
 buseo_no number(3),
 buseo_name varchar2(20),
 CONSTRAINT pk_buseo_buseono PRIMARY KEY ( buseo_no )
);
-- 8. sawon 테이블의 buseo 컬럼을 buseo 테이블의 buseo_no를 참조하는 외래키로 설정
ALTER TABLE sawon
ADD CONSTRAINT fk_buseo FOREIGN KEY ( buseo )
REFERENCES buseo ( buseo_no );
-- 9. buseo 테이블에 데이터 입력
INSERT INTO buseo ( buseo_no, buseo_name )
VALUES (101, '소프트웨어유지보수부');
INSERT INTO buseo ( buseo_no, buseo_name )
VALUES (202, '관리부');
INSERT INTO buseo ( buseo_no, buseo_name )
VALUES (303, '인적자원부');
-- 10. sawon 테이블에 데이터 입력 (입력이 안 될 경우도 있음)
INSERT INTO sawon ( sabun, sawon_name, weolgub, buseo, jiyeok )
VALUES (8001, '홍길동이군', 100000, 101, '부산');
INSERT INTO sawon ( sabun, sawon_name, ubmu, weolgub, buseo, jiyeok )
VALUES (8002, '홍길자', '사무', 200000, 202, '인천'); -- ubmu 컬럼은 '개발', '유지보수', '관리'만 입력 가능
INSERT INTO sawon ( sabun, sawon_name, ubmu, buseo, jiyeok )
VALUES (8003, '홍길순', '개발', 111, '대전'); -- buseo 테이블의 buseo_no에 111은 없음
INSERT INTO sawon ( sabun, sawon_name, weolgub, jiyeok )
VALUES (8004, '홍길석', 12345678, '서울'); -- weolgub은 정수형 8자리까지 입력 가능
INSERT INTO sawon ( sabun, sawon_name, ubmu, buseo, jiyeok )
VALUES (8005, '홍길철', '유지보수', 303, '판교');
-- 11. sawon 테이블에서 jiyeok 컬럼을 제거
ALTER TABLE sawon DROP ( jiyeok );
-- 12. buseo 테이블에서 buseo 명이 '인적자원부'인 행을 삭제
ALTER TABLE sawon DROP CONSTRAINT fk_buseo;
DELETE FROM buseo WHERE buseo_name = '인적자원부';
-- 13. sawon 테이블이 모든 내용을 삭제하고 저장공간을 해제
TRUNCATE TABLE sawon;
