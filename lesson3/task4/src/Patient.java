
public class Patient implements IPatient {
	private String name;

	public Patient() {

	}

	public Patient(String name) {
		this.name = name;
	}

	@Override
	public String getName() {

		return name;
	}

	@Override
	public void setName(String name) {

		this.name = name;
	}

}
