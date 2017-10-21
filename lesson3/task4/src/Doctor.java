
public class Doctor implements IDoctor {
	private String name;
	private int countPatient = 0;
	private Patient[] patients = new Patient[25];

	public Doctor() {

	}

	public Doctor(String name) {
		this.name = name;

	}

	public Patient[] getPatient() {
		return patients;
	}

	@Override
	public String getName() {

		return name;
	}

	public void addPatient(Patient patient) {

		for (int i = 0; i < patients.length; i++) {
			if (patients[i] == null) {
				patients[i] = patient;
				break;
			}
		}
	}

	public void removePatient(Patient patient) {
		for (int i = 0; i < patients.length; i++) {
			if (patients[i] == patient) {
				patients[i] = null;
			}
		}

	}

	public int countAllPatientsOfDoctor() {
		for (int i = 0; i < patients.length; i++) {
			if (patients[i] != null)
				countPatient++;
		}

		return countPatient;
	}

	public void showAllPatientsOfDoctor() {

		for (int i = 0; i < patients.length; i++) {
			if (patients[i] != null)
				System.out.println("Patient: "+patients[i].getName());
		}
	}
}
