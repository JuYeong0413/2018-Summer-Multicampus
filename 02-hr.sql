SELECT * FROM employees;

-- 1. EMPLOYEES ���̺��� ��� �̸��� first_name�� last_name�� ���� full_name���� ���
SELECT CONCAT(first_name || ' ', last_name) full_name
FROM employees;

-- 2. �μ���ȣ�� 30(���źμ�)�̰� �޿� 10000�̸��� ����� full_name�� ����,�μ���ȣ�� ��� [5��]
SELECT CONCAT(first_name || ' ', last_name) full_name, salary, department_id
FROM employees
WHERE department_id = 30 and salary < 10000;

-- 3. �μ���ȣ�� 30�̰� �޿��� 10000�޷� ���ϸ� �޴� 2006�⵵ ���� �Ի��� ����� full_name�� ��� [3��]
SELECT CONCAT(first_name || ' ', last_name) full_name
FROM employees
WHERE department_id = 30 and salary < 10000 and SUBSTR(to_char(hire_date), 1, 2) < 06;
-- WHERE department_id = 30 and salary < 10000 and to_char(hire_date, 'YYYY') < 2006;

-- 4. ��ȭ��ȣ���� 590���� �����ϴ� ������ ��ȭ��ȣ�� ��ȸ [5��]
SELECT first_name, last_name, phone_number
FROM employees
WHERE SUBSTR(phone_number, 1, 3) = 590;
-- WHERE phone_number LIKE '590%';

-- 5. 2003�⿡ �Ի��� ������� ���, �̸�, �Ի����� ��� [6��]
SELECT employee_id, first_name, last_name, hire_date
FROM employees
WHERE SUBSTR(to_char(hire_date), 1, 2) = 03;
-- WHERE to_char(hire_date, 'YYYY') = 2003;

-- 6. Ŀ�̼��� �޴� ������� ����� ��� [35��]
SELECT *
FROM employees
WHERE commission_pct IS NOT NULL;