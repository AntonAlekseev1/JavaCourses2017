package arrays;

public class Bouquet {
	private double totalPrice;
	private AFlower bouquet[];
	private int i = 0;

	public Bouquet(int flowerCount) {
		bouquet = new AFlower[flowerCount];
	}

	public void addFlower(AFlower flower) {
		try {
			bouquet[i] = flower;
			i++;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Exception! Not all flowers are counted");
			System.exit(0);
		}
	}

	public double totalPrice() {
		totalPrice = 0;
		try {
			for (AFlower flower : bouquet) {
				totalPrice += flower.getPrice();
			}
		} catch (NullPointerException e) {
			System.out.println("Exception! Add any more flowers");
			System.exit(0);
		}
		return totalPrice;
	}

	public void print() {
		System.out.println("the bouquet consists from: ");
		try {
			for (AFlower flower : bouquet) {
				System.out.println(flower.getName() + " Price " + flower.getPrice());
			}
		} catch (NullPointerException e) {
			System.out.println("Exception! Add any more flowers");
			System.exit(0);
		}
	}
}