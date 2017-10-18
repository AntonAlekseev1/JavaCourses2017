package arrays;

import java.util.Random;

public class Array2 {

	private int max;
	private int number;
	private int a[] = new int[3];

	public int GenerateRandomNum() {
		Random random = new Random();
		number = random.nextInt(899) + 100;
		return number;
	}

	public int [] writeInArray() {
		for (int i = 0; i < a.length; i++) {
			a[i] = number % 10;
			number /= 10;
		}
		return a;
	}

	public int getMaxValue() {
		max = a[0];
		for (int i = 0; i < a.length; i++) {
			if (a[i] > max)
				max = a[i];
		}
		return max;
	}

	public void print(String b, int a) {
		System.out.println(b + a);

	}
	public void printArray(int a[]) {
		for (int i = a.length-1; i>=0; i--) {
			System.out.print(a[i]+"; ");
		}
		System.out.println();
	}
}