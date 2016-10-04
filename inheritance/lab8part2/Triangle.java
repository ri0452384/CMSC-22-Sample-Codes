
public class Triangle extends Shape {
	
	double side1;
	double side2;
	double side3;

	Triangle(double s1, double s2, double s3, String color,boolean filled) throws Exception{
		super(color,filled);
		setSides(s1, s2, s3);
	}
	
	//helper method designed to check the sides before setting them
	private void setSides(double s1, double s2, double s3) throws Exception{
		if(s1 < s2+s3 || s2 < s1+s3 || s3 < s1 + s2 ){
			side1 = s1;
			side2 = s2;
			side3 = s3;
		}else{
			throw new Exception("Invalid side/s found!");
		}
	}
	
	Triangle(double s1, double s2, double s3) throws Exception{
		super();
		setSides(s1, s2, s3);
	}
	
	//area of a triangle as per Heron's formula
	public double getArea() {
		double s = getPerimeter()/2;
		return Math.sqrt(s*(s-side1)*(s-side2)*(s-side3));
	}

	
	public double getPerimeter() {
		return side1 + side2 + side3;
	}
	
	public String toString(){
		return "A Triangle with sides = "+side1+",  "+ side2 +", and "+side3+" which is a subclass of "+ super.toString();
	}

}
