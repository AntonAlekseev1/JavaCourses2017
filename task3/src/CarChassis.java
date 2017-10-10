
public class CarChassis implements IProductPart {
	public double length;
	public int numberOfWheels;

	public CarChassis() {
		System.out.println("Car chassis created ");
	}

	public CarChassis(double length, int numberOfWheels) {
		this.length = length;
		this.numberOfWheels = numberOfWheels;
		System.out.println("Car chassis created " + getLength() + " m " + getNumberOfWheels() + " wheels");
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public int getNumberOfWheels() {
		return numberOfWheels;
	}

	public void setNumberOfWheels(byte numberOfWheels) {
		this.numberOfWheels = numberOfWheels;
	}

	public void show() {
		System.out.println(getLength() + " m " + getNumberOfWheels() + " weels");
	}
}
