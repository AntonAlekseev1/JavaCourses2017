package arrays;

public class Main {

	public static void main(String[] args) {
		Array1 array = new Array1(new int[] { 1, 2, 3, 4, 5 });
		array.printArray();
		System.out.println("sum of all elements of the array = ".concat(array.summArray()));

	}

}
