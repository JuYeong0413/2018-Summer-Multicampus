-- 1. �̸��� ���� 'King' ����� ����� �μ����� ��� ( employees, departments )
SELECT e.employee_id empid, d.department_name dname
FROM employees e, departments d
WHERE e.department_id = d.department_id AND e.last_name = 'King';

SELECT e.employee_id empid, d.department_name dname
FROM employees e INNER JOIN departments d
ON e.department_id = d.department_id
WHERE e.last_name = 'King';

-- 2. �̸��� ���� 'King' ����� ����� �μ��� �׸��� �������� ��� ( employees, departments, jobs )
SELECT e.employee_id empid, d.department_name dname, j.job_title job
FROM employees e, departments d, jobs j
WHERE e.department_id = d.department_id AND e.job_id = j.job_id AND e.last_name = 'King';

SELECT e.employee_id empid, d.department_name dname, j.job_title job
FROM employees e INNER JOIN departments d
ON e.department_id = d.department_id
INNER JOIN jobs j
ON e.job_id = j.job_id
WHERE e.last_name = 'King';

-- 3. 2007�� ��ݱ⿡ �Ի��� ������� ����� �̸�, �Ի��� �׸��� �μ����� ���
--    (*) Grant�� ���� �μ��� �������� ���� ���������� Grant�� ��µǷ���
SELECT e.employee_id empid, e.last_name name, e.hire_date hiredate, d.department_name
FROM employees e, departments d
WHERE e.department_id = d.department_id(+) AND (to_char(e.hire_date, 'YYYYMM') BETWEEN 200701 AND 200706);

SELECT e.employee_id empid, e.last_name name, e.hire_date hiredate, d.department_name
FROM employees e LEFT OUTER JOIN departments d
ON e.department_id = d.department_id
WHERE to_char(e.hire_date, 'YYYYMM') BETWEEN 200701 AND 200706;