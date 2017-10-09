
public class Product implements IProduct{

	@Override
	public void instalFirstPart(IProductPart first) {
		System.out.println("CarChassis is installed");
		 
	}

	@Override
	public void instalSecondPart(IProductPart second) {
		System.out.println("Body is installed");
		
	}

	@Override
	public void instalThirdPart(IProductPart third) {
		System.out.println("Engine is installed");
		
	}

}
