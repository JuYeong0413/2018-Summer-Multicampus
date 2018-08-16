package day10;

public abstract class Shape {
	
	protected double area;
	private String name;
	
	public Shape() {
		
	}
	
	public Shape(String name) {
		this.name = name;
	}
	
	public abstract void calculationArea();
	
	public void print() {
		System.out.println(name + "의 면적은 " + area);
	}
}
