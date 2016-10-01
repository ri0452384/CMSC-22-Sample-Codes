import java.util.*;
/*
Coded by: Rayven Ingles
All (rights)wrongs (reserved)reversed 2016

*/

public class Account{
	
	private int accountNumber;
	private double balance;
	
	Account(int acc, double bal){
		accountNumber = acc;
		this.setBalance(bal);
	}
	
	Account(int acc){
		accountNumber = acc;
		this.setBalance(0.00);
		
	}
	
	public int getAccountNumber(){
		int tmp = accountNumber;
		return tmp;
	}
	
	public double getBalance(){
		double bal = balance;
		return bal;
	}
	
	public void setBalance(double bal){
		double total = balance + bal;
		//checks if bal is negative and total will be less than zero
		if(total < 0){
			throw new IllegalArgumentException("You will be broke if you do that!");
		}else{
			balance = total;
		}
		
	}
	public void credit(double amount){
		
		setBalance(-amount);
		
	}
	
	public void debit(double amount){
		
		setBalance(amount);
		
	}
	
	public String toString(){
				
		String output = String.format("A/C no: %1d , Balance= %.2f", accountNumber, balance);
		return output;
	}
	
	
	
	
}