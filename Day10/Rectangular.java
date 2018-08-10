package day10;

public class Rectangular extends Shape {
	
	private double width;
	private double height;

	public Rectangular() {
		super();
	}
	
	public Rectangular(String string, int width, int height) {
		super(string);
		this.setWidth(width);
		this.setHeight(height);
	}

	public double getWidth() {
		return width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public void calculationArea() {
		area = width * height;
	}
}
