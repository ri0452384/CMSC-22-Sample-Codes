   import java.awt.Graphics;

public class Circle implements GeometricObject{
	
	protected double radius;
	private static final double PI_VALUE = 3.14159;

	public Circle(){
		this.radius = 1.0;
	}
	
	public Circle(double r){
		this.radius = r;
	}
	
	public double getPerimeter(){
		return 2*PI_VALUE*radius;
	}
	
	public double getArea(){
		return PI_VALUE*radius*radius;
	}
	
	public String toString(){
		return "radius = "+radius+"\n";
		
	}
	
}