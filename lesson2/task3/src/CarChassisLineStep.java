
public class CarChassisLineStep implements ILineStep {

	@Override
	public IProductPart buildProductPart() {
		// TODO Auto-generated method stub
		CarChassis carChassis = new CarChassis(4.5, 4);
		return carChassis;
	}

}
