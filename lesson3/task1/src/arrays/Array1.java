package arrays;

public class Array1 {
	private int sum;
	private int a[];

	public Array1(int a[]) {
		this.a = a;
	}

	public void printArray() {
		for (int i = 0; i < a.length; i++) 
		System.out.print(Integer.toString(a[i])+" ");
		System.out.println();	
	}

	public String summArray() {
		sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return Integer.toString(sum);
	}
}
