
public class Main {

	public static void main(String[] args) {
		Registry reg=new Registry();
		Doctor doc=new Doctor();
		reg.addDoctor(2,"Zina","oculist");
		reg.addDoctor(3,"Aleks","psychologist");
		reg.addDoctor(5,"Elena","psychologist");
		
		reg.addPatient(2, "Yura", "illness#2");
		reg.addPatient(4, "Vasia", "illness#4");
		
		reg.addPatientToDoctor(1,0);
		reg.addPatientToDoctor(1,1);
		reg.addPatientToDoctor(1, 2);
	//	reg.getDoctor(1).addPatient(1);
	//	doc.addPatient(2);
		doc.showPatientOfDoctor(1);
	//	reg.removePatient(1);
		
	//	reg.showAllDoctors();;
	//	reg.showAllPatients();
	//	reg.showPatientOfDoctor(1);
	}

}
