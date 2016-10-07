package lab8part2;

import lab8.Circle;
import lab8.Rectangle;
import lab8.Shape;
import lab8.Square;

public class ShapeTest {

	public static void main(String[] args) {
		Shape s1 = new Circle(5.5, "RED", false);  // Upcast Circle to Shape
		System.out.println(s1);                    // which version? The toString() invoked is for the Shape object
		// which version? ,  it tries to invoke the getArea of the type Shape which is abstract
		//System.out.println(s1.getArea());  
		System.out.println(((Circle)s1).getArea());  //fix: added casting to the s1 object to make it a Circle object
		// which version? which version? ,  it tries to invoke the getPerimeter of the type Shape which is abstract
		//System.out.println(s1.getPerimeter());     
		System.out.println(((Circle)s1) .getColor()); //fix:added casting to the s1 object to make it a Circle object
		System.out.println(s1.isFilled());
		//getRadius is undefined for the type Shape because it is only found in the Circle object
		//System.out.println(s1.getRadius());
		   
		Circle c1 = (Circle)s1;                   // Downcast back to Circle
		System.out.println(c1);
		System.out.println(c1.getArea());
		System.out.println(c1.getPerimeter());
		System.out.println(c1.getColor());
		System.out.println(c1.isFilled());
		System.out.println(c1.getRadius());
		   
		//Shape s2 = new Shape(); //abstract classes should not be instantiated
		   
		Shape s3 = new Rectangle(1.0, 2.0, "RED", false);   // Upcast
		System.out.println(s3);
		System.out.println(((Rectangle) s3).getArea()); // same as the previous example, added Rectangle typecasting
		System.out.println(((Rectangle) s3).getPerimeter());// same as the previous example, added Rectangle typecasting
		System.out.println(s3.getColor());
		System.out.println(((Rectangle) s3).getLength());// same as the previous example, added Rectangle typecasting
		   
		Rectangle r1 = (Rectangle)s3;   // downcast
		System.out.println(r1);
		System.out.println(r1.getArea());
		System.out.println(r1.getColor());
		System.out.println(r1.getLength());
		   
		Shape s4 = new Square(6.6);     // Upcast
		System.out.println(s4);
		System.out.println(((Square) s4).getArea()); //fix: typecasted s4 to become a Square
		System.out.println(s4.getColor());
		System.out.println(((Square) s4).getSide()); //fix: typecasted s4 to become a Square
		  
		// Take note that we downcast Shape s4 to Rectangle, 
		//  which is a superclass of Square, instead of Square
		Rectangle r2 = (Rectangle)s4;
		System.out.println(r2);
		System.out.println(r2.getArea());
		System.out.println(r2.getColor());
		System.out.println(((Square) r2).getSide()); //technically Square is a Rectangle
		System.out.println(r2.getLength());
		   
		// Downcast Rectangle r2 to Square
		Square sq1 = (Square)r2;
		System.out.println(sq1);
		System.out.println(sq1.getArea());
		System.out.println(sq1.getColor());
		System.out.println(sq1.getSide());
		System.out.println(sq1.getLength());
	}

}
