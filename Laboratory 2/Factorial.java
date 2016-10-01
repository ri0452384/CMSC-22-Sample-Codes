import java.util.*;

public class Factorial{
	
		int ans=0;
	public int calculate(int x){
		int counter=0;
		while(counter < x){
			
			if(x==1){
				return 1;				
			}else{
				ans = x*(calculate(x-1));
			}
			counter++;			
		}
		return ans;
		
	}

	public static void main(String[] args){
		Factorial test = new Factorial();
		Scanner input = new Scanner(System.in);
		int x = input.nextInt();
		int answer;
		answer = test.calculate(x);
		System.out.println(answer);
	}


}