/*

Least Number after Deleting Digits
Problem: Please get the least number after deleting k digits from the input number. For example, if the input number is 24635, the least number is 23 after deleting 3 digits.

Analysis: Let’s delete a digit from the number at each step. What’s the first digit to be deleted from the number 24635, in order to get the least number with the remaining digits? We may list all the remaining numbers after deleting a digit, in the following table:

Deleted Digit		Remaining Number
2					4635
4					2635
6					2435
3					2465
5					2463

The number 2435 is the least one in all remaining numbers, by deleting the digit 6. Notice that the digit 6 is the first digit in the number 24635 which is greater than the next digit.

Let’s delete another digit from the number 2435, the remaining least number after the first step. We may summarize the remaining numbers after delete every digit from it in the following table:

Deleted Digit		Remaining Number
2					435
4					235
3					245
5					243

The number 235 is the least one in all remaining numbers, by deleting the digit 4. Notice that the digit 4 is the first digit in the number 2435 which is greater than the next digit.

The remaining three digits in the number 235 are increasingly sorted. What is the next digit to be deleted to get the least remaining number? Again, we may list the remaining numbers after deleting each digit in a table:

Deleted Digit			Remaining Number
2						35
3						25
5						23

The number 23 is the least one in all remaining numbers, by deleting the last digit 5.

If we are going to deleting more digits from a number whose digits are increasingly sorted to get the least number, the last digit is deleted at each step.

Now we get the rules to delete digits to get the least remaining number: If there are digits who are greater than the next one, delete the first digit. If all digits in the number are increasingly sorted, delete the last digit gets deleted. The process repeats until the required k digits are deleted.

The code can be implemented in Java as the following:
 */


public class GetLowestNumberRemovingK {
	//Starting O(n*K) Algorithm
	public static String getLeastNumberDeletingDigits_1(String number, int k) {
	    String leastNumber = number;
	    while(k > 0 && leastNumber.length() > 0) {
	        int firstDecreasingDigit = getFirstDecreasing(leastNumber);
	        if(firstDecreasingDigit >= 0) {
	            leastNumber = removeDigit(leastNumber, firstDecreasingDigit);
	        }
	        else {
	            leastNumber = removeDigit(leastNumber, leastNumber.length() - 1);
	        }
	        --k;
	    }

	    return leastNumber;
	}

	private static int getFirstDecreasing(String number) {
	    for(int i = 0; i < number.length() - 1; ++i) {
	        int curDigit = number.charAt(i) - '0';
	        int nextDigit = number.charAt(i + 1) - '0';
	        if(curDigit > nextDigit) {
	            return i;
	        }
	    }

	    return -1;
	}
	
	private static String removeDigit(String number, int digitIndex) {
	    String result = "";
	    if(digitIndex > 0) {
	        result = number.substring(0, digitIndex);
	    }
	    if(digitIndex < number.length() - 1) {
	        result += number.substring(digitIndex + 1);
	    }

	    return result;
	}
	//End of O(n*K) complexity method
	

	public static void main(String args[]){
		System.out.println(getLeastNumberDeletingDigits_1("4205123", 4));

		System.out.println(getLeastNumberDeletingDigits_1("24635", 2));
	}
	
}
