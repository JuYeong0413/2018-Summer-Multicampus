package day08;

/*
***** BookMgr 클래스의 제약조건
*
1. 책(Book) 객체 여러 개를 저장할 수 있는 책 목록(booklist)이라는 배열을 멤버변수로 가져야 한다.
2. 책 목록(booklist)이라는 배열 변수를 초기화하는 생성자 메서드를 작성해야 한다.
3. 책 목록을 화면에 출력하는 printBookList() 메서드가 있어야 한다.
4. 모든 책 가격의 합을 출력하는 printTotalPrice() 메서드가 있어야 한다.

[실행결과]
=== 책 목록 ===
Java Program
JSP Program
SQL Fundamentals
JDBC Program
EJB Program

=== 책 가격의 총합 ===
전체 책 가격의 합 : 132000
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