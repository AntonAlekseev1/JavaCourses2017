
public class Body implements IProductPart {
	public String vin;
	public String bodyType;

	public Body() {
		System.out.println("Body createed.");
	}

	public Body(String vin, String bodyType) {
		this.vin = vin;
		this.bodyType = bodyType;
		System.out.println("Body createed. " + getVin() + " Type - " + bodyType());
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String bodyType() {
		return bodyType;
	}

	public void setbodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public void show() {
		System.out.println(getVin() + " " + bodyType());
	}
}
