package concurrency;

public class RuleThirtyThread extends Thread {
	int width;
	int generations;
	int last;
	int first;
    private int min = -1;
    private int max = -1;
    int[] current;
    private int[] next;
    boolean solved = false;
    int[] ruleset = new int[]{0,0,0,1,1,1,1,0};

    public RuleThirtyThread(int generations,int min, int max) {
        //if (min >= max || min < 0 || max < 0) {
       //     throw new IllegalArgumentException("Bad arguments");
       // }
        this.min = min;
        this.max = max;
        //System.out.print(max-min);
       this.generations = generations;
        
    }

    public void run() {
    	 
	for(int i=1;i<generations;i++){
		next = new int [width];
			
		for(int j=0;j<width;j++){
			
			int left, mid, right;
			if(j==0){
				left=this.first;
				mid = current[j];
				right  = current[j+1];
			}
			else if(j==width-1){ 
				left   =current[j-1];
				mid    = current[j];
				right=this.last;
				}
			else{
			left   = current[j-1];
			mid    = current[j];
			right  = current[j+1];
			}
			next[j] = rules(left, mid, right);
			//current[j] = next[j];
			//System.out.print(next[j]);
			current = next;
			//System.out.print("\n");
			}

        }
		for(int l=0;l<current.length;l++){
			System.out.print(next[l]);
		}
	    this.solved = true;
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

    public void setLast(int last) throws Exception {
       this.last = last;
    }

    public int getFirst() throws Exception {
        
        return this.first;
    }

	public void setFirst(int first) {
		this.first = first;
		
	}
}
