
public class Product implements IProduct {
	private Body body;
	private Engine engine;
	private CarChassis carChassis;

	@Override
	public void instalFirstPart(IProductPart first) {
		this.setCarChassis((CarChassis) first);
		System.out.println("CarChassis is installed -- ");

	}

	@Override
	public void instalSecondPart(IProductPart second) {
		this.setBody((Body) second);
		System.out.println("Body is installed -- ");

	}

	@Override
	public void instalThirdPart(IProductPart third) {
		this.setEngine((Engine) third);
		System.out.println("Engine is installed -- ");

	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public CarChassis getCarChassis() {
		return carChassis;
	}

	public void setCarChassis(CarChassis carChassis) {
		this.carChassis = carChassis;
	}

}
