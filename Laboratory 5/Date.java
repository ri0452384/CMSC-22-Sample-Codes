import java.util.*;
/*
Coded by: Rayven Ingles
All (rights)wrongs (reserved)reversed 2016

*/


public class Date{
	
	private int year; //must be between 1000-9999
	private int month; // only 1-12
	private int day; // between 1-31
	
	Date(){
		this.setDate(1000,1,1);
	}
	
	Date(int y, int m, int d){
		
		this.setDate(y,m,d);
	}
	
	public int getYear(){
		int ans = year;
		return ans;
	}
	
	public int getMonth(){
		int ans = month;
		return ans;
	}
	
	public int getDay(){
		int ans = day;
		return ans;
	}
	
	public void setYear(int y){
		
		this.setDate(y, month, day);
	}
	
	public void setMonth(int m){
		
		this.setDate(year, m, day);
	}
	
	public void setDay(int d){
		
		this.setDate(year,month,d);
	}
	//set date implements the error checking, which is then used by all setter methods
	public void setDate(int y, int m, int d){
		//year is expected to be a 4 digit number only
		if(y > 9999 && y<1000){
			throw new IllegalArgumentException("Year must only be between 1000-9999!");
		}
		year = y;
		//month error checking
		if(month>12 && month < 1){
			throw new IllegalArgumentException("Month must be between 1-12!");
		}
		month = m;
		//to check the number of days in each month, leap year still to be implemented
		if(m == 1 || m == 3 || m==5 || m== 7 || m == 8 || m== 10 || m == 12 ){
			if(d < 1 && d > 31){
				throw new IllegalArgumentException(months[m]+" is only between 1-31.");
			}
			day = d;
		}else if(m == 2){
			if(d < 1 && d > 29){
				throw new IllegalArgumentException(months[m]+" is only between 1-28.");
			}
			day = d;
		}else{
			if(d < 1 && d > 30){
				throw new IllegalArgumentException(months[m]+" is only between 1-30.");
			}
			day = d;
		}
		
	}
	
	public String toString(){
		String result = String.format("%02d/%02d/%4d",day,month,year);
		return result;
	}
	
	
	private static final String[] months = {"Unknown","January", "February", "March", "April", "May", "June", "July","August","September", "October", "November", "December"};
	
	
}