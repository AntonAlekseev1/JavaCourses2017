
public class Patient implements IPatient {
	private String name;
	private String illness;
	public Patient() {
		
	}
public Patient(String name, String illness) {
	this.name=name;
	this.illness=illness;
}
	@Override
	public String getName() {

		return name;
	}

	@Override
	public void setName(String name) {
		
		this.name = name;
	}

	@Override
	public String getllness() {

		return illness;
	}

	@Override
	public void setIllness(String illness) {
		
		this.illness = illness;
	}

}
