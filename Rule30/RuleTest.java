

public class RuleTest {
	public static void main(String[] args){	
		long startTime = System.currentTimeMillis();
		RuleThirty thirty = new RuleThirty();
	int number = 10;// s.nextInt();
	thirty.input = number;
	thirty.run();
	
	 System.out.println("\ntime consumed in ms: " + (System.currentTimeMillis() - startTime));
}
}
