import java.io.*;
import java.util.*;

public class LargeInteger{
	int sign; //can be either zero, -1(negative) or +1(positive)
	int[] magnitude; //to be used for storing multiple int values
	int bitCount; //number of bits for the integer value
	int bitLength = -2; //to be used for the byte array
	int lowestSetBit = -2; 
	int firstNonzeroByte = -2; //index of lowest-order byte in magnitude that contains a nonzero int
	int firstNonzeroInt = -2;//indez of lowest-order int in magnitude that contains a nonzero int
	
	public LargeInteger(){
		
	}
	
	public LargeInteger(int[] val) {
        

        if (val[0] < 0) {
            mag = makePositive(val);
            sign = -1;
        } else {
            mag = removeZeroInts(val);
            signum = (mag.length == 0 ? 0 : 1);
        }
    }
	
	private LargeInteger(long val) {
         if (val < 0) {
            sign = -1;
            val = -val;
        } else {
            sign = 1;
        }
 
        int highWord = (int)(val >>> 32);
        if (highWord==0) {
             mag = new int[1];
            mag[0] = (int)val;
        } else {
            mag = new int[2];
             mag[0] = highWord;
            mag[1] = (int)val;
         }
     }
	
	public LargeInteger(byte[] val) {
        
        if (val[0] < 0) {
            mag = makePositive(val);
             signum = -1;
        } else {
            mag = removeZeroBytes(val);
            sign = (mag.length == 0 ? 0 : 1);
        }
    }
	
	 public LargeInteger(int sign, byte[] magnitude) {
        this.mag = removeZeroBytes(magnitude);

        if (sign < -1 || sign > 1)
            throw(new NumberFormatException("Invalid sign!")); 
        if (this.mag.length==0) {
            this.sign = 0;
         } else {
            if (sign == 0)
                throw(new NumberFormatException("zero is only for 0"));
            this.sign = sign;
        }
     }
	 
	 public LargeInteger(String val) {
		int radix = 10;
        int cursor = 0, numDigits;
        int len = val.length();

        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX)
            throw new NumberFormatException("Radix out of range");
        if (val.length() == 0)
            throw new NumberFormatException("Length cannot be zero");

        // Check for at most one leading sign
         sign = 1;
        int index = val.lastIndexOf('-');
         if (index != -1) {
             if (index == 0 ) {
                //to check for negative sign in val
                 sign = -1;
                 cursor = 1;
             } else {
				 //do something?
				 System.out.println("Invalid input.");
             }
        }
        // Skip leading zeros and compute number of digits in magnitude
         while (cursor < len && Character.digit(val.charAt(cursor), radix) == 0){
			cursor++;
			}
         if (cursor == len) {
             sign = 0;
             mag = ZERO.mag;
             return;
         } else {
             numDigits = len - cursor;
         }
 
         // declare array. needs to be bigger than expected output
        int numBits = (int)(((numDigits * bitsPerDigit[radix]) >>> 10) + 1);
        int numWords = (numBits + 31) /32;
         mag = new int[numWords];
 
         // Process first (potentially short) digit group
         int firstGroupLen = numDigits % digitsPerInt[radix];
         if (firstGroupLen == 0)
             firstGroupLen = digitsPerInt[radix];
         String group = val.substring(cursor, cursor += firstGroupLen);
         mag[mag.length - 1] = Integer.parseInt(group, radix);
        if (mag[mag.length - 1] < 0)
             throw new NumberFormatException("Illegal digit");
 
         // Process remaining digit groups
        int superRadix = intRadix[radix];
        int groupVal = 0;
         while (cursor < val.length()) {
            group = val.substring(cursor, cursor += digitsPerInt[radix]);
            groupVal = Integer.parseInt(group, radix);
            if (groupVal < 0)
                 throw new NumberFormatException("Illegal digit");
            destructiveMulAdd(mag, superRadix, groupVal);
         }
        
        mag = removeZeroInts(mag);
    }
	
	private final static long LONG_MASK = 0xffffffffL;
	
	public static final BigInteger ZERO = new BigInteger(new int[0], 0);
	
	public static final BigInteger ONE = valueOf(1);
	
	private static final BigInteger TWO = valueOf(2);
	
	public static final BigInteger TEN = valueOf(10);
	
	// bitsPerDigit in the given radix times 1024
	// Rounded up to avoid underallocation.
    private static long bitsPerDigit[] = { 0, 0,1024, 1624, 2048, 2378, 2648, 2875, 3072, 3247, 3402, 3543, 3672, 3790, 3899, 4001, 4096, 4186, 4271, 4350, 4426, 4498, 4567, 4633, 4696, 4756, 4814, 4870, 4923, 4975, 5025, 5074, 5120, 5166, 5210, 5253, 5295};
		
	//used for string conversion in constructor
    private static int digitsPerInt[] = {0, 0, 30, 19, 15, 13, 11, 11, 10, 9, 9, 8, 8, 8, 8, 7, 7, 7, 7, 7, 7, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 5};

    private static int intRadix[] = {0, 0, 0x40000000, 0x4546b3db, 0x40000000, 0x48c27395, 0x159fd800, 0x75db9c97, 0x40000000, 0x17179149, 0x3b9aca00, 0xcc6db61, 0x19a10000, 0x309f1021, 0x57f6c100, 0xa2f1b6f,  0x10000000, 0x18754571, 0x247dbc80, 0x3547667b, 0x4c4b4000, 0x6b5a6e1d, 0x6c20a40,  0x8d2d931,  0xb640000,  0xe8d4a51,  0x1269ae40,0x17179149, 0x1cb91000, 0x23744899, 0x2b73a840, 0x34e63b41,0x40000000, 0x4cfa3cc1, 0x5c13d840, 0x6d91b519, 0x39aa400
    };
		

	private static int[] removeZeroBytes(byte a[]) {
        int byteLength = a.length;
        int keep;

        
        for (keep=0; keep<a.length && a[keep]==0; keep++){
			//do nothing but increase keep counter until first nonzero byte
			}
        //initiate array and copy nonzeros into the new array
        int intLength = ((byteLength - keep) + 3)/4;
        int[] answer = new int[intLength];
        int b = byteLength - 1;
        for (int i = intLength-1; i >= 0; i--) {
            answer[i] = a[b--] & 0xff;
            int bytesRemaining = b - keep + 1;
            int bytesToTransfer = Math.min(3, bytesRemaining);
            for (int j=8; j <= 8*bytesToTransfer; j += 8){
                answer[i] |= ((a[b--] & 0xff) << j);
			}
        }
        return answer;
    }
	
	private static int[] removeZeroInts(int val[]) {
        int byteLength = val.length;
        int keep;

    
        for (keep=0; keep<a.length && a[keep]==0; keep++){
			//do nothing but increase keep counter until first nonzero byte
			}
        // Only perform copy if necessary
        if (keep > 0) {
            int result[] = new int[val.length - keep];
            for(int i=0; i<val.length - keep; i++)
               result[i] = val[keep+i];
            return result;
        }
        return val;
    }
	
	// Multiply x array times word y in place, and add word z
    private static void destructiveMulAdd(int[] x, int y, int z) {
        // Perform the multiplication word by word
        long ylong = y & LONG_MASK;
        long zlong = z & LONG_MASK;
        int len = x.length;

        long product = 0;
        long carry = 0;
        for (int i = len-1; i >= 0; i--) {
            product = ylong * (x[i] & LONG_MASK) + carry;
            x[i] = (int)product;
            carry = product >>> 32;
        }

         // Perform the addition
         long sum = (x[len-1] & LONG_MASK) + zlong;
         x[len-1] = (int)sum;
         carry = sum >>> 32;
        for (int i = len-2; i >= 0; i--) {
             sum = (x[i] & LONG_MASK) + carry;
             x[i] = (int)sum;
            carry = sum >>> 32;
        }
     }
	
	
	
	
}

