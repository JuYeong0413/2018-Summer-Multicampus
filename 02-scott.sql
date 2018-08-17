SELECT * FROM emp;

-- 1. �̸� A�� �����ϴ� ����� ���
SELECT ename FROM emp WHERE ename LIKE 'A%';

-- 2. �̸��� �ι�° ���ڰ� L�� ����� ���
SELECT ename FROM emp WHERE ename LIKE '_L%';

-- 3. �̸��� L�� �� �� �̻� ���Ե� ����� ���
SELECT ename FROM emp WHERE ename LIKE '%L%L%';
-- WHERE (length(ename) - length(replace(ename, 'L', ''))) >= 2
-- L�� ����''���� ��ȯ �� ���� ���̿��� ���� ��

-- 4. ���ʽ��� �޿����� 10%�� ���� ��� ����� ���� �̸�, �޿�, ���ʽ��� ���
SELECT ename, sal, comm
FROM emp
WHERE comm > (sal * 1.1);

-- �μ���ȣ�� ����
SELECT *
FROM emp
ORDER BY deptno desc; -- desc : ���� ����

-- �μ���ȣ�� �����ϵ� �޿��� ���� ������� ���
SELECT *
FROM emp
ORDER BY deptno, sal desc;

-- �ֱ� �Ի��� ������ �����, �޿�, �Ի����ڸ� ���
SELECT ename, sal, hiredate
FROM emp
ORDER BY hiredate desc;

-- Ŀ�̼��� ���� ������ ���� (��, Ŀ�̼��� ������ ���� ���߿� ��µǵ���)
SELECT *
FROM emp
ORDER BY nvl(comm, -1) desc;

-- �μ���ȣ�� ������ �� �μ���ȣ�� ���� ���� �޿��� ���� ������ �����Ͽ� �����ȣ, �̸�, ����, �μ���ȣ, �޿��� ���
SELECT empno, ename, job, deptno, sal
FROM emp
ORDER BY deptno, sal desc;

-- �����, �޿�, ����(�޿�/12)�� ����ϵ� ������ �ʴ������� �ݿø��Ͽ� ���
SELECT ename, sal, round(sal/12, -2) "����" -- AS ����, �ѱ��ν� ""
FROM emp;

-- �����, �޿�, ����(�޿��� 3.3%)�� ������ �����ϰ� ���
SELECT ename, sal, TRUNC(sal * 0.033, -1) AS tax
FROM emp;

-- smith�� ������ �����ȣ, ����, ������(�ҹ���) ���
SELECT empno, ename, LOWER(job) job -- ��Ī ��ü�� �� �÷������� �ش�
FROM emp
WHERE ename = 'SMITH';
-- WHERE lower(ename) = 'smith';
-- WHERE ename = upper('smith');
-- ���� ���� ���ΰ� ����� �Է°��� ���ߴ� �� ���� (����)

-- �̸��� ù���ڰ� 'K'���� ũ�� 'Y'���� ���� ����� ���� (�����ȣ, �̸�, ����, �޿�, �μ���ȣ)�� ����ϵ� �̸������� ����
SELECT empno, ename, job, sal, deptno
FROM emp
WHERE SUBSTR(ename, 1, 1) > 'K' and SUBSTR(ename, 1, 1) < 'Y'
-- K�� Y�� �����ϸ� BETWEEN AND ��� ���� (���� ��쿡�� �� ��)
ORDER BY ename;

-- �̸��� 5���� �̻��� ������� ���
SELECT *
FROM emp
WHERE LENGTH(ename) >= 5;

-- �̸��� 15�ڷ� ���߰� ���ڴ� ���ʿ� �����ʿ��� '*'�� ä���
SELECT RPAD(ename, 15, '*') ename
FROM emp;

-- ������ 10�ڷ� ���߰� ���ڴ� �����ʿ� ���ʿ� '-'�� ä���
SELECT LPAD(sal, 10, '-') sal
FROM emp;

-- ������ ���ڿ��� '�����̻�����ĥ�ȱ�' ���ڷ� ��ü
SELECT TRANSLATE(sal, '0123456789', '�����̻�����ĥ�ȱ�')
FROM emp;

-- ������ ���ڿ��� 0�� '$'�� �ٲپ� ���
SELECT REPLACE(sal, 0, '$')
FROM emp;

SELECT sysdate
FROM dual ; -- ����Ŭ���� ���� ���̺� dummy table ����(�ܼ��� ����� ����)

-- ������� �ٹ��� ���� ���� ��� ������ ���
SELECT *
FROM emp
ORDER BY (sysdate-hiredate) desc;

-- ������� �ٹ��� ���� �� �� �� ���ΰ��� ���
SELECT ename, TRUNC((sysdate-hiredate)/7, 0) weeks,
       CEIL(mod((sysdate-hiredate), 7)) days
FROM emp;

-- 10�� �μ��� ����� ��������� �ٹ� ������ ���
SELECT ename, TRUNC(MONTHS_BETWEEN(sysdate, hiredate), 0) months
FROM emp
WHERE deptno = 10;

-- ���� ��¥���� 3�������� ��¥ ���ϱ�
SELECT ADD_MONTHS(sysdate, 3)
FROM dual;

-- ���� ��¥���� ���ƿ��� '��'������ ��¥ ���ϱ�
SELECT NEXT_DAY(sysdate, '��') -- ����� ���� ('MONDAY') �� ����. -> ����Ŭ�� ��ġ�� �� �ѱ۷� ��Ƽ�...
FROM dual;

-- ���� ��¥���� �ش� ���� ������ ��¥ ���ϱ�
SELECT LAST_DAY(sysdate)
FROM dual;

-- �Ի����ڿ��� �Ի�⵵�� ���
SELECT ename, hiredate, to_char(hiredate, 'YYYY') hireyear
FROM emp;

-- �Ի����ڸ� '1999�� 1�� 1��' �������� ���
SELECT ename, hiredate, to_char(hiredate, 'YYYY"��" MM"��" DD"��"') hiredate
FROM emp;

SELECT ename, hiredate, to_char(hiredate, 'HH"��" MI"��" SS"��"') hiredate
FROM emp;

-- 1981�⵵�� �Ի��� ��� �˻�
SELECT ename, hiredate
FROM emp
WHERE SUBSTR(to_char(hiredate), 1, 2) = 81;

-- 5���� �Ի��� ��� �˻�
SELECT ename, hiredate
FROM emp
--WHERE SUBSTR(to_char(hiredate), 4, 2) = 5;
WHERE to_char(hiredate, 'MM') = 5;

-- �޿� �տ� $�� �����ϰ� 3�ڸ� ���� ,�� ���
SELECT ename, to_char(sal, '$999,999,999,999') sal -- �ڸ��� �˳��ϰ� �� ��
FROM emp;

-- ----------------------------------------------------------------------------

SELECT * FROM info_tab;

SELECT id,
       decode( substr(id, 8, 1), 1, '����', 3, '����', 2, '����', 4, '����' ) AS gender
FROM info_tab;

SELECT  id, CASE SUBSTR(id, 8, 1)
            WHEN '1' THEN '����'
            WHEN '3' THEN '����'
            ELSE '����'
       END as gender
FROM info_tab;

-- ----------------------------------------------------------------------------

SELECT * FROM emp;

-- �μ���ȣ�� 10�̸� ������, 20�̸� ������, 30�̸� IT�� �� �ܴ� ����η� ���
-- (1) decode
SELECT ename, deptno,
       decode( deptno, 10, '������', 20, '������', 30, 'IT��', '�����' ) AS dname
FROM emp;
-- (2) case when
SELECT ename, deptno,
       CASE deptno
       WHEN 10 THEN '������'
       WHEN 20 THEN '������'
       WHEN 30 THEN 'IT��'
       ELSE '�����'
    END as dname
FROM emp;

-- ����(job)�� analyst�̸� �޿� ������ 10%�̰� clerk�̸� 15%, manager�̸� 20%�� ��� �����ȣ, �����, ����, �޿�, �����ѱ޿��� ���
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

-- ������ 'SALESMAN'�� ������� ������ ���, ����, �ּҰ�, �ִ밪�� ���ϱ�
SELECT AVG(sal), SUM(sal), MIN(sal), MAX(sal)
FROM emp
WHERE job = 'SALESMAN';

-- Ŀ�̼�(COMM)�� �޴� ������� ����
SELECT COUNT(empno) -- �𸣰����� *, �ƴϸ� primary key (���⼭�� empno)
FROM emp
WHERE (comm != 0) AND (comm IS NOT NULL);

-- ----------------------------------------------------------------------------

-- �μ����� �ο���, ��ձ޿�, �����޿�, �ְ�޿�, �޿��� ���� ���ϱ�
SELECT deptno, count(*) people_num, round(avg(sal), 0), min(sal), max(sal), sum(sal)
FROM emp
GROUP BY deptno;

-- �μ����� �ο���, ��ձ޿�, �����޿�, �ְ�޿�, �޿��� ���� ���ϱ� (�μ��� �޿��� ���� ���� ������)
SELECT deptno, count(*) people_num, round(avg(sal), 0) avg_sal, min(sal) min_sal, max(sal) max_sal, sum(sal) sum_sal
FROM emp
GROUP BY deptno
ORDER BY sum(sal) desc;

-- �μ��� ������ �׷��Ͽ� �μ���ȣ, ����, �ο���, �޿��� ���, �޿��� ���� ���ϱ�
SELECT deptno, job, count(*) people_num, round(avg(sal)) avg_sal, sum(sal) sum_sal
FROM emp
GROUP BY deptno, job;

-- �޿��� �ִ� 2900 �̻��� �μ��� ���� �μ���ȣ, ��� �޿�, �޿��� ���� ���
SELECT deptno, round(avg(sal)) avg_sal, sum(sal) sum_sal
FROM emp
GROUP BY deptno
HAVING max(sal) >= 2900;

-- ������ �޿��� ����� 3000�̻��� ������ ���� ������, ��� �޿�, �޿��� ���� ���
SELECT job, round(avg(sal)) avg_sal, sum(sal) sum_sal
FROM emp
GROUP BY job
HAVING avg(sal) >= 3000;

-- ��ü �հ� �޿��� 5000�� �ʰ��ϴ� �� ������ ���ؼ� ������ �޿� �հ踦 ���
-- ��, SALESMAN�� �����ϰ� �޿� �հ谡 ���� ������ ����
SELECT job, sum(sal) sum_sal
FROM emp
WHERE job != 'SALESMAN'
GROUP BY job
HAVING sum(sal) > 5000
ORDER BY sum(sal) desc;

-- ������ �ְ� �޿��� �ּ� �޿��� ���̸� ���϶�
SELECT job, (max(sal) - min(sal)) sal_gap
FROM emp
GROUP BY job;

-- �μ� �ο��� 4���� ���� �μ��� �μ���ȣ, �ο���, �޿��� ���� ���
SELECT deptno, count(*) people_num, sum(sal) sum_sal
FROM emp
GROUP BY deptno
HAVING count(*) > 4;