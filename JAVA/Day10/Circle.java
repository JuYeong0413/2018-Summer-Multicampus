package day10;

public class Circle extends Shape {
	
	private double radius;

	public Circle() {
		super();
	}
	
	public Circle(String string, int radius) {
		super(string);
		this.setRadius(radius);
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public void calculationArea() {
		area = Math.PI * radius * radius;
	}
}
