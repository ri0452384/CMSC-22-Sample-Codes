import java.util.*;

public class OddEven{
	
	public void determine(int n){
		if(n % 2 != 0){
			System.out.println("Odd Number");
		}else{
			System.out.println("Even Number");
		}
			
	}
	
	public static void main(String[] args){
		
		OddEven test = new OddEven();
		Scanner input = new Scanner(System.in);
		int x = input.nextInt();
		test.determine(x);
		System.out.println("BYE!");
				
	}
	
}

