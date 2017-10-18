package arrays;

public class Main3 {

	public static void main(String[] args) {
		Bouquet bouquet=new Bouquet(3);
		bouquet.addFlower(new Rose("red"));
		bouquet.addFlower(new Chamomile());
		bouquet.addFlower(new Rose("pink"));
		bouquet.print();
		System.out.println("Total price - "+bouquet.totalPrice());

	}

}
