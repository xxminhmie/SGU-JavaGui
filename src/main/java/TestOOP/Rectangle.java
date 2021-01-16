package TestOOP;

public class Rectangle extends Shape{
	private double width;
	private double length;
	
	public Rectangle() {
		this.width=1.0;
		this.length=1.0;
	}
	public Rectangle(double width, double length) {
		this.length=length;
		this.width=width;
	}
	public Rectangle(double width, double length, String color, boolean filled) {
		super(color, filled);
		this.length=length;
		this.width=width;
	}

	@Override
	public double getArea() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getPerimeter() {
		// TODO Auto-generated method stub
		return 0;
	}

}
