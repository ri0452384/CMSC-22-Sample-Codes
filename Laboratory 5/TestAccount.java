/*
 * A Test Driver for the Account class.
 */
public class TestAccount {
   public static void main(String[] args) {
      // Test constructor and toString()
      Account a = new Account(52384, 9999.99);
      System.out.println(a);  // toString()
 
      // Test Setters and Getters
      a.setBalance(2012d);
	  System.out.println("Balance is: " + a.getBalance());
      a.credit(999.99);
	  System.out.println("Balance is: " + a.getBalance());
      a.debit(999999d);
      System.out.println(a.toString());  // run toString() to inspect the modified instance
      
     
      // Test setDate()
      a.setBalance( 999999999999999999.99);
      System.out.println(a);  // toString()
   }
}