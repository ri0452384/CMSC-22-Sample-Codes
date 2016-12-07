package concurrency;

public class Rule30ThreadTest {
	 	//public static final int MIN = 1;
	
	    public static final int MAX = 10;
	    public static final int THREAD_COUNT = 4;
		private static int[] current;

	    public static void main(String[] args) throws Exception {
	        long startTime = System.currentTimeMillis();
	        int width;
	        // create 4 CountDivisorsThread as workers
	        RuleThirtyThread[] worker = new RuleThirtyThread[THREAD_COUNT];
	        
	        width = 2*(MAX - 1) +1;
	        // divide total range to equal chunks..
	        int integersPerThread = width / THREAD_COUNT;
	        // starting point for first thread
	        int start=0;
	        // end point for range of ints
	        int end = start + integersPerThread;

	        current = new int[width];
	    	//if(generations == 1)
				for (int f = 0; f < width-1; f++) {
				current[f] = 0;
				}
				//All cells start with state 0, except the center cell has state 1.
				current[width/2] = 1;
	        
	        // initialize the tasks of our workers by assigning them to chunks of work
	        for (int i = 0; i < THREAD_COUNT; i++) {
	            worker[i] = new RuleThirtyThread(MAX,start, end);
	            worker[i].width = end-start;
	            //System.out.println(worker[i].width);
	            if(i == THREAD_COUNT -1){
	            	worker[i].current = new int[end-start -1];
	            }else{
	            	worker[i].current = new int[end-start];
	            }
	            for(int j=0;j<worker[i].current.length;j++){
	            	if(i == THREAD_COUNT -1){
	            		worker[i].current[j] = current[end-start+j];
	            		
		            }else{
		            	worker[i].current[j] = current[start+j];
		            	
		            }
	            		
	            }
	            
	            // Determine the range of ints for the next thread.
		            start = end + 1;
		            if (i == THREAD_COUNT - 1) {
		                // make sure last thread processes up until MAX
		                end = MAX;
		            }else{
		            	end = start + integersPerThread+1;
		            }
	        }

	        // start our workers by calling start() method
	       
	        for (int i = 0; i < THREAD_COUNT; i++) {
	            
	        	 //this will call run();
	            	worker[i].start();
	           
	        }
	       
	        
	        // we want to wait for the workers to die before displaying the final results!
	        for (int i = 0; i < THREAD_COUNT; i++) {
	            while (worker[i].isAlive()) {
	                try {
	                    worker[i].join();
	                } catch (InterruptedException e) {
	                    System.err.println("thread interrupted: " + e.getMessage());
	                }
	            }
	        }

	      
	        System.out.println("\nRESULTS");
	       // System.out.println("int with max divisors: " + whichInt);
	        //System.out.println("divisor count: " + globalMaxDivisorCount);
	        System.out.println("time consumed in ms: " + (System.currentTimeMillis() - startTime));

	    }
}
