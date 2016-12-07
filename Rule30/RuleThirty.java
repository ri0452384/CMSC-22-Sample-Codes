
public class RuleThirty extends Thread{
	
	int threads;
	int[] ruleset = new int[]{0,0,0,1,1,1,1,0}; //to follow rule 30, can be changed to any other type of rule later on
	int generation = 0;
	int[] current;
	int[] next;
	int width; // to be used as the number of cells per row
	int input; // to be used as number of rows
	
		
	public void run(){
	if(input<=0){
		System.out.println("Number must be greater than zero!"); 	}
	width = 2*(input - 1) +1;
	//width = input; //since we have to generate a square array
	current = new int[width];
	generate(1,input);
	
	}
	
	private void generate(int min,int max){
		
		//this block is for generating the entire first row only
		if(input == 1)
				for (int f = 0; f < width-1; f++) {
				current[f] = 0;
				//System.out.print(current[f]);
				}
				//All cells start with state 0, except the center cell has state 1.
				current[width/2] = 1;
				//for(int j=0;j<current.length;j++)
					//System.out.print(current[j]);
				//System.out.println("");
				 
		for(int i=1;i<input;i++){
			next = new int [width];
				
			for(int j=0;j<width;j++){
				
				int left, mid, right;
				if(j==0){
					left=0;
					mid = current[j];
					right  = current[j+1];
				}
				else if(j==width-1){ 
					left   =current[j-1];
					mid    = current[j];
					right=0;
					}
				else{
				left   = current[j-1];
				mid    = current[j];
				right  = current[j+1];
				}
				next[j] = rules(left, mid, right);
				//current[j] = next[j];
				
				
				}
				current = next;	
			}
		for(int i=0;i<current.length;i++){
			System.out.print(current[i]);
		}
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

	
}