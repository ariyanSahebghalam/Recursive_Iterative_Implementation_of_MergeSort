
/**
 * The class that implements the methods mergeSortRecursive for the recursive implementation
 * of the mergesort algorithm and mergeSortIterative for the iterative implementation of the
 * mergesort algorithm.
 *
 * @author Ariyan Sahebghalam, Eyosyas Andarge, Sadiq Azmi
 */
public class A2 {

	// Preventing object creation
	private A2() {};


	/**
	 * Sorts an array via merge sort but in a recursive fashion
	 *
	 * @param a An array to sort with mergesort using recursion
	 *
	 * Learned the logic of how to write mergeSortRecursive through this Youtube video from a channel called
	 * Abdul Bari https://www.youtube.com/watch?v=_SEmX51UHEA
	 */
	public static void mergeSortRecursive(int[] a) {

		int length = a.length;

		// BASE CASE
		if (length <= 1) {
			return;
		}

		// Middle point of the array
		int mid = length / 2;

		int[] left_array = new int[mid]; // In odd arrays, the left array is smaller
		int[] right_array = new int[length - mid]; // In odd arrays, the right array is bigger


		System.arraycopy(a, 0, left_array, 0, mid);
		System.arraycopy(a, mid, right_array, 0, length - mid);

		mergeSortRecursive(left_array);
		mergeSortRecursive(right_array);
		merge(left_array, right_array, a);  // Compares and sorts the individual elements from the left and array and right to the original
	}

	/**
	 * Helper function used to merge the two arrays passed for mergesort recursion
	 *
	 * @param left_array left half of an array, about to be merged
	 * @param right_array right half of an  array, about to be merged
	 * @param original The array that the merged values are going inside
	 */
	private static void merge(int[] left_array, int[] right_array, int[] original) {

		int left_size = original.length / 2;
		int right_size = original.length - left_size;

		int i = 0;
		int left = 0;
		int right = 0;

		// Comparing the two newly created arrays and inserting back the values into the original array
		while(left < left_size && right < right_size) {
			if (left_array[left] < right_array[right]) {
				original[i] = left_array[left];
				i++;
				left++;
			}
			else {
				original[i] = right_array[right];
				i++;
				right++;
			}
		}

		// In case there are elements remaining in the left array that are not compared
		while (left < left_size) {
			original[i] = left_array[left];
			i++;
			left++;
		}

		// In case there are elements remaining in the right array that are not compared
		while (right < right_size) {
			original[i] = right_array[right];
			i++;
			right++;
		}
	}



	/**
	 * Sorts an array in an iterative mergesort fashion as opposed to recursive
	 * using a way called bottom-up mergesort.
	 *
	 * @param a An array to sort using mergesort iterative
	 *
	 * Uses and inspired by the outer and inner loop in the sort method
	 * in MergeBU.java from the link https://algs4.cs.princeton.edu/20sorting/
	 */
	public static void mergeSortIterative(int[] a) {

		int low;
		int high;
		int mid;

		int n = a.length;
		int[] auxiliary = new int[n];


		// Outer loop determines the length of each of the sublists
		for (int len = 1; len < n; len *= 2) {
			// Low, mid, and are the starting, middle, and ending indices of each of the sublists
			for (low = 0; low < n - len; low += len + len) {
				mid = low + len - 1;
				high = Math.min(low + 2 * len - 1, n - 1);
				merge(a, auxiliary, low, mid, high);        // Where the merging process happens
			}
		}
	}




	/**
	 * Helper function used to merge two arrays for the iterative mergesort
	 *
	 * @param original The original array that is supposed to have its content copied into the auxiliary array
	 * @param auxiliary An auxiliary array used to help with the process of merging in iterative mergesort, a copy of the original array.
	 * @param low The starting index from which we must copy
	 * @param mid The middle index of the supposed array
	 * @param high The ending index that we must stop copying
	 *
	 */
	private static void merge(int[] original, int[] auxiliary, int low, int mid, int high) {

		// Copying the original array into the auxiliary array
		for (int i = low; i <= high; i++) {
			auxiliary[i] = original[i];
		}

		// starting index of the first sublist
		int i = low;
		// starting index of the second sublist
		int j = mid + 1;

		// merging back the elements into the original array from the auxiliary array
		for (int k = low; k <= high; k++) {
			if (i > mid) {
				original[k] = auxiliary[j];  // In case the second sublist was bigger, filling out the remaining values
				j++;
			}
			else if (j > high) {
				original[k] = auxiliary[i];   // In case the first sublist was bigger, filling out the remaining values
				i++;
			}
			else if (auxiliary[j] < auxiliary[i]) {
				original[k] = auxiliary[j];   // If the second sublist element was smaller
				j++;
			}
			else {
				original[k] = auxiliary[i];
				i++;
			}
		}

	}
}



