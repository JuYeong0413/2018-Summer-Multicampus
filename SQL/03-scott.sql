-- ������̺�(emp)�� ������ �μ����̺�(dept)�� �� ����� �μ����� ���
SELECT ename, dname
FROM emp, dept; -- emp * dept �� ��� (���� �ǹ� ����)

-- �� ���̺����� �������踦 �ξ��ִ� �� (foreign key)
-- ��������(inner join)
SELECT e.ename ename, d.dname dname, e.deptno deptno -- �ܺο��� ���� �� �ְԲ� �÷����� ��Ī �ο�
FROM emp e, dept d -- ���̺�� ��Ī ���� ���� AS ���� ���δ�.
WHERE e.deptno = d.deptno;

SELECT e.ename ename, d.dname dname, e.deptno deptno
FROM emp e INNER JOIN dept d
ON e.deptno = d.deptno;

-- �ܺ�����(outer join) : �� ���� null���̾ �˻�
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

-- ������̺�(emp)�� �� ����� �Ŵ����� ��ȸ
-- ��������
SELECT e1.empno, e1.ename, e1.mgr, e2.ename
FROM emp e1, emp e2
WHERE e1.mgr = e2.empno(+); -- ���� �ʿ� (+) ������� ��

SELECT e1.empno, e1.ename, e1.mgr, e2.ename
FROM emp e1 LEFT OUTER JOIN emp e2
ON e1.mgr = e2.empno;

--1. EMP ���̺��� �����ȣ, �̸�, ����, �μ���ȣ�� DEPT ���̺��� �μ���, �ٹ����� ���
SELECT e.empno empno, e.ename ename, e.job job, e.deptno deptno, d.dname dname, d.loc loc
FROM emp e, dept d
WHERE e.deptno = d.deptno(+);

SELECT e.empno empno, e.ename ename, e.job job, e.deptno deptno, d.dname dname, d.loc loc
FROM emp e LEFT OUTER JOIN dept d
ON e.deptno = d.deptno;

--2. SALESMAN ����� �����ȣ, �̸�, �޿�, �μ���, �ٹ����� ���
SELECT e.empno empno, e.ename ename, e.sal sal, d.dname dname, d.loc loc
FROM emp e, dept d
WHERE e.deptno = d.deptno(+) AND e.job = 'SALESMAN'; -- JOIN�� ���� ������ �տ� ����ϴ� ���� �ӵ��� �� ������

SELECT e.empno empno, e.ename ename, e.sal sal, d.dname dname, d.loc loc
FROM emp e LEFT OUTER JOIN dept d
ON e.deptno = d.deptno
WHERE e.job = 'SALESMAN';

--3. ���ʽ�(comm)�� �޴� ����� ���� �̸�, �μ���, ��ġ�� ���
SELECT e.ename ename, d.dname dname, d.loc loc
FROM emp e, dept d
WHERE e.deptno = d.deptno(+) AND e.comm IS NOT NULL AND e.comm != 0;

SELECT e.ename ename, d.dname dname, d.loc loc
FROM emp e LEFT OUTER JOIN dept d
ON e.deptno = d.deptno
WHERE e.comm IS NOT NULL AND e.comm != 0;

--4. �μ��� �μ���� �޿� �հ踦 ���
SELECT d.dname dname, sum(e.sal) sum_sal
FROM emp e, dept d
WHERE e.deptno = d.deptno
GROUP BY d.dname;

SELECT d.dname dname, sum(e.sal) sum_sal
FROM emp e INNER JOIN dept d
ON e.deptno = d.deptno
GROUP BY d.dname;

--5. �����ں��� ���� �Ի��� ����� ���� �̸�, �Ի���, ������ �̸�, ������ �Ի����� ���
SELECT e1.ename ename, e1.hiredate hiredate, e2.ename mgr_name, e2.hiredate mgr_hiredate
FROM emp e1, emp e2
WHERE e1.mgr = e2.empno(+) AND e1.hiredate < e2.hiredate;

SELECT e1.ename ename, e1.hiredate hiredate, e2.ename mgr_name, e2.hiredate mgr_hiredate
FROM emp e1 LEFT OUTER JOIN emp e2
ON e1.mgr = e2.empno
WHERE e1.hiredate < e2.hiredate;

-- ----------------------------------------------------------------------------

-- ��ü ����� �� ��ձ޿����� ���� �޿��� �޴� ����� ����� ��ȸ
SELECT avg(sal) FROM emp;

SELECT *
FROM emp
WHERE sal < ( SELECT avg(sal) FROM emp );
-- WHERE sal = ( SELECT min(sal) FROM emp );
-- WHERE sal = ( SELECT max(sal) FROM emp );

-- ������ ���� ���� ����� ���� ��ȸ
SELECT *
FROM emp
WHERE sal = ( SELECT max(sal) FROM emp );

-- ��� �޿����� ���� �ִ� �޿����� ���� ������ �޴� ����� ��������ȸ
SELECT *
FROM emp
WHERE ( sal > ( SELECT avg(sal) FROM emp ) ) AND ( sal < ( SELECT max(sal) FROM emp ) );

SELECT e.*
FROM emp e, ( SELECT max(sal) max, avg(sal) avg FROM emp) e2
WHERE e.sal < e2.max AND e.sal > e2.avg;

-- ���޼����� ���� 10���Ǹ���� ���
-- ���ķ� ������ ���������� ���� ���̺� ����
SELECT rownum, e.*
FROM ( SELECT empno, ename, sal
       FROM emp
       ORDER BY sal desc ) e
WHERE rownum <= 10;

-- 1. SCOTT�� �޿����� ���� ����� ������ �����ȣ, �̸�, ������, �޿��� ���
SELECT empno, ename, job, sal
FROM emp
WHERE sal > ( SELECT sal FROM emp WHERE ename = 'SCOTT' );

-- 2. 30�� �μ��� �ּ� �޿����� ���μ��� �ּ� �޿��� ���� �μ��� ���
SELECT min(sal) FROM emp WHERE deptno = 30;

SELECT deptno, min(sal)
FROM emp
GROUP BY deptno
HAVING min(sal) > ( SELECT min(sal) FROM emp WHERE deptno = 30 );

-- 3. �������� ��� �޿� �߿��� ���� ���� �޿��� ���� ������ ���
SELECT avg(sal) FROM emp GROUP BY job ORDER BY avg(sal);

SELECT emp.job, s.*
FROM (SELECT avg(sal) FROM emp GROUP BY job ORDER BY avg(sal)) s, emp
WHERE rownum = 1;

SELECT job, avg(sal)
FROM emp
GROUP BY job
HAVING avg(sal) = ( SELECT min(avg(sal)) FROM emp GROUP BY job );

-- 4. �����ȣ�� 7521�� ������ ���� ��� 7934�� �������� �޿��� ���� ���� ����� ������ ���
SELECT *
FROM emp
WHERE job = ( SELECT job FROM emp WHERE empno = 7521 ) AND sal > ( SELECT sal FROM emp WHERE empno = 7934 );

-- 5. ������ ��MANAGER���� ����� ������ �̸�, ����, �μ���, �ٹ����� ��� 
SELECT e.ename ename, e.job job, d.dname dname, d.loc loc
FROM ( SELECT * FROM emp WHERE job = 'MANAGER' ) e, dept d
WHERE e.deptno = d.deptno;

SELECT e.ename ename, e.job job, d.dname dname, d.loc loc
FROM ( SELECT * FROM emp WHERE job = 'MANAGER' ) e INNER JOIN dept d
ON e.deptno = d.deptno;

SELECT e.ename ename, e.job job, d.dname dname, d.loc loc
FROM emp e, dept d
WHERE e.deptno = d.deptno AND e.job = 'MANAGER';

-- 6. 'WARD'�� �μ��� ������ ���� ��� ��� ��� 
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

-- 10�� �μ�������� ������ ������ ������ �˻�
SELECT job FROM emp WHERE deptno = 10;

SELECT *
FROM emp
WHERE job = ANY (SELECT job FROM emp WHERE deptno = 10); -- ���������� �� �ϳ��� �¾Ƶ� �� �� ANY ���, �ݴ�� ALL
-- WHERE job IN (SELECT job FROM emp WHERE deptno = 10);

-- �������� �ּ� �޿��� �޴� ����� ������ �����ȣ, �̸�, ������, �޿��� ���
SELECT min(sal) FROM emp GROUP BY job;

SELECT empno, ename, job, sal
FROM emp
WHERE sal = ANY ( SELECT min(sal) FROM emp GROUP BY job );

-- ��� �Ѹ��� ������κ��� ���� ���� �� �ִ� ����� ������ �����ȣ, �̸�, ������ ���
SELECT e1.empno, e1.ename, e1.job
FROM emp e1
WHERE exists ( SELECT * FROM emp e2 WHERE e1.empno = e2.mgr ); -- �ݴ�� not exists

-- 1. ������ 'NEW YORK'�� 'DALLAS'�� �ƴ� �μ��� ����� ��ȸ
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

-- 2. �޿��� 3000�̻��� ����� ���� �μ����� ���
SELECT * FROM emp WHERE sal >= 3000;

SELECT d.dname
FROM dept d
WHERE d.deptno IN ( SELECT deptno FROM emp WHERE sal >= 3000 );

SELECT d.dname
FROM dept d
WHERE EXISTS ( SELECT e.deptno FROM emp e WHERE e.deptno = d.deptno AND e.sal >= 3000 );

-- ----------------------------------------------------------------------------

create table stat_salary ( 
           stat_date   date  not  null,        -- ��¥
           dept_no   number,                   -- �μ�
           emp_count number,                   -- �����
           tot_salary number,                  -- �޿��Ѿ�
           avg_salary number );                -- �޿����
           
INSERT INTO stat_salary(stat_date, dept_no)
SELECT sysdate, deptno from dept;

SELECT * FROM stat_salary;

-- �μ��� �ο���, ������ �հ� ������ ����� ���ϱ�
SELECT count(*), sum(sal) sum_sal, avg(sal) avg_sal
FROM emp
GROUP BY deptno;

UPDATE stat_salary s
SET ( s.emp_count, s.tot_salary, s.avg_salary )
= ( SELECT count(*), sum(e.sal) sum_sal, avg(e.sal) avg_sal FROM emp e WHERE s.dept_no = e.deptno GROUP BY e.deptno );

-- emp_copy ���
INSERT INTO emp_copy ( empno, ename, job, mgr, hiredate, sal, deptno )
VALUES ( 7788, 'SCOTT', 'ANALYST', 7566, '87/04/19', 3000, 20 );
INSERT INTO emp_copy ( empno, ename, job, mgr, hiredate, sal, deptno )
VALUES ( 7566, 'JONES', 'MANAGER', 7839, '81/04/02', 2975, 20 );

SELECT * FROM emp_copy;

-- 1. SCOTT�� ������ �޿��� JONES�� ������ �޿��� ����
SELECT job, sal FROM emp_copy WHERE ename = 'SCOTT';
UPDATE emp_copy
SET ( job, sal ) = ( SELECT job, sal FROM emp_copy WHERE ename = 'SCOTT' )
WHERE ename = 'JONES';

-- 2. �μ����� 'SALES'�� ����� ������ ����
DELETE FROM emp_copy
WHERE job = 'SALESMAN';

-- ----------------------------------------------------------------------------

-- �������̺� : view
CREATE OR REPLACE VIEW v_emp AS
        SELECT empno, ename, deptno FROM  emp
        with read only; -- �б� ����
        
select * from v_emp;

INSERT INTO v_emp VALUES (9000, 'ȫ����', 30);
INSERT INTO v_emp VALUES (8000, 'ȫ����', 30); -- ���� ���̺��� PK ���� �޴´�.
INSERT INTO v_emp VALUES (9001, 'ȫ�浿', 90); -- ���� ���̺��� FK ���� �޴´�.
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
VALUES(seq_emp_empno.NEXTVAL, 'ȫ��', '����');

SELECT * FROM emp;

DROP SEQUENCE seq_emp_empno;

CREATE SEQUENCE seq_emp_empno
            start with 1000
            increment by 10;
            
INSERT INTO emp(empno, ename, job)
VALUES(seq_emp_empno.NEXTVAL, '�ھ�', '����');

SELECT seq_emp_empno.currval FROM dual;

-- ----------------------------------------------------------------------------

CREATE SEQUENCE seq_rental_num;
CREATE SEQUENCE seq_video_num;

-- ----------------------------------------------------------------------------

-- ȸ�� ���̺�
create table ex_member(
id varchar2(10), -- ���̵�
pass varchar2(10), -- �н�����
name varchar2(20), -- �̸�
tel varchar2(20), -- ��ȭ��ȣ
addr varchar2(100) -- �ּ�
);

-- ��ǰ ���̺�
create table ex_good(
gno varchar2(10), -- ��ǰ��ȣ
gname varchar(30), -- ��ǰ��
gdetail varchar2(300), -- �󼼼���
price number);  -- ����

-- �ֹ� ���̺�
create table ex_order (
ono number,  -- ��ȣ
orderno varchar2(20), -- �ֹ���ȣ
gno varchar2(10), -- ��ǰ��ȣ
id varchar2(10), -- ȸ�� ���̵�
count number,  -- ����
status varchar2(10) -- ��ۻ���
);

create sequence seq_order_ono;

-- ######################################################
-- �ֹ����̺�� ȸ�����̺�, ��ǰ���̺��� ���踦 �δ´ٸ�?

-- ȸ�� ���̺� ����Ÿ �Է�
insert into ex_member(id, pass, name, tel, addr )
values('kim', '1111', '��浿', '02-222-2222','���� ������ �̻۵�');

insert into ex_member(id, pass, name, tel, addr )
values('park', '1111', '�ڱ浿', '03-333-3333','��õ ������ �̻۵�');

insert into ex_member(id, pass, name, tel, addr )
values('meng', '1111', '�ͱ浿', '04-444-4444','��� ������ �̻۵�');

commit;

-- ���� ���̺� ����Ÿ �Է�
insert into ex_good( gno, gname, gdetail, price )
values('1001', '�Ӹ���', '���� ��� �Ӹ���', 200 );

insert into ex_good( gno, gname, gdetail, price )
values('2002', '������-A', '������� ������ �޸��� ������', 10000 );

insert into ex_good( gno, gname, gdetail, price )
values('2010', '������-B', '���� ������� ������ �����ϰ� �޸��ٴ� ������', 20000 );

insert into ex_good( gno, gname, gdetail, price )
values('3333', '�ڵ������̽�', '�ΰ� �����ϴ� �ڵ��� ���̽�', 1500 );

commit;

-- �ֹ� ���̺� ����Ÿ �˻�
insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20161212', '1001', 'park', 1, '��ۿϷ�');

insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20161212', '2010', 'park', 1, '�����');

insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20161111', '1001', 'kim', 2, '�ֹ�');

insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20161111', '3333', 'kim', 3, '�ֹ�');

insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20163333', '1001', 'park', 3, '�ֹ�');

insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20163333', '2010', 'park', 1, '�����');

insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20163333', '2002', 'park', 2, '�ֹ�');

insert into ex_order( ono, orderno, gno, id, count, status )
values( seq_order_ono.nextval, '20165050', '1001', 'meng', 1, '�����');

commit;

-- ###############################################################
-- [ ���� ]
-- 1. ������� �ֹ� ������ ��ǰ ���� ���
SELECT id, gno FROM ex_order WHERE status = '�����';

SELECT m.id, m.name, o.gno, g.gname, g.gdetail
FROM ex_member m, ( SELECT id, gno FROM ex_order WHERE status = '�����' ) o, ex_good g
WHERE o.gno = g.gno AND o.id = m.id;

-- 2. �ֹ� ���� ��ǰ ������ �� ���� ���
SELECT id, gno FROM ex_order WHERE status = '�ֹ�';

SELECT m.*, o.gno, g.gname
FROM ex_member m, ( SELECT id, gno FROM ex_order WHERE status = '�ֹ�' ) o, ex_good g
WHERE o.gno = g.gno AND o.id = m.id;

-- 3. �ֹ����� �� ����(ID�� ���)�� �ֹ��� ��ǰ�� �ѱݾ��� ���
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

-- 4. 3���� �ֹ� ������ ù��° ��ǰ�� �� �� ���� ���
SELECT o.orderno, sum(o.count) as total
FROM ex_good g, ex_order o
WHERE g.gno = o.gno
GROUP BY o.orderno;

SELECT o.orderno, concat('�� ' || to_char(sum(o.count) - 1), '��') AS "����"
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

SELECT DISTINCT o.id, t.orderno, t.total, (g.gname || ' �� ' || t.count || '��') AS "��ǰ"
FROM ex_order o, ex_good g,
    ( SELECT o.orderno, sum(g.price * o.count) AS total,
      max(g.gno) AS max_gno,
      sum(o.count) - 1 AS count
      FROM ex_good g, ex_order o
      WHERE g.gno = o.gno
      GROUP BY o.orderno ) t
WHERE o.orderno = t.orderno AND g.gno = t.max_gno;