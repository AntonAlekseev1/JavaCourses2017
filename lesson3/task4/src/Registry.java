
public class Registry {
	private int countDoctor;
	private int countPatient = 0;
	private Doctor[] doctorList = new Doctor[15];
	private Patient[] patientList = new Patient[30];

	public Patient getPatient(int i) {
		return patientList[i];
	}

	public Doctor getDoctor(int id) {
		return doctorList[id];
	}

	public void addDoctor(Doctor doctor) {
		for (int i = 0; i < doctorList.length; i++) {
			if (doctorList[i] == null) {
				doctorList[i] = doctor;
				break;
			}
		}
	}

	public int countDoctors() {
		for (int i = 0; i < doctorList.length; i++) {
			if (doctorList[i] != null)
				countDoctor = countDoctor + 1;
		}

		return countDoctor;
	}

	public void showAllDoctors() {

		for (int i = 0; i < doctorList.length; i++) {
			if (doctorList[i] != null)
				System.out.println("Doctor: " + doctorList[i].getName());

		}
		System.out.println();
	}

	public void addPatient(Patient patient) {
		for (int i = 0; i < patientList.length; i++) {
			if (patientList[i] == null) {
				patientList[i] = patient;
				break;
			}
		}
	}

	public int countPatients() {
		for (int i = 0; i < patientList.length; i++) {
			if (patientList[i] != null)
				countPatient = countPatient + 1;
		}

		return countPatient;
	}

	public void showAllPatients() {

		for (int i = 0; i < patientList.length; i++) {
			if (patientList[i] != null)
				System.out.println("Patient: "+patientList[i].getName());
		}
		System.out.println();
	}

	public void addPatientToDoctor(Doctor doctor, Patient patient) {

		doctor.addPatient(patient);
	}

	public void removePatientFromDoctor(Doctor doctor, Patient patient) {

		doctor.removePatient(patient);
	}

	public void removePatient(Patient patient) {

		for (int j = 0; j < patientList.length; j++) {
			if (patientList[j] == patient) {
				patientList[j] = null;
			}
		}
		for (int i = 0; i < doctorList.length; i++) {

			if (doctorList[i] != null)
				removePatientFromDoctor(doctorList[i], patient);
		}
	}

	public void showPatientOfDoctor(int id) {
		System.out.println("Doctor: " + getDoctor(id).getName());
		getDoctor(id).showAllPatientsOfDoctor();
	}

	public int countPatientsOfDoctor(int id) {

		return getDoctor(id).countAllPatientsOfDoctor();
	}
}