
public class Main {

	public static void main(String[] args) {
		Registry reg = new Registry();

		Patient patient1 = new Patient("Yura");
		Patient patient2 = new Patient("Dima");
		Patient patient3 = new Patient("Dasha");
		Patient patient4 = new Patient("Vasia");
		Patient patient5 = new Patient("Jon");

		Doctor doctor1 = new Doctor("Sergey");
		Doctor doctor2 = new Doctor("Boris");
		Doctor doctor3 = new Doctor("Herbert");

		reg.addDoctor(doctor1);
		reg.addDoctor(doctor2);
		reg.addDoctor(doctor3);

		reg.addPatient(patient1);
		reg.addPatient(patient2);
		reg.addPatient(patient3);
		reg.addPatient(patient4);
		reg.addPatient(patient5);

		reg.addPatientToDoctor(doctor1, patient1);
		reg.addPatientToDoctor(doctor1, patient2);
		reg.addPatientToDoctor(doctor1, patient3);
		reg.addPatientToDoctor(doctor2, patient4);
		reg.addPatientToDoctor(doctor2, patient5);

		reg.removePatientFromDoctor(doctor1, patient2);

		reg.removePatient(patient1);

		StringBuilder strBuilder = new StringBuilder();
		strBuilder.append("amount doctors = ");
		strBuilder.append(reg.countDoctors());
		strBuilder.append("\n");
		strBuilder.append("amount patients = ");
		strBuilder.append(reg.countPatients());
		Printer.println(strBuilder.toString());
		Printer.println("Doctors: \n");
		reg.showAllDoctors();
		Printer.println("Patients: \n");
		reg.showAllPatients();
		System.out.println("recorded patients = " + reg.countPatientsOfDoctor(0));
		reg.showPatientOfDoctor(0);
		System.out.println("recorded patients = " + reg.countPatientsOfDoctor(1));
		reg.showPatientOfDoctor(1);
	}

}
