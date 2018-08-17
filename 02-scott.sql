SELECT * FROM emp;

-- 1. 이름 A로 시작하는 사원명 출력
SELECT ename FROM emp WHERE ename LIKE 'A%';

-- 2. 이름이 두번째 문자가 L인 사원명 출력
SELECT ename FROM emp WHERE ename LIKE '_L%';

-- 3. 이름에 L이 두 번 이상 포함된 사원명 출력
SELECT ename FROM emp WHERE ename LIKE '%L%L%';
-- WHERE (length(ename) - length(replace(ename, 'L', ''))) >= 2
-- L을 공백''으로 변환 후 원래 길이에서 빼는 것

-- 4. 보너스가 급여보다 10%가 많은 모든 사원에 대해 이름, 급여, 보너스를 출력
SELECT ename, sal, comm
FROM emp
WHERE comm > (sal * 1.1);

-- 부서번호로 정렬
SELECT *
FROM emp
ORDER BY deptno desc; -- desc : 역순 정렬

-- 부서번호로 정렬하되 급여가 높은 순서대로 출력
SELECT *
FROM emp
ORDER BY deptno, sal desc;

-- 최근 입사한 순으로 사원명, 급여, 입사일자를 출력
SELECT ename, sal, hiredate
FROM emp
ORDER BY hiredate desc;

-- 커미션이 높은 순으로 정렬 (단, 커미션이 없으면 제일 나중에 출력되도록)
SELECT *
FROM emp
ORDER BY nvl(comm, -1) desc;

-- 부서번호로 정렬한 후 부서번호가 같을 경우는 급여가 많은 순으로 정렬하여 사원번호, 이름, 업무, 부서번호, 급여를 출력
SELECT empno, ename, job, deptno, sal
FROM emp
ORDER BY deptno, sal desc;

-- 사원명, 급여, 월급(급여/12)를 출력하되 월급은 십단위에서 반올림하여 출력
SELECT ename, sal, round(sal/12, -2) "월급" -- AS 생략, 한글인식 ""
FROM emp;

-- 사원명, 급여, 세금(급여의 3.3%)를 원단위 절삭하고 출력
SELECT ename, sal, TRUNC(sal * 0.033, -1) AS tax
FROM emp;

-- smith의 정보를 사원번호, 성명, 담당업무(소문자) 출력
SELECT empno, ename, LOWER(job) job -- 별칭 자체를 그 컬럼명으로 준다
FROM emp
WHERE ename = 'SMITH';
-- WHERE lower(ename) = 'smith';
-- WHERE ename = upper('smith');
-- 기존 값은 냅두고 사용자 입력값을 맞추는 게 좋다 (후자)

-- 이름의 첫글자가 'K'보다 크고 'Y'보다 작은 사원의 정보 (사원번호, 이름, 업무, 급여, 부서번호)를 출력하되 이름순으로 정렬
SELECT empno, ename, job, sal, deptno
FROM emp
WHERE SUBSTR(ename, 1, 1) > 'K' and SUBSTR(ename, 1, 1) < 'Y'
-- K와 Y를 포함하면 BETWEEN AND 사용 가능 (지금 경우에는 안 됨)
ORDER BY ename;

-- 이름이 5글자 이상인 사원들을 출력
SELECT *
FROM emp
WHERE LENGTH(ename) >= 5;

-- 이름을 15자로 맞추고 글자는 왼쪽에 오른쪽에는 '*'로 채운다
SELECT RPAD(ename, 15, '*') ename
FROM emp;

-- 월급은 10자로 맞추고 숫자는 오른쪽에 왼쪽엔 '-'로 채운다
SELECT LPAD(sal, 10, '-') sal
FROM emp;

-- 월급을 숫자에서 '영일이삼사오육칠팔구' 글자로 대체
SELECT TRANSLATE(sal, '0123456789', '영일이삼사오육칠팔구')
FROM emp;

-- 월급의 숫자에서 0을 '$'로 바꾸어 출력
SELECT REPLACE(sal, 0, '$')
FROM emp;

SELECT sysdate
FROM dual ; -- 오라클에서 가상 테이블 dummy table 제공(단순한 출력을 위함)

-- 현재까지 근무일 수가 많은 사람 순으로 출력
SELECT *
FROM emp
ORDER BY (sysdate-hiredate) desc;

-- 현재까지 근무일 수가 몇 주 몇 일인가를 출력
SELECT ename, TRUNC((sysdate-hiredate)/7, 0) weeks,
       CEIL(mod((sysdate-hiredate), 7)) days
FROM emp;

-- 10번 부서의 사원의 현재까지의 근무 월수를 계산
SELECT ename, TRUNC(MONTHS_BETWEEN(sysdate, hiredate), 0) months
FROM emp
WHERE deptno = 10;

-- 현재 날짜에서 3개월후의 날짜 구하기
SELECT ADD_MONTHS(sysdate, 3)
FROM dual;

-- 현재 날짜에서 돌아오는 '월'요일의 날짜 구하기
SELECT NEXT_DAY(sysdate, '월') -- 영어로 쓰면 ('MONDAY') 안 나옴. -> 오라클을 설치할 때 한글로 깔아서...
FROM dual;

-- 현재 날짜에서 해당 월의 마지막 날짜 구하기
SELECT LAST_DAY(sysdate)
FROM dual;

-- 입사일자에서 입사년도를 출력
SELECT ename, hiredate, to_char(hiredate, 'YYYY') hireyear
FROM emp;

-- 입사일자를 '1999년 1월 1일' 형식으로 출력
SELECT ename, hiredate, to_char(hiredate, 'YYYY"년" MM"월" DD"일"') hiredate
FROM emp;

SELECT ename, hiredate, to_char(hiredate, 'HH"시" MI"분" SS"초"') hiredate
FROM emp;

-- 1981년도에 입사한 사원 검색
SELECT ename, hiredate
FROM emp
WHERE SUBSTR(to_char(hiredate), 1, 2) = 81;

-- 5월에 입사한 사원 검색
SELECT ename, hiredate
FROM emp
--WHERE SUBSTR(to_char(hiredate), 4, 2) = 5;
WHERE to_char(hiredate, 'MM') = 5;

-- 급여 앞에 $를 삽입하고 3자리 마다 ,를 출력
SELECT ename, to_char(sal, '$999,999,999,999') sal -- 자리수 넉넉하게 줄 것
FROM emp;

-- ----------------------------------------------------------------------------

SELECT * FROM info_tab;

SELECT id,
       decode( substr(id, 8, 1), 1, '남자', 3, '남자', 2, '여자', 4, '여자' ) AS gender
FROM info_tab;

SELECT  id, CASE SUBSTR(id, 8, 1)
            WHEN '1' THEN '남자'
            WHEN '3' THEN '남자'
            ELSE '여자'
       END as gender
FROM info_tab;

-- ----------------------------------------------------------------------------

SELECT * FROM emp;

-- 부서번호가 10이면 영업부, 20이면 관리부, 30이면 IT부 그 외는 기술부로 출력
-- (1) decode
SELECT ename, deptno,
       decode( deptno, 10, '영업부', 20, '관리부', 30, 'IT부', '기술부' ) AS dname
FROM emp;
-- (2) case when
SELECT ename, deptno,
       CASE deptno
       WHEN 10 THEN '영업부'
       WHEN 20 THEN '관리부'
       WHEN 30 THEN 'IT부'
       ELSE '기술부'
    END as dname
FROM emp;

-- 업무(job)이 analyst이면 급여 증가가 10%이고 clerk이면 15%, manager이면 20%인 경우 사원번호, 사원명, 업무, 급여, 증가한급여를 출력
-- (1) decode
SELECT empno, ename, job, sal, 
       decode ( job, 'ANALYST', sal*1.1, 'CLERK', sal*1.15, 'MANAGER', sal*1.2, sal ) AS inc_sal
FROM emp;
-- (2) case when
SELECT empno, ename, job, sal,
       CASE job WHEN 'ANALYST' THEN sal*1.1
       WHEN 'CLERK' THEN sal*1.15
       WHEN 'MANAGER' THEN sal*1.2
       ELSE sal
    END as inc_sal
FROM emp;

-- ----------------------------------------------------------------------------

-- 업무가 'SALESMAN'인 사람들의 월급의 평균, 총합, 최소값, 최대값을 구하기
SELECT AVG(sal), SUM(sal), MIN(sal), MAX(sal)
FROM emp
WHERE job = 'SALESMAN';

-- 커미션(COMM)을 받는 사람들의 수는
SELECT COUNT(empno) -- 모르겠으면 *, 아니면 primary key (여기서는 empno)
FROM emp
WHERE (comm != 0) AND (comm IS NOT NULL);

-- ----------------------------------------------------------------------------

-- 부서별로 인원수, 평균급여, 최저급여, 최고급여, 급여의 합을 구하기
SELECT deptno, count(*) people_num, round(avg(sal), 0), min(sal), max(sal), sum(sal)
FROM emp
GROUP BY deptno;

-- 부서별로 인원수, 평균급여, 최저급여, 최고급여, 급여의 합을 구하기 (부서별 급여의 합이 높은 순으로)
SELECT deptno, count(*) people_num, round(avg(sal), 0) avg_sal, min(sal) min_sal, max(sal) max_sal, sum(sal) sum_sal
FROM emp
GROUP BY deptno
ORDER BY sum(sal) desc;

-- 부서별 업무별 그룹하여 부서번호, 업무, 인원수, 급여의 평균, 급여의 합을 구하기
SELECT deptno, job, count(*) people_num, round(avg(sal)) avg_sal, sum(sal) sum_sal
FROM emp
GROUP BY deptno, job;

-- 급여가 최대 2900 이상인 부서에 대해 부서번호, 평균 급여, 급여의 합을 출력
SELECT deptno, round(avg(sal)) avg_sal, sum(sal) sum_sal
FROM emp
GROUP BY deptno
HAVING max(sal) >= 2900;

-- 업무별 급여의 평균이 3000이상인 업무에 대해 업무명, 평균 급여, 급여의 합을 출력
SELECT job, round(avg(sal)) avg_sal, sum(sal) sum_sal
FROM emp
GROUP BY job
HAVING avg(sal) >= 3000;

-- 전체 합계 급여가 5000을 초과하는 각 업무에 대해서 업무와 급여 합계를 출력
-- 단, SALESMAN은 제외하고 급여 합계가 높은 순으로 정렬
SELECT job, sum(sal) sum_sal
FROM emp
WHERE job != 'SALESMAN'
GROUP BY job
HAVING sum(sal) > 5000
ORDER BY sum(sal) desc;

-- 업무별 최고 급여와 최소 급여의 차이를 구하라
SELECT job, (max(sal) - min(sal)) sal_gap
FROM emp
GROUP BY job;

-- 부서 인원이 4명보다 많은 부서의 부서번호, 인원수, 급여의 합을 출력
SELECT deptno, count(*) people_num, sum(sal) sum_sal
FROM emp
GROUP BY deptno
HAVING count(*) > 4;