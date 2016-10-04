
public abstract class Shape {
	
	private boolean filled;
	private String color;
	
	Shape(){
		color = "green";
		filled = true;
	}
	
	Shape(String color,boolean filled){
		this.setFilled(filled);
		this.setColor(color);
		
	}

	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString(){
		return "A Shape with color of "+ color +" and "+( isFilled() ?( "filled" ): ("not filled"));
	}
	
	public abstract double getArea();
	public abstract double getPerimeter();
	
}
