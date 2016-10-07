package lab8part2;

public class Circle extends Shape{

	private static final double PI_VALUE = 3.14159;
	private double radius;
	
	public Circle(){
		super();
		setRadius(1.0);
	}
	
	public Circle(double r){
		super();
		setRadius(r);
	}
	 
	Circle(double r,String color,boolean filled){
		super(color,filled);
		setRadius(r);
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	// by definition, the area of a circle is  PI * r^2
	public double getArea(){
		return PI_VALUE*radius*radius;
	}
	// by definition, the circumference is 2 * PI * r
	public double getPerimeter(){
		return 2*PI_VALUE*radius;
	}
	
	public String toString(){
		return "A Circle with radius = "+radius+" which is a subclass of "+ super.toString();
	}
	
}
