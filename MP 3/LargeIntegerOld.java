import java.io.*;
import java.util.*;

public class LargeInteger{

	int sign; //can be either zero, -1(negative) or +1(positive)
	int[] mag; //to be used for storing multiple int values; magnitude of the LargeInteger
	int intLength; // used for number of elements in mag array
	int offset = 0; // used to navigate the digits, together when variable intLength
	
	int bitCount=-1; //number of bits for the integer value
	int bitLength = -1; //to be used for the byte array
	int lowestSetBit = -2; 
	int firstNonzeroByte = -2; //index of lowest-order byte in magnitude that contains a nonzero int
	int firstNonzeroInt = -2;//indez of lowest-order int in magnitude that contains a nonzero int

	LargeInteger(){
		sign = 0;
		intLength = 1;
		mag = new int[intLength];
		mag[0] =  0;
		offset = 0;
	}
	
	LargeInteger(long value){
		if(value < 0){
			sign = -1;
			value = -value;
			
		}else{
			sign = 1;
		}
		
		int top = (int)(value >>> 32);
		//to check if the long can be contained in a single int value
		if(top ==0){
			intLength = 1;
			mag = new int[1];
			mag[0] = (int) value;
			offset = 0;
						
			
		}else{
			intLength = 2;
			mag = new int[2];
			mag[0] = top;
			mag[1] = (int) value;
			offset = 0;
		}
		
		
	}
	
	LargeInteger(LargeInteger a){
		if(a.offset > 0 || a.mag.length != a.intLength){
			mag = new int[a.intLength];
			for(int i=0;i<a.intLength;i++){
				mag[i] = a.mag[a.offset + i];
			}
		}else{
			mag = a.mag;
			intLength = mag.length;
			
		}
		this.sign = a.sign;//can be just a.sign but in case a is zero
		intLength = a.mag.length;
		offset = 0;
	}
	LargeInteger(int[] a, int length){
		mag = a;
		intLength = length;
		offset = 0;
	}
	
	LargeInteger(String value){
		
		int radix  = 10; // base 10
		int pointer = 0; //to navigate the String input
		int numDigits;
		int length = value.length();
		
		sign = 1;
		int index = value.lastIndexOf('-');
		if(index != -1){
			if(index == 0){
				if(value.length() ==  1){
					System.out.println("you only entered the negative symbol.");
				}
				sign = -1;
				pointer = 1;
				
			}else{
				System.out.println("Do not enter invalid characters between numbers.");
			}
		}
		
		
		while(pointer < length && Character.digit(value.charAt(pointer),radix)==0){
			pointer++;
		}
		if(pointer == length){
			sign = 0;
			mag = new int[1];
			mag[0] = 0;
			intLength = 1;
			return;
		}else{
			numDigits = length - pointer;
			
		}
		
		//declaration of array
		int numBits = (int)(((numDigits * 3402) >>>10 ) +1 );
		int numWords = (numBits + 31) /32;	
		intLength = numWords;
		mag = new int[numWords];

		int firstIntLength = numDigits % 9;
		if(firstIntLength==0){ 
			firstIntLength = 9;
		}
		//placeholder for the current set of integers
		String group = value.substring(pointer, pointer += firstIntLength);
		
		//for the first group of ints in the array
		mag[mag.length -1] = Integer.parseInt(group,radix);
		if(mag[mag.length -1] < 0){
			System.out.println("non-digit found");
		}
		
		//the rest of the int groups
		int tempRadix = 0x3b9aca00;//masked equivalent for 10
		int groupVal = 0; // to be used as value of the current group
		while(pointer < value.length()){
			group = value.substring(pointer,pointer += 9);
			
			groupVal = Integer.parseInt(group,radix);
			if(groupVal < 0){
				System.out.println("non-digit found");
			}
			breakdown(mag,tempRadix,groupVal);
			
		}
		
	}

	
	
	LargeInteger(LargeInteger a, int s){
		if(a.offset > 0 || a.mag.length != a.intLength){
			mag = new int[a.intLength];
			for(int i = 0; i< a.intLength;i++){
				mag[i] = a.mag[a.offset+i];
				
			}
		}else{
			mag = a.mag;
		}
		
		this.sign = (a.intLength ==0) ? 0 : sign;
	}
	/*
	LargeInteger(int[] magnitude,int s){
		mag = magnitude;
		sign = s;
		intLength =magnitude.length;
		offset = 0;
	}
	*/
	
	public int length(){
		
		return this.intLength;
	}
	
	public LargeInteger add(String s){
		LargeInteger addend = new LargeInteger(s);
		return this.add(addend);
		
	}
	
	public LargeInteger add(long number){
		LargeInteger addend = new LargeInteger(number);
		return this.add(addend);
	}
	
	public LargeInteger add(LargeInteger addend){
		
		int [] answer;
		if(addend.sign == 0){
			return this;
		}
		if(sign == 0){
			return addend;
		}
		
		if(addend.sign == sign){
			return new LargeInteger(add(mag,addend.mag), sign);
		}
		
		int cmp = intArrayComp(mag,addend.mag);
		if(cmp == 0){
			return new LargeInteger();
		}
		answer = (cmp>0 ? subtract(mag,addend.mag):subtract(addend.mag, mag));
		
		answer = removeZeros(answer);
		return new LargeInteger(answer, cmp * sign);
	}
	
	private static int[] add(int[]a, int[]b){
		
		if(a.length < b.length){ 
		//swap 2 arrays
			int[] tmp = a;
			a = b;
			b = tmp;
		}
		
		int aIndex = a.length;
		int bIndex = b.length;
		int result[] = new int[aIndex];
		long sum = 0;
		
		while(bIndex > 0){
			sum = (a[--aIndex] & 0xffffffffL) + (b[--bIndex] & 0xffffffffL) + (sum >>>32);
			result[aIndex] = (int) sum;
			
		}
		//copy the rest while propagating carry
		boolean carry = (sum >>> 32 !=0);
		while(aIndex > 0 && carry){
			carry = ((result[--aIndex] = a[aIndex] + 1) == 0);
		}
		
		while(aIndex > 0){
			result[--aIndex] = a[aIndex];
		}
		//array is increased by 1 when it is needed
		if(carry){
			int newLen = result.length +1;
			int tmp[] = new int[newLen];
			for(int i=1;i<newLen;i++){
				tmp[i] = result[i-1];
			}
			tmp[0] = 0x01;
			result = tmp;
		}
		
		return result;
		
	}
	
	public LargeInteger subtract(String s){
		LargeInteger b = new LargeInteger(s);
		return this.subtract(b);
	}
	
	public LargeInteger subtract(long number){
		LargeInteger b = new LargeInteger(number);
		return this.subtract(b);
	}
	
	public LargeInteger subtract(LargeInteger b){
		int[] resultMag;
		if(b.sign == 0){
			return this;
		}
		if(this.sign == 0){
			return b.negate();
		}
		
		if(b.sign != this.sign){
			
			return new LargeInteger(add(mag,b.mag), sign);
		}
		
		int cmp = intArrayComp(mag, b.mag);
		
		if(cmp == 0){
			return new LargeInteger();
		}
		resultMag = (cmp>0 ? subtract(mag,b.mag) : subtract(b.mag,mag));
		return new LargeInteger(resultMag,cmp * sign);
		
	}
	

	
	private static int[] subtract(int[] big,int[] little){
		int bigIndex = big.length;
		int[] result = new int[bigIndex];
		int littleIndex = little.length;
		long difference = 0;
		
		while(littleIndex > 0){
			difference = (big[--bigIndex] & 0xffffffffL) - (little[--littleIndex] & 0xffffffffL) + (difference >>>32);
			result[bigIndex] = (int) difference;
		}
		
		boolean borrow = (difference >>32 !=0);
		while((bigIndex > 0) && borrow){
			borrow = ((result[--bigIndex] = big[bigIndex]-1) == -1);
			
		}
		while(bigIndex > 0){
			result[--bigIndex] = big[bigIndex];
			
		}
		return result;
	} 
	
	public LargeInteger multiply(String s){
		LargeInteger b = new LargeInteger(s);
		return this.multiply(b);
	}
	
	public LargeInteger multiply(long number){
		LargeInteger b = new LargeInteger(number);
		return this.multiply(b);
	}
	
	public LargeInteger multiply(LargeInteger val){
		if(val.sign == 0 || sign ==0){
			return new LargeInteger();
		}
		int[] answer = multiplyArrays(mag,val.mag, mag.length ,val.mag.length);
		answer = removeZeros(answer);
		return new LargeInteger(answer,sign * val.sign);
	}
	//multiply helper which needs two arrays and outputs the result in the result array
	private int[] multiplyArrays(int[] x,int[] y, int xLength,int yLength){
		int xIni = xLength -1;
		int yIni = yLength -1;
		int[] result=null;
		
		if(result == null || result.length < (xLength + yLength)){
			result = new int[xLength + yLength];
		}
		
		long carry = 0;
		for(int j = yIni, k = yIni + 1 + xIni ; j >=0 ; j--,k--){
			long prod = (y[j] & 0xffffffffL) * (x[xIni] & 0xffffffffL) + carry;
			result[k] = (int) prod;
			carry = prod >>> 32;
		}
		result[xIni] = (int) carry;
		
		for(int i = xIni-1; i>=0;i--){
			carry = 0;
			for(int j=yIni,k=yIni +1 +i; j>=0; j--,k--){
				long prod = (y[j] & 0xffffffffL) * x[i] & 0xffffffffL + (result[k] & 0xffffffffL) + carry;
				result[k] = (int) prod;
				carry = prod >>> 32;
			}
			result[i] = (int) carry;
			
		}
		return result;
		
	}
	
	
	public LargeInteger divide(LargeInteger divisor){
		LargeInteger q = new LargeInteger();
		LargeInteger r = new LargeInteger();
		LargeInteger a = new LargeInteger(this);
		LargeInteger b = new LargeInteger(divisor);
		
		a.divide(b,q,r);
		LargeInteger ans =  new LargeInteger(q,this.sign*divisor.sign);
		return ans;
	}
	
	void divide(LargeInteger b,LargeInteger quotient,LargeInteger r){
		if(b.intLength == 0){
			System.out.println("ERR");
			return;
		}
		
		//dividend is zero
		if(intLength == 0){
			quotient.intLength = quotient.offset = r.intLength = r.offset = 0;
			return;
			
		}
		int cmp = this.compare(b);
		//dividend is less than divisor, expected to be zero
		if(cmp < 0){
			quotient = new LargeInteger();
			r.copyMag(this);
			return;
		}
		
		//dividend equal to divisor is expected to be 1
		if(cmp == 0){
			quotient = new LargeInteger((long) 1);
			quotient.offset = r.intLength = r.offset = 0;
			return;
		}
		
		quotient.clear();
		
		//in case of single word division
		if(b.intLength ==1){
			r.copyMag(this);
			r.divOneWord(b.mag[b.offset], quotient);
			return;
		}
		
		
		
		//preserving divisor
		int[] tempDiv = new int[b.intLength];
		
		for(int i=0;i<b.intLength;i++){
			tempDiv[i] = b.mag[b.offset +i];
		}
		int divLength = b.intLength;
		
		
		if(r.mag.length < intLength + 1){
			r.mag = new int[intLength + 1];
		}
		
		for(int i=0;i<intLength;i++){
			r.mag[i+1] = mag[i+offset];
		}
		r.intLength = intLength;
		r.offset = 1;
		
		int nLength = r.intLength;
		
		//redefine quotient array
		int max = nLength - divLength + 1;
		if(quotient.mag.length < max){
			quotient.mag = new int[max];
			quotient.offset = 0;
		}
		quotient.intLength = max;
		int[] q = quotient.mag;
		
		//normalize divisor
		int shift = 32-bitLength(tempDiv[0]);
		if(shift > 0){
			LargeInteger.longShiftLeft(tempDiv,divLength,shift);
			r.shiftLeft(shift);
		}
		
		//reinsert leading zeros in remainder
		if(r.intLength == nLength){
			r.offset = 0;
			r.mag[0] = 0;
			r.intLength++;
		}
		
		int divHigh = tempDiv[0];
		long divHighLong = divHigh & 0xffffffffL;
		int divLow = tempDiv[1];
		int[] qWord = new int[2];
		
		for(int j=0;j<max;j++){
			int qDigit = 0;
			int qRem = 0;
			boolean skipCorrection = false;
			int nh = r.mag[j+r.offset];
			int nh2 = nh + 0x80000000;
			int nm = r.mag[j+1 + r.offset];
			
			if(nh == divHigh){
				//partial quotient and remainder initialization for the first element in divisor array
				qDigit = ~0;//compliment of zero
				qRem = nh + nm;
				skipCorrection = qRem + 0x80000000 < nh2;
			}else{
				//partial quotient and remainder on a shift
				long nChunk = (((long) nh) << 32) | (nm & 0xffffffffL);
				if(nChunk >= 0){
					qDigit = (int) (nChunk / divHighLong);
					qRem = (int) (nChunk - (qDigit * divHighLong));
				}else{
					//partial quotient and remainder initialization while processing the rest
					divWord(qWord,nChunk,divHigh);
					qDigit = qWord[0];
					qRem = qWord[1];
				}
			}
			
			if(qDigit ==0){
				continue;
			}
			//loop the approximation until the value is acceptable
			if(!skipCorrection){
				long nl = r.mag[j+2+r.offset] & 0xffffffffL;
				long rs = ((qRem & 0xffffffffL) << 32) | nl;
				long estimate = (divLow & 0xffffffffL) * (qDigit & 0xffffffffL);
				
				if((estimate+Long.MIN_VALUE) > (rs+Long.MIN_VALUE)){
					qDigit--;
					qRem = (int)((qRem & 0xffffffffL)+ divHighLong);
					if((qRem & 0xffffffffL) >= divHighLong){
						estimate = (divLow & 0xffffffffL) + (qDigit & 0xffffffffL);
						rs = ((qRem & 0xffffffffL) << 32) | nl;
						if((estimate+Long.MIN_VALUE) > (rs+Long.MIN_VALUE)){
							qDigit--;
						}
					}
				}
			}
			
			//multiply then subtract
			r.mag[j+r.offset] = 0;
			int borrow = getCarry(r.mag,tempDiv,qDigit,divLength,j+r.offset);
			
			
			if(borrow + 0x80000000 > nh2){
				//add back to the dividend
				int x = restoreDiv(tempDiv, r.mag,j+1+r.offset);
				//System.out.println(x);
				qDigit--;
			}
			//q[j] = qDigit;
			quotient.mag[j] = qDigit;
		}
		
		if(shift>0){
			r.shiftRight(shift);
			
		}
		
		r.norm();
		quotient.norm();
	}
	
	//adds 1x divisor back to the dividend at a given offset o
	private int restoreDiv(int[] a,int[] result,int o){
		long carry = 0;
		for(int j=a.length-1; j>= 0; j--){
			long sum = (a[j] & 0xffffffffL) + (result[j+offset] & 0xffffffffL) + carry;
			result[j+offset] = (int) sum;
			carry = sum >>> 32;
		}
		return (int) carry;
	}
	
	
	//returns the carry multiplying a by x, then subtracts the product from q
	private int getCarry(int[] q,int[]a,int x,int len,int offset){
		long xLong = x & 0xffffffffL;
		long carry = 0;
		offset +=len;
		
		for(int j=len-1 ; j>=0 ; j--){
			long prod = (a[j] & 0xffffffffL) * xLong + carry;
			long diff = q[offset] - prod;
			q[offset--] = (int) diff;
			carry = (prod >>> 32) + (((diff & 0xffffffffL) > (((~(int)prod) & 0xffffffffL))) ? 1 : 0);
			
		}
		return (int) carry;
		
	}
	
	void copyMag(LargeInteger val){
		int len = val.intLength;
		if(mag.length < len){
			mag = new int[len];
		}
		for(int i=0;i<len;i++){
			mag[i] = val.mag[i];
		}
		intLength = len;
		offset = 0;
		
	}
	
	void copyMag(int[] a, int length){
		if(mag.length < length){
			mag = new int[length];
		}
		for(int i=0;i<length;i++){
			mag[i] = a[i];
		}
		intLength = length;
		offset = 0;
	}
	
	 private void divWord(int[] result, long n, int d) {
         long divLong = d & 0xffffffffL;
 
         if (divLong == 1) {
             result[0] = (int)n;
             result[1] = 0;
             return;
         }
 
         //quotient & rem declaration
         long q = (n >>> 1) / (divLong >>> 1);
         long r = n - q*divLong;
 
         // Correct the approximation
         while (r < 0) {
             r += divLong;
             q--;
         }
         while (r >= divLong) {
             r -= divLong;
             q++;
         }
 
         
         result[0] = (int)q;
         result[1] = (int)r;
     }
	
	 void divOneWord(int div,LargeInteger quotient){
		long divLong = div & 0xffffffffL;
		
		if(intLength == 1){
			long remValue = mag[offset] & 0xffffffffL;
			quotient.mag[0]= (int)(remValue / divLong);
			quotient.intLength = quotient.mag[0] == 0 ? 0 : 1;
			quotient.offset = 0;
			
			mag[0] = (int)(remValue - (quotient.mag[0] * divLong));
			offset = 0;
			intLength = (mag[0] == 0) ? 0: 1;
			
			return;
		}
		
		if(quotient.mag.length < intLength){
			quotient.mag = new int[intLength];;
		}
		quotient.offset = 0;
		quotient.intLength = intLength;
		
		int shift = 32 - bitLength(div);
		int rem = mag[offset];
		long remLong = rem & 0xffffffffL;
		if(remLong < divLong){
			quotient.mag[0] = 0;
		}else{
			quotient.mag[0] = (int)(remLong/divLong);
			rem = (int)(remLong - (quotient.mag[0] * divLong));
			remLong = rem & 0xffffffffL;
		}
		
		int xLength = intLength;
		int[] qWord = new int[2];
		while(--xLength > 0){
			long divEst = (remLong << 32) | (mag[offset+intLength - xLength] & 0xffffffffL);
			if(divEst >= 0){
				qWord[0] = (int)(divEst / divLong);
				qWord[1] = (int)(divEst - (qWord[0] * divLong));
				
			}else{
				divWord(qWord,divEst,div);
				
				
			}
			quotient.mag[intLength- xLength] = qWord[0];
			rem = qWord[1];
			remLong = rem & 0xffffffffL;
			
		}
		
		//undo normalize
		if(shift > 0){
			mag[0] = rem %= div;
		}else{
			mag[0] = rem;
		}
		intLength = (mag[0] ==0) ? 0 : 1;
		
		
		quotient.normalize();
	}
	
	 void norm(){
		if(intLength == 0){
			offset = 0;
			return;
		}
		
		int index = offset;
		if(mag[index] !=0){
			return;
		}
		int indexBound = index + intLength;
		do{
			index ++;			
		}while(index < indexBound && mag[index] == 0);
		
		int numZeros = index - offset;
		intLength -= numZeros;
		offset = (intLength == 0 ? 0 : offset + numZeros);
	}
	
	  int[] shiftLeft(int[] a, int length, int n){
		
		int numInts = n >>> 5;
		int numBits = n & 0x1F;
		int numBitsHigh = bitLength(mag[0]);
		
		if(n <= (32-numBitsHigh)) {
			longShiftLeft(a, length, numBits);
			return a;			
		}else{
			if(numBits <= (32-numBitsHigh)){
				int[] ans = new int[numInts + length];
				for(int i=0;i<length;i++){
					ans[i] = a[i];
				}
				longShiftLeft(ans,ans.length,numBits);
				return ans;
			}else{
				int[] ans = new int[numInts + length + 1];
				for(int i = 0;i <length; i++){
					ans[i] = a[i];
				}
				intShiftRight(ans,ans.length,32-numBits);
				return ans;
			}	
		}
	}
	
	void shiftLeft(int n){
		if(intLength == 0){
			return;
		}
		int numInts = n >>> 5;
		int numBits = n & 0x1F;
		int numBitsHigh = this.bitLength(mag[offset]);
		
		
		if(n <=(32-numBitsHigh)){
			longShiftLeft(numBits);
			return;
		}
		
		int newLength = intLength + numInts + 1;
		if(numBits <= 32-numBitsHigh){
			newLength--;
		}
		if(mag.length < newLength){
			int[] tmp = new int[newLength];
			for(int i=0;i<intLength;i++){
				tmp[i] = mag[offset+i];
			}
			copyMag(tmp,newLength);
		}else if(mag.length - offset >= newLength){
			
			for(int i=0;i<newLength;i++){
				mag[offset + intLength + i] = 0;
			}
			
		}else{
			for(int i=0;i<intLength;i++){
				mag[i] = mag[offset + i];
			}
			for(int i=intLength;i<newLength;i++){
				mag[i] = 0;
			}
			offset = 0;			
		}
		intLength = newLength;
		if(numBits == 0){
			return;
		}
		if(numBits <= (32-numBitsHigh)){
			longShiftLeft(numBits);
		}else{
			intShiftRight(32-numBits);
		}
	}
	
	private void longShiftLeft(int n){
		int[] tmp = mag;
		int n2 = 32-n;
		
		for(int i=offset, z = tmp[i], k = i+intLength-1; i<k;i++){
			int y = z;
			z = tmp[i+1];
			tmp[i] = (y << n) | (z >>> n2);
		}
		tmp[offset + intLength+1] <<= n;
	}
	
	static void longShiftLeft(int[] a, int len,int s){
		if(len == 0 || s ==0){
			return;
		}
		int shift2 = 32-s;
		for(int i = 0, c = a[i], m = i+len-1; i<m;i++){
			int temp = c;
			c = a[i+1];
			a[i] = (temp << s) | (c >>> shift2);
		}
		a[len-1] <<= s;
		return;
	}
	
	 void shiftRight(int n){
		if(intLength ==0){
			return;
		}
		int numInts = n >>> 5;
		int numBits = n & 0x1F;
		this.intLength = -numInts;
		if(numBits == 0){
			return;
		}
		int numBitsHigh = bitLength(mag[offset]);
		if(numBits >=numBitsHigh){
			this.longShiftLeft(32-numBits);
			this.intLength--;
		}else{
			intShiftRight(numBits);
		}
			
	}
	private void intShiftRight(int n){
		int[] val = mag;
		int n2 = 32-n;
		
		for(int i= offset + intLength - 1, x=val[i]; i > offset; i--){
			int tmp = x;
			x = val[i-1];
			
			val[i] = (x << n2) | (tmp >>> n);
		}
		val[offset] >>>= n;
	}
	
	void intShiftRight(int[] a, int length, int s){
		int n2 = 32-s;
		for(int i=length-1,c=a[i];i>0;i--){
			int b=c;
			c=a[i-1];
			a[i] = (c << n2) | (b >>> s);
		}
		a[0] = s;
	}
	
	void clear(){
		offset = intLength = 0;
		for(int i=0,n=mag.length;i<n;i++){
			mag[i] = 0;
		}
		
	}

public LargeInteger abs(){
	
		return (sign >=0 ? this: this.negate());
	
}

public LargeInteger negate(){
	
		return new LargeInteger(this.mag, -this.sign);
}

	private static void breakdown(int[] magnitude, int radix, int val){
		long radLong = radix & 0xffffffffL;
		long vLong = val & 0xffffffffL;
		int len = magnitude.length;
		
		
		long prod = 0;
		long carry = 0;
		
		for(int i=len-1;i>=0;i--){
			prod = radLong * (magnitude[i] & 0xffffffffL) + carry;
			magnitude [i] = (int) prod;
			carry = prod >>> 32;
			
		}
		
		//adding the carry
		long sum = (magnitude[len-1] & 0xffffffffL) + vLong;
		magnitude[len-1] = (int) sum;
		carry = sum >>> 32;
		
		for(int i=len-2;i >= 0;i--){
			sum = (magnitude[i] & 0xffffffffL) + carry;
			magnitude[i] = (int) sum;
			carry = sum >>> 32;
		}
		
	}

	private static int intArrayComp(int[] a, int[] b){
		if(a.length < b.length){
			return -1;
		}
		if(a.length > b.length){
			return 1;
		}
		 
		 for(int i=0;i<a.length;i++){
			 long x = a[i] & 0xffffffffL;
			 long y = b[i] & 0xffffffffL;
			 
			 if(x < y){
				 return -1;
			 }
			 if(x > y){
				 return 1;
			 }
			 
		 }
		 return 0;
	}
	
	private int compare(LargeInteger b){
		if(this.intLength < b.intLength){
			return -1;
		}
		if(this.intLength > b.intLength){
			return 1;
		}
		
		for(int i=0; i<intLength;i++){
			
			int b1 = mag[offset+i] + 0x80000000;
			int b2 = b.mag[b.offset + i] + 0x80000000;
			if(b1 < b2){
				return -1;
			}
			if(b1 > b2){
				return 1;
			}
		}
		return 0;		
	}
	//decision tree to count number of bits in the int a taken as a single word
	private int bitLength(int a){
		return (a < 1<<15 ? 
				(a < 1<<7 ? 
				(a < 1<<3 ? 
				(a < 1<<1 ? (a < 1<<0 ? (a<0 ? 32 : 0) : 1) : (a < 1<<2 ? 2 : 3)) : 
				(a < 1<<5 ? (a < 1<<4 ? 4 : 5) : (a < 1<<6 ? 6 : 7))) : 
				(a < 1<<11 ? 
				(a < 1<<9 ? (a < 1<<8 ? 8 : 9) : (a < 1<<10 ? 10 : 11)) : 
				(a < 1<<13 ? (a < 1<<12 ? 12 : 13) : (a < 1<<14 ? 14 : 15)))) : 
				(a < 1<<23 ? 
				(a < 1<<19 ? 
				(a < 1<<17 ? (a < 1<<16 ? 16 : 17) : (a < 1<<18 ? 18 : 19)) : 
				(a < 1<<21 ? (a < 1<<20 ? 20 : 21) : (a < 1<<22 ? 22 : 23))) : 
				(a < 1<<27 ? 
				(a < 1<<25 ? (a < 1<<24 ? 24 : 25) : (a < 1<<26 ? 26 : 27)) :  
				(a < 1<<29 ? (a < 1<<28 ? 28 : 29) : (a < 1<<30 ? 30 : 31)))));
	}
	private static int[] removeZeros(int[] val){
		int byteLength = val.length;
		int pointer;
		for(pointer = 0;pointer<val.length && val[pointer] == 0;pointer++){
			//do nothing, just to get to the first nonzero digit
		}
		if(pointer>0){
			int answer[] = new int[val.length-pointer];
			for(int i=0;i<val.length-pointer;i++){
				answer[i] = val[pointer +1];
			}
			return answer;
		}
		return val;
	}
	
	
	public String toString(){
		String group="";
		for(int i=0;i<intLength;i++){
			group += Integer.toString(mag[i])+ " ";
			
		}
		
		return group;
	}

}