package arrays;

public class Array {
	private int sum;
	private int array[];

	public Array(int array[]) {
		this.array = array;
	}

	public void printArray() {
		for (int i = 0; i < array.length; i++)
			System.out.print(Integer.toString(array[i]) + " ");
		System.out.println();
	}

	public int summArray() {
		sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		return sum;
	}
}
