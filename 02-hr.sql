SELECT * FROM employees;

-- 1. EMPLOYEES 테이블에서 사원 이름을 first_name과 last_name를 합쳐 full_name으로 출력
SELECT CONCAT(first_name || ' ', last_name) full_name
FROM employees;

-- 2. 부서번호가 30(구매부서)이고 급여 10000미만인 사원의 full_name과 월급,부서번호를 출력 [5개]
SELECT CONCAT(first_name || ' ', last_name) full_name, salary, department_id
FROM employees
WHERE department_id = 30 and salary < 10000;

-- 3. 부서번호가 30이고 급여가 10000달러 이하를 받는 2006년도 이전 입사한 사원의 full_name을 출력 [3개]
SELECT CONCAT(first_name || ' ', last_name) full_name
FROM employees
WHERE department_id = 30 and salary < 10000 and SUBSTR(to_char(hire_date), 1, 2) < 06;
-- WHERE department_id = 30 and salary < 10000 and to_char(hire_date, 'YYYY') < 2006;

-- 4. 전화번호에서 590으로 시작하는 사원명과 전화번호를 조회 [5개]
SELECT first_name, last_name, phone_number
FROM employees
WHERE SUBSTR(phone_number, 1, 3) = 590;
-- WHERE phone_number LIKE '590%';

-- 5. 2003년에 입사한 사원들의 사번, 이름, 입사일을 출력 [6개]
SELECT employee_id, first_name, last_name, hire_date
FROM employees
WHERE SUBSTR(to_char(hire_date), 1, 2) = 03;
-- WHERE to_char(hire_date, 'YYYY') = 2003;

-- 6. 커미션을 받는 사원들의 명단을 출력 [35개]
SELECT *
FROM employees
WHERE commission_pct IS NOT NULL;