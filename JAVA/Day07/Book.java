package day07;

/*
�Ʒ��� ������ �����Ͽ� Book Ŭ������ �����Ͻÿ�. 

Book Ŭ������ ��������
	1. å ����(title)�� ����(price)������ �����ϴ� ��������� �־�� �Ѵ�.
	2. ��������� ��� private ���������ڸ� ������ �Ѵ�. 
	3. ��������� ���� �����ϰ� �����ϴ� setter/getter �޼��尡 �־�� �Ѵ�.
		setTitle(String title) : ������� title ���� �����ϴ� �޼ҵ�
		String getTitle() : ������� title ���� ��ȯ�ϴ� �޼ҵ�
		void setPrice(int price) : ������� price ���� �����ϴ� �޼ҵ�
		int getPrice() : ������� price ���� ��ȯ�ϴ� �޼ҵ� 
	4. setter/getter �޼���� ��� public ���������ڸ� ������ �Ѵ�. 
 */

public class Book {
	private String title;
	private int price;
	
	
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
		this.price = price;
	}
	
}
