package TestOOP;

public abstract class Shape {
	private String color;
	private boolean filled;
	
	public Shape() {
		this.color=null;
		this.filled = true;
	}
	public Shape(String color, boolean filled) {
		this.color = color;
		this.filled = filled;
	}
	
	public String getColor() {
		return this.color;
	}
	public void setColor(String color) {
		this.color=color;
	}
	public boolean isFilled() {
		return this.filled;
	}
	public void setFilled(boolean filled) {
		this.filled=filled;
	}
	
	public abstract double getArea();
	public abstract double getPerimeter();

}
