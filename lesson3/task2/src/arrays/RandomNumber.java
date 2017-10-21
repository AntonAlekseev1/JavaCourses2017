package arrays;

import java.util.Random;

public class RandomNumber {

	private int max;
	private int number;
	private int array[] = new int[3];

	public int generateRandomNum() {
		Random random = new Random();
		number = random.nextInt(899) + 100;
		return number;
	}

	public int[] writeInArray() {
		for (int i = 0; i < array.length; i++) {
			array[i] = number % 10;
			number /= 10;
		}
		return array;
	}

	public int getMaxValue() {
		max = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max)
				max = array[i];
		}
		return max;
	}

	public void print(String b, int a) {
		System.out.println(b + a);

	}

	public void printArray(int a[]) {
		for (int i = a.length - 1; i >= 0; i--) {
			System.out.print(a[i] + "; ");
		}
		System.out.println();
	}
}