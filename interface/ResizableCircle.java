import java.math.*;
public class ResizableCircle extends Circle implements Resizable{
	
	
	
	
public ResizableCircle(double r){
	this.radius = r;
}

public void resize(int percent){
	
	if(percent > 0){
		radius =radius * (1+(percent/100));
	}else{
		radius = radius * (1-((double)-percent/100));
	}
	
	
}
	
	
	
	
}