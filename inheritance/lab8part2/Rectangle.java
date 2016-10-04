
public class Rectangle extends Shape {

	private double width;
	private double length;
	
	Rectangle(){
		super();
		setWidth(1.0);
		setLength(1.0);
	}
	
	Rectangle(double width,double length){
		super();
		this.setWidth(width);
		this.setLength(length);
	}
	
	Rectangle(double width, double length,String color,boolean filled){
		super(color,filled);
		this.setWidth(width);
		this.setLength(length);
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}
	//by definition, area is L x W
	public double getArea(){
		return length * width;
	}
	//by mathematical definition, the perimeter is L + w + L + W
	public double getPerimeter(){
		return 2*length + 2*width;
	}
	
	public String toString(){
		return "A Rectangle with length = "+length+" and width = "+ width +" which is a subclass of "+ super.toString();
	}
	
}
