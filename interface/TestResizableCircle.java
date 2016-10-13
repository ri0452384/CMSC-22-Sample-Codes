public class TestResizableCircle{

public static void main(String[] args){
	
	ResizableCircle rc = new ResizableCircle(50.0);
	System.out.print(rc);
	rc.resize(-30);
	System.out.print(rc);
	rc.resize(100);
	System.out.print(rc);


}



}