-- 1. 이름의 성이 'King' 사원의 사번과 부서명을 출력 ( employees, departments )
SELECT e.employee_id empid, d.department_name dname
FROM employees e, departments d
WHERE e.department_id = d.department_id AND e.last_name = 'King';

SELECT e.employee_id empid, d.department_name dname
FROM employees e INNER JOIN departments d
ON e.department_id = d.department_id
WHERE e.last_name = 'King';

-- 2. 이름의 성이 'King' 사원의 사번과 부서명 그리고 업무명을 출력 ( employees, departments, jobs )
SELECT e.employee_id empid, d.department_name dname, j.job_title job
FROM employees e, departments d, jobs j
WHERE e.department_id = d.department_id AND e.job_id = j.job_id AND e.last_name = 'King';

SELECT e.employee_id empid, d.department_name dname, j.job_title job
FROM employees e INNER JOIN departments d
ON e.department_id = d.department_id
INNER JOIN jobs j
ON e.job_id = j.job_id
WHERE e.last_name = 'King';

-- 3. 2007년 상반기에 입사한 사원들의 사번과 이름, 입사일 그리고 부서명을 출력
--    (*) Grant는 아직 부서가 배정받지 못한 상태이지만 Grant도 출력되려면
SELECT e.employee_id empid, e.last_name name, e.hire_date hiredate, d.department_name
FROM employees e, departments d
WHERE e.department_id = d.department_id(+) AND (to_char(e.hire_date, 'YYYYMM') BETWEEN 200701 AND 200706);

SELECT e.employee_id empid, e.last_name name, e.hire_date hiredate, d.department_name
FROM employees e LEFT OUTER JOIN departments d
ON e.department_id = d.department_id
WHERE to_char(e.hire_date, 'YYYYMM') BETWEEN 200701 AND 200706;