package day08;

/*
***** BookMgr Ŭ������ ��������
*
1. å(Book) ��ü ���� ���� ������ �� �ִ� å ���(booklist)�̶�� �迭�� ��������� ������ �Ѵ�.
2. å ���(booklist)�̶�� �迭 ������ �ʱ�ȭ�ϴ� ������ �޼��带 �ۼ��ؾ� �Ѵ�.
3. å ����� ȭ�鿡 ����ϴ� printBookList() �޼��尡 �־�� �Ѵ�.
4. ��� å ������ ���� ����ϴ� printTotalPrice() �޼��尡 �־�� �Ѵ�.

[������]
=== å ��� ===
Java Program
JSP Program
SQL Fundamentals
JDBC Program
EJB Program

=== å ������ ���� ===
��ü å ������ �� : 132000
*/

public class BookTest {
	public static void main(String[] args) {
		Book[] books = {
				new Book("Java Program", 28500),
				new Book("JSP Program", 26500),
				new Book("SQL Fundamentals", 27200),
				new Book("JDBC Program", 25000),
				new Book("EJB Program", 24800),
		};
		
		BookMgr b = new BookMgr(books);
		
		b.printBookList();
		System.out.println();
		b.printTotalPrice();
		
	}
}