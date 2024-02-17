import java.util.Random;
import java.util.Arrays;

/**
 * Tester class used to investigate how the running time complexity of the
 * sorting methods, iterative and recursive mergesort, implemented compares with that of quicksort which is the default
 * java sorting algorithm for arrays of primitive types.
 * As the number of items in the arrays increase, the time it takes to perform those sorting methods increases.
 *
 * @author Ariyan Sahebghalam, Eyosyas Andarge, Sadiq Azmi
 *
 */
public class Part2 {

	// Preventing object creation
	private Part2() {};

	/**
	 * Main method to run the test cases
	 */
	public static void main(String[] args) {

		System.out.printf("%29s %18s %13s", "mergeSortIter","mergeSortRecur", "sort(int)");
		System.out.println();

		// loop for values of n=1 to 10m
		for (int n = 10; n <= 10_000_000; n*=10) {
			System.out.print("n = ");
			System.out.printf("%-,10d" + "\t", n);
			int[] testArray = new int[n];

			Random rand = new Random();

			// Assigning random numbers to the variable
			for (int j = 0; j < n; j++) {
				testArray[j] = rand.nextInt() % 100;
			}

			// Creating identical copies of the array
			int[] testArray2 = Arrays.copyOf(testArray, n);
			int[] testArray3 = Arrays.copyOf(testArray, n);


			// Time it takes to perform merge sort iterative
			long start_time = System.currentTimeMillis();
			A2.mergeSortIterative(testArray);
			long end_time = System.currentTimeMillis();

			// Time it takes to perform Merge sort recursive
			long start_time2 = System.currentTimeMillis();
			A2.mergeSortRecursive(testArray2);
			long end_time2 = System.currentTimeMillis();

			// Time it takes to perform quickSort
			long start_time3 = System.currentTimeMillis();
			Arrays.sort(testArray3);
			long end_time3 = System.currentTimeMillis();

			// display times
			System.out.printf("%dms %15dms %16dms\n", (end_time - start_time), (end_time2 - start_time2), (end_time3 - start_time3));

			System.out.println();

		}

	}

}

