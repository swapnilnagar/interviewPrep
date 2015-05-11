public class CoinChangeDynamicProblem {

	public static int numberOFWaysForChange(int S[], int m, int n) {
		// table[i] will be storing the number of solutions for
		// value i. We need n+1 rows as the table is constructed
		// in bottom up manner using the base case (n = 0)
		int[] table = new int[n + 1];

		// Base case (If given value is 0)
		table[0] = 1;

		// Pick all coins one by one and update the table[] values
		// after the index greater than or equal to the value of the
		// picked coin
		for (int i = 0; i < m; i++)
			for (int j = S[i]; j <= n; j++)
				table[j] += table[j - S[i]];

		return table[n];
	}

	/*
	 * This function will calculate the minimum coind required to make change
	 * for specific value, given the array of coins.
	 * 
	 * @param denom : Array of coins given to make change
	 * 
	 * @param targetAmount : Amount for which change is needed.
	 * 
	 * This function will print the coins used to convert the amount.
	 */
	public static int minChange(int[] denom, int targetAmount) {
		// Table that holds the minimum coins required for each value
		// Table[3] will hold the minimum coin required to make change for $3
		int[] table = new int[targetAmount + 1];
		// Each index of backTrackLastCoin will hold the index of last coin in
		// denom array
		int[] backTrackLastCoin = new int[targetAmount + 1];
		int i, j;
		// initialize the table array to hold maximum value
		for (i = 0; i < table.length; ++i) {
			table[i] = Integer.MAX_VALUE;
		}
		// For value of $0 minimum coin required is 0;
		table[0] = 0;

		// Calculate minimum coins required for each amount from 1 to target
		for (i = 1; i <= targetAmount; ++i) {
			for (j = 0; j < denom.length; ++j) {
				// Pick a coin only is it is less than or equal to the amount
				// (i.e. i)
				// Also check condition to make sure no better solution have
				// been found
				if (denom[j] <= i && (table[i - denom[j]] + 1 < table[i])) {
					// Use the current coin, but if you are using a current
					// coint than
					// best solution fro $i should be amount stored in table
					// after using
					// the current i.e. table[i-denon[j]]
					table[i] = table[i - denom[j]] + 1;
					// Backtrack array that hold the index of best coin used.
					backTrackLastCoin[i] = j;
				}
			}
			System.out.print(backTrackLastCoin[i] + " ");
		}

		System.out.println("\nPrinting Coin Change ");
		for (i = targetAmount; i > 0;) {
			System.out.print(denom[backTrackLastCoin[i]] + " ");
			i -= denom[backTrackLastCoin[i]];
		}
		
		return table[targetAmount];
	}

	// Driver program to test above function
	public static void main(String a[]) {
		int arr[] = { 1, 2, 3, 6 };

		System.out.println(numberOFWaysForChange(arr, arr.length, 11));

		System.out.println("\nMinimum Coins Required :" + minChange(arr, 11));
	}

}
