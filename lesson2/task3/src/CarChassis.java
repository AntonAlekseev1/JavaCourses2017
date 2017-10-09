
public class CarChassis implements IProductPart {
	public double length;
	public int numberOfWheels;
	public CarChassis(double length,int numberOfWheels) {
		this.length=length;
		this.numberOfWheels=numberOfWheels;
		System.out.println("Car chassis manufacturing.");
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length=length;
	}
	public int getNumberOfWheels() {
		return numberOfWheels;
	}
	public void setNumberOfWheels(byte numberOfWheels) {
		this.numberOfWheels=numberOfWheels;
	}
}
