
public class AssemblyLine implements IAssemblyLine {

	@Override
	public IProduct assembleProduct(IProduct product) {
		// TODO Auto-generated method stub
		System.out.println("Start create product");
		CarChassis carChassis = (CarChassis) new CarChassisLineStep().buildProductPart();
        Body body = (Body) new BodyLineStep().buildProductPart();
        Engine engine = (Engine) new EngineLineStep().buildProductPart();
        product.instalFirstPart(carChassis);
        product.instalSecondPart(body);
        product.instalThirdPart(engine);

        System.out.println("Car is created!");
		return product;
	}
	 public AssemblyLine(){
	        assembleProduct(new Product());
	    }
	public static void main(String[] args) {
        new AssemblyLine();
    }
}
