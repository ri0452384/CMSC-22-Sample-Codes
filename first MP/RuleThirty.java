import java.util.*;
import java.util.Scanner;

public class RuleThirty{
	
	
	int[] ruleset = new int[]{0,0,0,1,1,1,1,0}; //to follow rule 30, can be changed to any other type of rule later on
	int generation = 0;
	int[][] cells; //for this exercise I used a 2d array
	int width; // to be used as the number of cells per row
	int input; // to be used as number of rows
	
		
	public void generate(){
	if(input<=0){
		System.out.println("Number must be greater than zero!"); //exception handling(lol)
		}
	//width = 2*(input - 1) +1;
	width = input; //since we have to generate a square array
	cells = new int[input][width];
	//this block is for generating the entire first row only
		for (int f = 0; f < width-1; f++) {
		cells[0][f] = 0;
		}
		//All cells start with state 0, except the center cell has state 1.
		cells[0][width/2] = 1;
		
			
		for(int i=1;i<input;i++){
		int[] nextgen = new int [width];
			
		for(int j=0;j<width;j++){
			
			int left, mid, right;
			if(j==0){
				left=0;
				mid = cells[generation][j];
				right  = cells[generation][j+1];
			}
			else if(j==width-1){ 
				left   = cells[generation][j-1];
				mid    = cells[generation][j];
				right=0;
				}
			else{
			left   = cells[generation][j-1];
			mid    = cells[generation][j];
			right  = cells[generation][j+1];
			}
			nextgen[j] = rules(left, mid, right);
			cells[i][j] = nextgen[j];
			}
			//Increment the generation counter.
			cells[i]=nextgen;
			generation++;
		}
 
	//transition of 1 row
   
	
    
	
	}
	
	
	
	
	public int rules(int a, int b, int c){
	if      (a == 1 && b == 1 && c == 1) return ruleset[0];
    else if (a == 1 && b == 1 && c == 0) return ruleset[1];
    else if (a == 1 && b == 0 && c == 1) return ruleset[2];
    else if (a == 1 && b == 0 && c == 0) return ruleset[3];
    else if (a == 0 && b == 1 && c == 1) return ruleset[4];
    else if (a == 0 && b == 1 && c == 0) return ruleset[5];
    else if (a == 0 && b == 0 && c == 1) return ruleset[6];
    else if (a == 0 && b == 0 && c == 0) return ruleset[7];
	else return 0;
    }

	public static void main(String[] args){	
	RuleThirty test = new RuleThirty();
	Scanner s = new Scanner(System.in);
	
	//System.out.println("Please enter a number:");
	test.input = s.nextInt();
	test.generate();
	
	//System.out.println("Generated figure here:\n");
	
	for(int i=0;i<test.input;i++){
		for(int j=0;j<test.width;j++){
			
			System.out.print(test.cells[i][j]);
			
		}
		System.out.print("\n");
		
	}
	
	//System.out.println("\nThe last row is: ");
	//for(int j=0;j<test.width;j++){
			
		//	System.out.print(test.cells[test.input-1][j]);
			
		//}


}
}
