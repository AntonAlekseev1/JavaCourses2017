package arrays;

public class Rose extends AFlower {
	private String color;

	public Rose(String color) {
		super("rose", 15);
		this.color = color;

	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}
