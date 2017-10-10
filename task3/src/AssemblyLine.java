
public class AssemblyLine implements IAssemblyLine {
	ILineStep carChassisLineStep, bodyLineStep, engineLineStep;

	public AssemblyLine(CarChassisLineStep carChassisLineStep, BodyLineStep bodyLineStep,
			EngineLineStep engineLineStep) {
		this.bodyLineStep = bodyLineStep;
		this.carChassisLineStep = carChassisLineStep;
		this.engineLineStep = engineLineStep;
	}

	@Override
	public IProduct assembleProduct(IProduct product) {

		System.out.println("Start create product");
		product.instalFirstPart(carChassisLineStep.buildProductPart());
		product.instalSecondPart(bodyLineStep.buildProductPart());
		product.instalThirdPart(engineLineStep.buildProductPart());
		System.out.println("Car is created!");
		return product;
	}

}
