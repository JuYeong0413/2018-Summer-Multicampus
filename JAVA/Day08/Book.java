package day08;

public class Book {
	private String title;
	private int price;
	
	public Book() {
		this("力格绝澜", 0);
	}
	
	public Book(String title, int price) {
		super();
		this.title = title;
		this.setPrice(price);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		if(price < 0) return;
		this.price = price;
	}
	
	public void print() {
		System.out.printf("Book[力格 : %-20s 啊拜 : %10d]%n", title, price);
	}
}
