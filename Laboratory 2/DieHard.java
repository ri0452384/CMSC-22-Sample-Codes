import java.util.*;

public class DieHard{

	int a = 0; // current content of Jar 1
	int b = 0; // current content of Jar 2
	int x = 0; // capacity of Jar 1
	int y = 0; // capacity of Jar 2
	int z = 0; // desired volume of either jar
	
	public void set(int first,int second,int desired){
		this.x = first;
		this.y = second;
		this.z = desired;
	}
	
	public void fillBig(){
		a = x;
	
	}
	
	public void spillToSmall(){
		b = y;
		a = a -(y-b);
	}
	
	public void emptyBig(){
		a=0;
	}
	
	public void ifPossible(int big, int small){
		a=big;
		b=small;
		
		while(a != z && b != z){
			
			if(a == z || b == z){
			System.out.println("YES");
			}
			else{
			fillBig();
			spillToSmall();
			emptyBig();
			}
		}
	}
	
	public static void main(String[] args){
	DieHard test = new DieHard();
	Scanner sc = new Scanner(System.in);
	int t = sc.nextInt();
	
	for(int i=0;i<t;i++){
	String s = sc.next();
	sc.useDelimiter(" ");
	int m = sc.nextInt();
	int n = sc.nextInt();
	int o = sc.nextInt();
	test.set(m,n,o);
	}
	
	
	
	}



}