package day08;

public class BookMgr {
	Book[] booklist;

	public BookMgr() {
		
	}
	
	public BookMgr(Book[] books) {
		booklist = new Book[books.length];
		
		for(int i = 0; i < books.length; i++) {
			booklist[i] = books[i];
		}

	}
	
	public void printBookList() {
		System.out.println("===== å  ��� =====");
		
		for(int i = 0; i < booklist.length; i++) {
			System.out.println(booklist[i].getTitle());
		}
	}
	
	public void printTotalPrice() {
		int sum = 0;
		for(int i = 0; i < booklist.length; i++) {
			sum += booklist[i].getPrice();
		}
		System.out.println("=== å ������ ���� ===");
		System.out.println("��ü å ������ �� : " + sum);
	}
}
