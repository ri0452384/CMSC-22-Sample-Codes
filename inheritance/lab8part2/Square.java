package lab8part2;

public class Square extends Rectangle {

	Square(){
		super(1.0,1.0);
		
	}
	
	Square(double side){
		super(side, side);
	}
	
	Square(double side, String color, boolean filled){
		super(side,side,color,filled);
	}
	
	public double getSide(){
		return this.getLength();
	}
	
	public void setSide(double side){
		this.setLength(side);
		this.setWidth(side);
	}
	
	public void setLength(double side){
		super.setLength(side);
		super.setWidth(side);
	}
	
	public void setWidth(double side){
		super.setLength(side);
		super.setWidth(side);
	}
	
	public String toString(){
		return "A Square with side = "+getLength()+" which is a subclass of "+ super.toString();
	}
	
}
