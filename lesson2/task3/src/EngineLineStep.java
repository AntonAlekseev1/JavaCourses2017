
public class EngineLineStep implements ILineStep{

	@Override
	public IProductPart buildProductPart() {
		// TODO Auto-generated method stub
		Engine engine=new Engine("in-line",85.6);
		return engine;
	}
	

}
