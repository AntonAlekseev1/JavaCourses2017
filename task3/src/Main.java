
public class Main {

	public static void main(String[] args) {
		AssemblyLine assemblyLine = new AssemblyLine(new CarChassisLineStep(), new BodyLineStep(),
				new EngineLineStep());
		Product product = new Product();

		product = (Product) assemblyLine.assembleProduct(product);

	}

}
