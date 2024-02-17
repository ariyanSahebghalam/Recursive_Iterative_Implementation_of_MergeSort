import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Random;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class A2Tester {
	private static Random rng = new Random();

	@Test (timeout = 2000)
	public void A2FieldsTester_5pts() {
		Field[] fields = A2.class.getDeclaredFields();
		for (Field f: fields){
			assertTrue("A2 contains a public field", 
					!Modifier.isPublic(f.getModifiers()));
		}
	}

	@SuppressWarnings("rawtypes")
	@Test (timeout = 2000)
	public void A2PrivateConstructorTester_5pts() {
		Constructor[] constructors = A2.class.getDeclaredConstructors();
		for (Constructor f: constructors){
			assertTrue("A2 contains a public constructor", 
					!Modifier.isPublic(f.getModifiers()));
		}
	}

	@Test (timeout = 2000)
	public void A2NumOfPublicMethodsTester_5pts() {
		Method[] methods = A2.class.getDeclaredMethods();
		int publicMethodCounter = 0;
		for (Method m: methods){
			if(Modifier.isPublic(m.getModifiers()))
				publicMethodCounter++;
		}
		assertTrue("Class contains a wrong number of public methods", publicMethodCounter == 2);
	}

	@Test (timeout = 2000)
	public void sortingMergeSortIterTest_20pts() {
		int n = 87;
		for (int trial = 0; trial < 100; trial++) {
			int[] array = createRandomIntArray(n);
			int[] sortedArray = createRandomSortedIntArray(n);
			//System.out.println("Before: " + Arrays.toString(array));
			A2.mergeSortIterative(array);
			//System.out.println("After:  " + Arrays.toString(array));
			assertTrue("Not sorted!", isSorted(array));
			assertTrue("Not sorted!", Arrays.equals(array, sortedArray));
		}
	}

	@Test (timeout = 2000)
	public void sortingMergeSortRecTest_20pts() {
		int n = 87;
		for (int trial = 0; trial < 100; trial++) {
			int[] array = createRandomIntArray(n);
			int[] sortedArray = createRandomSortedIntArray(n);
			A2.mergeSortRecursive(array);
			assertTrue("Not sorted!", isSorted(array));
			assertTrue("Not sorted!", Arrays.equals(array, sortedArray));
		}
	}

	@Test (timeout = 20000)
	public void sortingSpeedMSI_10pts() {
		long timeQS, timeMSI;
		
		int[] array = createRandomIntArray(1000000);
		long begin = System.currentTimeMillis();
		Arrays.sort(array);
		timeQS = System.currentTimeMillis() - begin;
		System.out.print("Arrays.Qsort\t" + timeQS + " ms\n");

		array = createRandomIntArray(1000000);
		begin = System.currentTimeMillis();
		A2.mergeSortIterative(array);
		timeMSI = System.currentTimeMillis() - begin;
		System.out.print("merge sort (iter.)\t" + timeMSI + " ms\n");
		assertTrue("merge sort (iter.) > 6x slower than quicksort", timeMSI < 6 * timeQS);

	}

	@Test (timeout = 20000)
	public void sortingSpeedMSR_10pts() {
		long timeQS, timeMSR;
		
		int[] array = createRandomIntArray(1000000);
		long begin = System.currentTimeMillis();
		Arrays.sort(array);
		timeQS = System.currentTimeMillis() - begin;
		System.out.print("Arrays.Qsort\t" + timeQS + " ms\n");

		array = createRandomIntArray(1000000);
		begin = System.currentTimeMillis();
		A2.mergeSortRecursive(array);
		timeMSR = System.currentTimeMillis() - begin;
		System.out.print("merge sort (rec.)\t" + timeMSR + " ms\n");
		assertTrue("merge sort (rec.) > 6x slower than quicksort", timeMSR < 6 * timeQS);
	}

	@Test (timeout = 30000)
	public void visualCheckOfPart2_0to25pts() {
		Part2.main(null);
	}

	private static int [] createRandomIntArray (int size){
		rng.setSeed(8); //each time, the random sequence will be the same
		int [] array = new int [size];

		for (int i = 0; i < size; i++) array[i] = rng.nextInt();

		return array;
	}

	private static int [] createRandomSortedIntArray (int size){
		rng.setSeed(8); //each time, the random sequence will be the same
		int [] array = new int [size];

		for (int i = 0; i < size; i++) array[i] = rng.nextInt();

		Arrays.sort(array);
		return array;
	}

	private static boolean isSorted(int [] array){
		if (array.length <= 1) return true;
		for (int i = 1; i < array.length; i++){
			if (array[i] < array [i-1]) return false;
		}
		return true;
	}

}