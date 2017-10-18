package arrays;

public class Main2 {
	public static void main(String[] args) {

		Array2 array = new Array2();
		System.out.println(array.GenerateRandomNum());
		array.printArray(array.writeInArray());
		array.print("the largest element of the number = ", array.getMaxValue());

	}
}