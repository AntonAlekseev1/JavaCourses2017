
public class Engine implements IProductPart{
private String engineType;
private double enginePower;
public Engine(String engineType,double enginePower) {
	this.engineType=engineType;
	this.enginePower=enginePower;
	System.out.println("Engine manufacturing.");
}
public String getEngineType() {
    return engineType;
}

public void setEngineType(String engineType) {
	this.engineType=engineType;
}
public Double getEnginePower() {
    return enginePower;
}

public void setEnginePower(double enginePower) {
	this.enginePower=enginePower;
}
}
