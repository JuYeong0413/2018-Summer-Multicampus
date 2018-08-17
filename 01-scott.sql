-- select * from emp;
-- �ּ�

CREATE  TABLE  student
(
    id char(4),
    name varchar2(10),
    kor number(3),
    eng number(3),
    total number(3),
    avg number(5,2)
    -- [] : ������ ���ٴ� ���
    -- �й� id char(4) �̸� name varchar2(10) ���� kor number(3) ���� eng number(3) ���� total number(3) ��� avg number(5,2)
    -- �ڸ��� 3���ϱ� ������ -999 ~ +999
    -- number(��ü�ڸ���, �Ҽ����ڸ���)
);

DESC student;

-- (1) ���� �÷� �߰� : �߰��ϸ� �ڿ� ���� �̰� ���ٲ�
ALTER TABLE student ADD ( math number(3) );

-- (2) ���� �÷� ����
ALTER TABLE student DROP ( total );

-- (3) ��� �÷��� �Ҽ��� 1�ڸ� ����
ALTER TABLE student MODIFY ( avg number(4,1) );

DESC student; -- ������ ���� ��

-- DML
-- ���ڵ� �Է� (data �Է�)
INSERT INTO student ( id, name, kor, eng, math )
-- database�� ���ڿ��� ������ small quotation
VALUES ( '1001', 'ȫ�浿', 80, 100, 50 );

INSERT INTO student ( id, name, kor, eng, math )
VALUES ( '1001', 'ȫ�浿����', 80, 100, 50 );

INSERT INTO student ( id, name, kor, eng, math )
VALUES ( '1001', 'abcdefghij', 80, 100, 50 );

SELECT * FROM student; -- data�� ���� ��

-- 1002 �й��� ȫ�����л��� ����, ����, ������ 80, 70�� �Է��ϱ�
INSERT INTO student ( id, name, kor, eng )
VALUES ( '1002', 'ȫ����', 80, 70);

-- ���ڵ� ����
DELETE FROM student WHERE name = 'abcdefghij';

-- ���ڵ� ����
UPDATE student SET avg = ( kor + eng + nvl( math,0 )) / 3;
-- �ѹ� null �� �� ������ null�� �ȴ�. null�� ���� 0���� ��ġ �ʿ�
-- ȫ���� �л��� ���������� 90���� �Է� : update
rollback;

UPDATE student SET math = 90 WHERE name = 'ȫ����';
SELECT * FROM student;

desc emp;

-- ----------------------------------------------------------------------------

SELECT * FROM emp;

CREATE TABLE emp_copy
    AS SELECT empno, ename, job, mgr, hiredate, sal, comm, deptno
    FROM emp;
    
SELECT * FROM emp_copy;

-- 1. �����ȣ�� 7788�� ����� �μ��� 10������ ����
UPDATE emp_copy SET deptno = 10 WHERE empno = 7788;
-- 2. �����ȣ�� 7788�� ����� �̸��� 'ȫ����'�� �����ϰ� �޿��� 3500���� ����
UPDATE emp_copy SET ename = 'ȫ����', sal = 3500 WHERE empno = 7788;
-- 3. ��� �μ����� ���ʽ��� 300���� �λ�
-- null �� ó��!!!
UPDATE emp_copy SET comm = nvl(comm, 0) + 300;
-- 4. �����ȣ�� 7499�� ����� ��ȣ�� ����
DELETE FROM emp_copy WHERE empno = 7499;
-- 5. �Ի����ڰ� 81�� 6�� 1�� ������ ����� ������ ����
DELETE FROM emp_copy WHERE hiredate < '81/06/01';

SELECT * FROM emp_copy;
SELECT * FROM emp;

-- ��� 8000, �����̸�, ���� 10000 �Է�
INSERT INTO emp ( empno, ename, sal )
VALUES ( 8000, '���ֿ�', 10000 );
-- ��� column �Է� �� column ���� ����, ������ �Է��ϴ� ���� ���̱�

-- INSERT INTO emp ( empno, ename, sal ) VALUES ( 8000, 'ȫ�浿', 10000 );
-- ���Ἲ ���� ���ǿ� ����˴ϴ�. -> �⺻Ű �ߺ� : ����� ������ ���̾�� �Ѵ�. ��ġ�� �� ��

-- INSERT INTO emp ( ename, sal ) VALUES ( 'ȫ����', 20000 );
-- �⺻Ű�� �� ��� ������ ���� �߻�

INSERT INTO emp ( empno, ename, deptno )
VALUES ( 9000, 'ȫ����', 60 ); -- �������� �ʴ� deptno

CREATE  TABLE  info_tab
(
    name varchar2(8), --�̸�
    id char(14), --�ֹι�ȣ
    tel varchar2(20), -- ��ȭ��ȣ
    sex varchar2(10), -- ����
    age number(3), -- ���� -> �ڸ��� ���� ���ϸ� �⺻�� 10�ڸ�
    home varchar2(14), -- �����
    CONSTRAINT pk_info_tel PRIMARY KEY ( tel ),
    CONSTRAINT uq_info_id UNIQUE ( id )
);

-- �̸��� �ʼ��������� ���� ( not null �Ӽ� )
ALTER TABLE info_tab MODIFY ( name varchar2(8) NOT NULL );
-- ������ '����', '����'�� �Էµǵ��� ���� ( check �Ӽ� )
ALTER TABLE info_tab
ADD CONSTRAINT ck_info_sex CHECK ( sex IN ( '����', '����' ) );
-- ������� �Է°��� ������ �⺻�� '����' ���� ( default �Ӽ� )
ALTER TABLE info_tab MODIFY ( home varchar2(14) DEFAULT '����');

INSERT INTO info_tab ( name, tel, id, sex, age, home)
VALUES ('ȫ�浿', '02-555-5556', '880808-1234568', '����', '27', '��õ');

SELECT * FROM info_tab;

INSERT INTO info_tab ( name, tel )
VALUES ('�����', '02-222-2222');

INSERT INTO info_tab ( name, tel, sex )
VALUES ('������', '02-333-3333', '����');

-- ----------------------------------------------------------------------------

INSERT INTO info_tab( name, tel, id )
VALUES('������', '02-777-4444', '990909-1234567'); -- O

INSERT INTO info_tab( name, tel, id, sex, age, home )
VALUES('������', '03-555-5555', '880808-1234567', '����', 27, '���'); -- �ֹι�ȣ �ߺ�

INSERT INTO info_tab( name, tel, id, sex, age, home )
VALUES('ȫ�浿', '03-031-0310', '900909-1234567', '����', 27, '���'); -- ���� (����)

INSERT INTO info_tab( name, id ) VALUES('ȫ����', '550505-1234567'); -- tel�� primary key

INSERT INTO info_tab( tel, id )VALUES('031-777-7777', '700707-1234567'); -- name�� not null

INSERT INTO info_tab( name, tel, id ) VALUES('������', '031-000-0000','700707-1234567'); -- O

SELECT * FROM info_tab;

-- ----------------------------------------------------------------------------

/*
�˻�
SELECT ���� ���� ������� �÷����� �� ���
FROM ���̺��
WHERE �˻��� �� �ʿ��� ���� ���;

SELECT * (��� �÷� �� ���ڴ�) FROM emp;
-> emp�� �ִ� �÷� ���Ǿ��� �� ���ڴٴ� ��

�� ���α׷������� != �� <> ���� �ǹ���
like -> �˻��� �� �� �ܾ �ش��ϴ� �� �� �˻��ϴ� ��
ȫ�̶�� ���ڰ� ���ִ°� �� �˻��� �ּ���
���ڿ� ���� (+) -> ||
*/

-- ��� ����� ������ �޿�, �޿��� ���ʽ��� ���� �հ�
SELECT ename, sal, sal + nvl(comm, 0) AS total_sal -- ��Ī�� �ο��ؾ� �� �� ����!!! ���� ���� ��, �����ص� �˾ƺ��� ��
FROM emp;

-- ����� �̸��� ������ �����Ͽ� 'STAFF'���� ���
SELECT ename || '   ' || job AS staff FROM emp;

-- ----------------------------------------------------------------------------

-- ��������
-- 1. 20�� �μ����� �ٹ��ϴ� ����� �����ȣ, �̸�, �μ���ȣ ���
SELECT empno, ename, deptno
FROM emp
WHERE deptno = 20;

-- 2. �Ի����� 82/01/01���� 82/06/01�� ����� �����ȣ, �̸�, �Ի����� ���
SELECT empno, ename, hiredate
FROM emp
WHERE hiredate between '82/01/01' and '82/06/01';

-- 3. �������� salesman, clerk�� ������� �̸��� ������ ���
SELECT ename, job
FROM emp
WHERE job = 'SALESMAN' or job = 'CLERK'; -- ���� ��ɾ �ƴϱ� ������ ��ҹ��� �����Ѵ�
-- WHERE job IN ('SALESMAN', 'CLERK'); -- 3�� �̻��̸� IN ������ ���� ���� ����.

-- 4. ������ president�̰� �޿��� 1500�̻��̰ų� ������ salesman�� ����� ������ ���
SELECT *
FROM emp
WHERE job = 'PRESIDENT' and (sal >=1500 or job = 'SALESMAN');

-- 5. ������ president �Ǵ� salesman�̰� �޿��� 1500�̻��� ����� ������ ���
SELECT *
FROM emp
WHERE (job = 'PRESIDENT' or job = 'SALESMAN') and (sal >= 1500);
-- WHERE (job IN ('PRESIDENT', 'SALESMAN')) and sal >= 1500;

-- 6. Ŀ�̼�(comm)�� ���� ����� �̸�, �޿�, Ŀ�̼��� ���
SELECT ename, sal, comm
FROM emp
WHERE comm IS NULL or comm = 0;

-- 7. �����, �޿�, Ŀ�̼�, �ѱ޿�(�޿� + Ŀ�̼�)�� ���
SELECT ename, sal, comm, sal + nvl(comm, 0) as total_salary
FROM emp;

-- ��������
-- 1. sawon ���̺��� �����ϼ���. sabun�� 6�ڸ� ����, sawon_name�� �ִ� �ѱ� 5�ڸ�, ubmu�� �ִ� �ѱ� 10�ڸ�,
--    weolgub�� ������ 8�ڸ��� �Ҽ��� 2�ڸ�, buseo�� ���� 3�ڸ�
-- 2. ���� ���̺��� sabun�� �⺻Ű�� ����
CREATE TABLE sawon
(
 sabun number(6),
 sawon_name varchar2(10),
 ubmu varchar2(20),
 weolgub number(10,2),
 buseo number(3),
 CONSTRAINT pk_sawon_sabun PRIMARY KEY ( sabun )
);
-- 3. �ִ� �ѱ� 10�ڸ��� jiyeok �÷��� �߰��ϵ� NULL ���� �Էµ��� �ʵ��� ����
ALTER TABLE sawon ADD ( jiyeok varchar2(10) NOT NULL );
-- 4. weolgub �÷��� ������ 7�ڸ��� ����
ALTER TABLE sawon MODIFY ( weolgub number(7) );
-- 5. ubmu �÷��� '����', '��������', '����'�� ������ ������ �Էµǵ��� ����
ALTER TABLE sawon
ADD CONSTRAINT ck_sawon_ubmu CHECK ( ubmu IN ( '����', '��������', '����' ) );
-- 6. ubmu �÷��� �����Ͱ� �Է��� �� �� ��� ����Ʈ������ '����'�� ����
ALTER TABLE sawon MODIFY ( ubmu varchar2(20) DEFAULT '����');
-- 7. buseo ���̺��� �����ϼ���
--    buseo_no�� ���� 3�ڷ� �̷���� ��ȣ�� �⺻Ű, buseo_name�� �ִ� �ѱ� 10�ڸ�
CREATE TABLE buseo
(
 buseo_no number(3),
 buseo_name varchar2(20),
 CONSTRAINT pk_buseo_buseono PRIMARY KEY ( buseo_no )
);
-- 8. sawon ���̺��� buseo �÷��� buseo ���̺��� buseo_no�� �����ϴ� �ܷ�Ű�� ����
ALTER TABLE sawon
ADD CONSTRAINT fk_buseo FOREIGN KEY ( buseo )
REFERENCES buseo ( buseo_no );
-- 9. buseo ���̺� ������ �Է�
INSERT INTO buseo ( buseo_no, buseo_name )
VALUES (101, '����Ʈ��������������');
INSERT INTO buseo ( buseo_no, buseo_name )
VALUES (202, '������');
INSERT INTO buseo ( buseo_no, buseo_name )
VALUES (303, '�����ڿ���');
-- 10. sawon ���̺� ������ �Է� (�Է��� �� �� ��쵵 ����)
INSERT INTO sawon ( sabun, sawon_name, weolgub, buseo, jiyeok )
VALUES (8001, 'ȫ�浿�̱�', 100000, 101, '�λ�');
INSERT INTO sawon ( sabun, sawon_name, ubmu, weolgub, buseo, jiyeok )
VALUES (8002, 'ȫ����', '�繫', 200000, 202, '��õ'); -- ubmu �÷��� '����', '��������', '����'�� �Է� ����
INSERT INTO sawon ( sabun, sawon_name, ubmu, buseo, jiyeok )
VALUES (8003, 'ȫ���', '����', 111, '����'); -- buseo ���̺��� buseo_no�� 111�� ����
INSERT INTO sawon ( sabun, sawon_name, weolgub, jiyeok )
VALUES (8004, 'ȫ�漮', 12345678, '����'); -- weolgub�� ������ 7�ڸ����� �Է� ����
INSERT INTO sawon ( sabun, sawon_name, ubmu, buseo, jiyeok )
VALUES (8005, 'ȫ��ö', '��������', 303, '�Ǳ�');
-- 11. sawon ���̺��� jiyeok �÷��� ����
ALTER TABLE sawon DROP ( jiyeok );
-- 12. buseo ���̺��� buseo ���� '�����ڿ���'�� ���� ����
ALTER TABLE sawon DROP CONSTRAINT fk_buseo;
DELETE FROM buseo WHERE buseo_name = '�����ڿ���';
-- 13. sawon ���̺��� ��� ������ �����ϰ� ��������� ����
TRUNCATE TABLE sawon;