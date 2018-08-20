-- 사원테이블(emp)에 사원명과 부서테이블(dept)에 그 사원의 부서명을 출력
SELECT ename, dname
FROM emp, dept; -- emp * dept 의 결과 (별로 의미 없음)

-- 두 테이블끼리의 연관관계를 맺어주는 것 (foreign key)
-- 내부조인(inner join)
SELECT e.ename ename, d.dname dname, e.deptno deptno -- 외부에서 읽을 수 있게끔 컬럼마다 별칭 부여
FROM emp e, dept d -- 테이블명에 별칭 붙일 때는 AS 없이 붙인다.
WHERE e.deptno = d.deptno;

SELECT e.ename ename, d.dname dname, e.deptno deptno
FROM emp e INNER JOIN dept d
ON e.deptno = d.deptno;

-- 외부조인(outer join) : 한 쪽이 null값이어도 검색
SELECT e.ename ename, d.dname dname, e.deptno deptno
FROM emp e, dept d
WHERE e.deptno = d.deptno(+);

SELECT e.ename ename, d.dname dname, e.deptno deptno
FROM emp e LEFT OUTER JOIN dept d
ON e.deptno = d.deptno;

SELECT e.ename ename, d.dname dname, d.deptno deptno
FROM emp e, dept d
WHERE e.deptno(+) = d.deptno;

SELECT e.ename ename, d.dname dname, d.deptno deptno
FROM emp e RIGHT OUTER JOIN dept d
ON e.deptno = d.deptno;

SELECT e.ename ename, d.dname dname, d.deptno deptno
FROM emp e FULL OUTER JOIN dept d
ON e.deptno = d.deptno;

-- 사원테이블(emp)에 각 사원의 매니저를 조회
-- 셀프조인
SELECT e1.empno, e1.ename, e1.mgr, e2.ename
FROM emp e1, emp e2
WHERE e1.mgr = e2.empno(+); -- 없는 쪽에 (+) 적어줘야 함

SELECT e1.empno, e1.ename, e1.mgr, e2.ename
FROM emp e1 LEFT OUTER JOIN emp e2
ON e1.mgr = e2.empno;

--1. EMP 테이블에서 사원번호, 이름, 업무, 부서번호와 DEPT 테이블에서 부서명, 근무지를 출력
SELECT e.empno empno, e.ename ename, e.job job, e.deptno deptno, d.dname dname, d.loc loc
FROM emp e, dept d
WHERE e.deptno = d.deptno(+);

SELECT e.empno empno, e.ename ename, e.job job, e.deptno deptno, d.dname dname, d.loc loc
FROM emp e LEFT OUTER JOIN dept d
ON e.deptno = d.deptno;

--2. SALESMAN 사원만 사원번호, 이름, 급여, 부서명, 근무지를 출력
SELECT e.empno empno, e.ename ename, e.sal sal, d.dname dname, d.loc loc
FROM emp e, dept d
WHERE e.deptno = d.deptno(+) AND e.job = 'SALESMAN'; -- JOIN을 위한 조건을 앞에 기술하는 것이 속도가 더 빠르다

SELECT e.empno empno, e.ename ename, e.sal sal, d.dname dname, d.loc loc
FROM emp e LEFT OUTER JOIN dept d
ON e.deptno = d.deptno
WHERE e.job = 'SALESMAN';

--3. 보너스(comm)를 받는 사원에 대해 이름, 부서명, 위치를 출력
SELECT e.ename ename, d.dname dname, d.loc loc
FROM emp e, dept d
WHERE e.deptno = d.deptno(+) AND e.comm IS NOT NULL AND e.comm != 0;

SELECT e.ename ename, d.dname dname, d.loc loc
FROM emp e LEFT OUTER JOIN dept d
ON e.deptno = d.deptno
WHERE e.comm IS NOT NULL AND e.comm != 0;

--4. 부서별 부서명과 급여 합계를 출력
SELECT d.dname dname, sum(e.sal) sum_sal
FROM emp e, dept d
WHERE e.deptno = d.deptno
GROUP BY d.dname;

SELECT d.dname dname, sum(e.sal) sum_sal
FROM emp e INNER JOIN dept d
ON e.deptno = d.deptno
GROUP BY d.dname;

--5. 관리자보다 먼저 입사한 사원에 대해 이름, 입사일, 관리자 이름, 관리자 입사일을 출력
SELECT e1.ename ename, e1.hiredate hiredate, e2.ename mgr_name, e2.hiredate mgr_hiredate
FROM emp e1, emp e2
WHERE e1.mgr = e2.empno(+) AND e1.hiredate < e2.hiredate;

SELECT e1.ename ename, e1.hiredate hiredate, e2.ename mgr_name, e2.hiredate mgr_hiredate
FROM emp e1 LEFT OUTER JOIN emp e2
ON e1.mgr = e2.empno
WHERE e1.hiredate < e2.hiredate;

-- ----------------------------------------------------------------------------

-- 전체 사원들 중 평균급여보다 낮은 급여를 받는 사원의 명단을 조회
SELECT avg(sal) FROM emp;

SELECT *
FROM emp
WHERE sal < ( SELECT avg(sal) FROM emp );
-- WHERE sal = ( SELECT min(sal) FROM emp );
-- WHERE sal = ( SELECT max(sal) FROM emp );

-- 월급이 가장 많은 사원의 정보 조회
SELECT *
FROM emp
WHERE sal = ( SELECT max(sal) FROM emp );

-- 평균 급여보다 높고 최대 급여보다 낮은 월급을 받는 사원의 정보를조회
SELECT *
FROM emp
WHERE ( sal > ( SELECT avg(sal) FROM emp ) ) AND ( sal < ( SELECT max(sal) FROM emp ) );

SELECT e.*
FROM emp e, ( SELECT max(sal) max, avg(sal) avg FROM emp) e2
WHERE e.sal < e2.max AND e.sal > e2.avg;

-- 월급순으로 상위 10명의명단을 출력
-- 정렬로 데이터 뽑은다음에 가상 테이블 설정
SELECT rownum, e.*
FROM ( SELECT empno, ename, sal
       FROM emp
       ORDER BY sal desc ) e
WHERE rownum <= 10;

-- 1. SCOTT의 급여보다 많은 사원의 정보를 사원번호, 이름, 담당업무, 급여를 출력
SELECT empno, ename, job, sal
FROM emp
WHERE sal > ( SELECT sal FROM emp WHERE ename = 'SCOTT' );

-- 2. 30번 부서의 최소 급여보다 각부서의 최소 급여가 높은 부서를 출력
SELECT min(sal) FROM emp WHERE deptno = 30;

SELECT deptno, min(sal)
FROM emp
GROUP BY deptno
HAVING min(sal) > ( SELECT min(sal) FROM emp WHERE deptno = 30 );

-- 3. 업무별로 평균 급여 중에서 가장 적은 급여를 가진 직업을 출력
SELECT avg(sal) FROM emp GROUP BY job ORDER BY avg(sal);

SELECT emp.job, s.*
FROM (SELECT avg(sal) FROM emp GROUP BY job ORDER BY avg(sal)) s, emp
WHERE rownum = 1;

SELECT job, avg(sal)
FROM emp
GROUP BY job
HAVING avg(sal) = ( SELECT min(avg(sal)) FROM emp GROUP BY job );

-- 4. 사원번호가 7521의 업무와 같고 사번 7934인 직원보다 급여를 많이 많이 사원의 정보를 출력
SELECT *
FROM emp
WHERE job = ( SELECT job FROM emp WHERE empno = 7521 ) AND sal > ( SELECT sal FROM emp WHERE empno = 7934 );

-- 5. 업무가 ‘MANAGER’인 사원의 정보를 이름, 업무, 부서명, 근무지를 출력 
SELECT e.ename ename, e.job job, d.dname dname, d.loc loc
FROM ( SELECT * FROM emp WHERE job = 'MANAGER' ) e, dept d
WHERE e.deptno = d.deptno;

SELECT e.ename ename, e.job job, d.dname dname, d.loc loc
FROM ( SELECT * FROM emp WHERE job = 'MANAGER' ) e INNER JOIN dept d
ON e.deptno = d.deptno;

SELECT e.ename ename, e.job job, d.dname dname, d.loc loc
FROM emp e, dept d
WHERE e.deptno = d.deptno AND e.job = 'MANAGER';

-- 6. 'WARD'와 부서와 업무가 같은 사원 명단 출력 
SELECT deptno, job FROM emp WHERE ename = 'WARD';

SELECT *
FROM emp
WHERE deptno = ( SELECT deptno FROM emp WHERE ename = 'WARD' ) AND job = ( SELECT job FROM emp WHERE ename = 'WARD' );

SELECT *
FROM emp
WHERE ( deptno, job ) = ( SELECT deptno, job FROM emp WHERE ename = 'WARD' );

SELECT e1.*
FROM emp e1, ( SELECT deptno, job FROM emp WHERE ename = 'WARD' ) e2
WHERE e1.deptno = e2.deptno AND e1.job = e2.job AND e1.ename <> 'WARD';

-- 10번 부서사원들의 업무와 동일한 직원들 검색
SELECT job FROM emp WHERE deptno = 10;

SELECT *
FROM emp
WHERE job = ANY (SELECT job FROM emp WHERE deptno = 10); -- 서브쿼리중 단 하나만 맞아도 될 때 ANY 사용, 반대는 ALL
-- WHERE job IN (SELECT job FROM emp WHERE deptno = 10);

-- 업무별로 최소 급여를 받는 사원의 정보를 사원번호, 이름, 담당업무, 급여를 출력
SELECT min(sal) FROM emp GROUP BY job;

SELECT empno, ename, job, sal
FROM emp
WHERE sal = ANY ( SELECT min(sal) FROM emp GROUP BY job );

-- 적어도 한명의 사원으로부터 보고를 받을 수 있는 사원의 정보를 사원번호, 이름, 업무를 출력
SELECT e1.empno, e1.ename, e1.job
FROM emp e1
WHERE exists ( SELECT * FROM emp e2 WHERE e1.empno = e2.mgr ); -- 반대는 not exists

-- 1. 지역이 'NEW YORK'과 'DALLAS'가 아닌 부서의 사원들 조회
SELECT d.deptno FROM dept d WHERE d.loc IN ( 'NEW YORK', 'DALLAS');
SELECT d.deptno FROM dept d WHERE d.loc = 'NEW YORK' OR d.loc = 'DALLAS';

SELECT e.*
FROM emp e
WHERE e.deptno != ALL ( SELECT d.deptno FROM dept d WHERE d.loc IN ( 'NEW YORK', 'DALLAS' ) );

SELECT *
FROM emp
WHERE deptno NOT IN ( SELECT deptno FROM dept WHERE loc IN ( 'NEW YORK', 'DALLAS' ) );

SELECT e.*, d.loc
FROM emp e, dept d
WHERE e.deptno = d.deptno AND e.deptno NOT IN ( SELECT d.deptno FROM dept d WHERE d.loc IN ( 'NEW YORK', 'DALLAS' ) );

SELECT e.*, d.loc
FROM emp e INNER JOIN dept d
ON e.deptno = d.deptno
WHERE e.deptno NOT IN ( SELECT d.deptno FROM dept d WHERE d.loc IN ( 'NEW YORK', 'DALLAS' ) );

SELECT e.*, d.loc
FROM emp e, dept d
WHERE e.deptno = d.deptno AND d.loc <> 'NEW YORK' AND d.loc <> 'DALLAS';

-- 2. 급여가 3000이상인 사원이 속한 부서명을 출력
SELECT * FROM emp WHERE sal >= 3000;

SELECT d.dname
FROM dept d
WHERE d.deptno IN ( SELECT deptno FROM emp WHERE sal >= 3000 );

SELECT d.dname
FROM dept d
WHERE EXISTS ( SELECT e.deptno FROM emp e WHERE e.deptno = d.deptno AND e.sal >= 3000 );

-- ----------------------------------------------------------------------------

create table stat_salary ( 
           stat_date   date  not  null,        -- 날짜
           dept_no   number,                   -- 부서
           emp_count number,                   -- 사원수
           tot_salary number,                  -- 급여총액
           avg_salary number );                -- 급여평균
           
INSERT INTO stat_salary(stat_date, dept_no)
SELECT sysdate, deptno from dept;

SELECT * FROM stat_salary;

-- 부서별 인원수, 월급의 합과 월급의 평균을 구하기
SELECT count(*), sum(sal) sum_sal, avg(sal) avg_sal
FROM emp
GROUP BY deptno;

UPDATE stat_salary s
SET ( s.emp_count, s.tot_salary, s.avg_salary )
= ( SELECT count(*), sum(e.sal) sum_sal, avg(e.sal) avg_sal FROM emp e WHERE s.dept_no = e.deptno GROUP BY e.deptno );

-- emp_copy 사용
INSERT INTO emp_copy ( empno, ename, job, mgr, hiredate, sal, deptno )
VALUES ( 7788, 'SCOTT', 'ANALYST', 7566, '87/04/19', 3000, 20 );
INSERT INTO emp_copy ( empno, ename, job, mgr, hiredate, sal, deptno )
VALUES ( 7566, 'JONES', 'MANAGER', 7839, '81/04/02', 2975, 20 );

SELECT * FROM emp_copy;

-- 1. SCOTT의 업무와 급여로 JONES의 업무와 급여를 수정
SELECT job, sal FROM emp_copy WHERE ename = 'SCOTT';
UPDATE emp_copy
SET ( job, sal ) = ( SELECT job, sal FROM emp_copy WHERE ename = 'SCOTT' )
WHERE ename = 'JONES';

-- 2. 부서명이 'SALES'인 사원의 정보를 삭제
DELETE FROM emp_copy
WHERE job = 'SALESMAN';

-- ----------------------------------------------------------------------------

-- 가상테이블 : view
CREATE OR REPLACE VIEW v_emp AS
        SELECT empno, ename, deptno FROM  emp
        with read only; -- 읽기 전용
        
select * from v_emp;

INSERT INTO v_emp VALUES (9000, '홍길자', 30);
INSERT INTO v_emp VALUES (8000, '홍길자', 30); -- 원본 테이블의 PK 영향 받는다.
INSERT INTO v_emp VALUES (9001, '홍길동', 90); -- 원본 테이블의 FK 영향 받는다.
DELETE FROM v_emp WHERE empno = 9000;

select * from emp;

Create or replace view v_emp as
            select e.empno empno, e.ename ename, d.dname dname 
            from emp e, dept d
            where e.deptno = d.deptno;
            
select * from v_emp;

-- ----------------------------------------------------------------------------

CREATE SEQUENCE seq_emp_empno;

INSERT INTO emp(empno, ename, job)
VALUES(seq_emp_empno.NEXTVAL, '홍씨', '개발');

SELECT * FROM emp;

DROP SEQUENCE seq_emp_empno;

CREATE SEQUENCE seq_emp_empno
            start with 1000
            increment by 10;
            
INSERT INTO emp(empno, ename, job)
VALUES(seq_emp_empno.NEXTVAL, '박씨', '개발');

SELECT seq_emp_empno.currval FROM dual;

-- ----------------------------------------------------------------------------

CREATE SEQUENCE seq_rental_num;
CREATE SEQUENCE seq_video_num;

-- ----------------------------------------------------------------------------

-- 회원 테이블
create table ex_member(
id varchar2(10), -- 아이디
pass varchar2(10), -- 패스워드
name varchar2(20), -- 이름
tel varchar2(20), -- 전화번호
addr varchar2(100) -- 주소
);

-- 상품 테이블
create table ex_good(
gno varchar2(10), -- 상품번호
gname varchar(30), -- 상품명
gdetail varchar2(300), -- 상세설명
price number);  -- 가격

-- 주문 테이블
create table ex_order (
ono number,  -- 번호
orderno varchar2(20), -- 주문번호
gno varchar2(10), -- 상품번호
id varchar2(10), -- 회원 아이디
count number,  -- 갯수
status varchar2(10) -- 배송상태
);

create sequence seq_order_ono;

-- ######################################################
-- 주문테이블과 회원테이블, 상품테이블의 관계를 맺는다면?

-- 회원 테이블 데이타 입력
insert into ex_member(id, pass, name, tel, addr )
values('kim', '1111', '김길동', '02-222-2222','서울 멋지구 이쁜동');

insert into ex_member(id, pass, name, tel, addr )
values('park', '1111', '박길동', '03-333-3333','인천 멋지구 이쁜동');

insert into ex_member(id, pass, name, tel, addr )
values('meng', '1111', '맹길동', '04-444-4444','경기 멋지구 이쁜동');

commit;

-- 상픔 테이블 데이타 입력
insert into ex_good( gno, gname, gdetail, price )
values('1001', '머리끈', '아주 비싼 머리끈', 200 );

insert into ex_good( gno, gname, gdetail, price )
values('2002', '자전거-A', '비싸지만 빠르게 달리는 자전거', 10000 );

insert into ex_good( gno, gname, gdetail, price )
values('2010', '자전거-B', '아주 비싸지만 느리고 안전하게 달린다는 자전거', 20000 );

insert into ex_good( gno, gname, gdetail, price )
values('3333', '핸드폰케이스', '싸고 유행하는 핸드폰 케이스', 1500 );

commit;

-- 주문 테이블 데이타 검색
insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20161212', '1001', 'park', 1, '배송완료');

insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20161212', '2010', 'park', 1, '배송중');

insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20161111', '1001', 'kim', 2, '주문');

insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20161111', '3333', 'kim', 3, '주문');

insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20163333', '1001', 'park', 3, '주문');

insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20163333', '2010', 'park', 1, '배송중');

insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20163333', '2002', 'park', 2, '주문');

insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20165050', '1001', 'meng', 1, '배송중');

commit;

-- ###############################################################
-- [ 문제 ]
-- 1. 배송중인 주문 내역과 상품 설명 출력
SELECT id, gno FROM ex_order WHERE status = '배송중';

SELECT m.id, m.name, o.gno, g.gname, g.gdetail
FROM ex_member m, ( SELECT id, gno FROM ex_order WHERE status = '배송중' ) o, ex_good g
WHERE o.gno = g.gno AND o.id = m.id;

-- 2. 주문 들어온 상품 내역과 고객 정보 출력
SELECT id, gno FROM ex_order WHERE status = '주문';

SELECT m.*, o.gno, g.gname
FROM ex_member m, ( SELECT id, gno FROM ex_order WHERE status = '주문' ) o, ex_good g
WHERE o.gno = g.gno AND o.id = m.id;

-- 3. 주문별로 고객 정보(ID만 출력)와 주문한 상품의 총금액을 출력
SELECT id FROM ex_order GROUP BY id;

SELECT m.id
FROM ex_member m, ex_order o
WHERE m.id = o.id
GROUP BY m.id;

SELECT o.orderno, sum(g.price * o.count) AS total
FROM ex_good g, ex_order o
WHERE g.gno = o.gno
GROUP BY o.orderno;



SELECT DISTINCT o.id, t.total
FROM ex_order o, 
    ( SELECT o.orderno, sum(g.price * o.count) AS total
        FROM ex_good g, ex_order o
        WHERE g.gno = o.gno
        GROUP BY o.orderno ) t
WHERE o.orderno = t.orderno;

-- 4. 3번에 주문 내역을 첫번째 상품명 외 몇 개로 출력
SELECT o.orderno, sum(o.count) as total
FROM ex_good g, ex_order o
WHERE g.gno = o.gno
GROUP BY o.orderno;

SELECT o.orderno, concat('외 ' || to_char(sum(o.count) - 1), '개') AS "개수"
FROM ex_good g, ex_order o
WHERE g.gno = o.gno
GROUP BY o.orderno;

SELECT g.gno
FROM ex_good g, ex_order o
WHERE g.gno = o.gno
GROUP BY g.gno;

SELECT g.gno, g.gname
FROM ex_good g,
    ( SELECT g.gno
      FROM ex_good g, ex_order o
      WHERE g.gno = o.gno
      GROUP BY g.gno ) t
WHERE g.gno = t.gno;

SELECT DISTINCT o.id, t.orderno, t.total, (g.gname || ' 외 ' || t.count || '개') AS "상품"
FROM ex_order o, ex_good g,
    ( SELECT o.orderno, sum(g.price * o.count) AS total,
      max(g.gno) AS max_gno,
      sum(o.count) - 1 AS count
      FROM ex_good g, ex_order o
      WHERE g.gno = o.gno
      GROUP BY o.orderno ) t
WHERE o.orderno = t.orderno AND g.gno = t.max_gno;