
public class MainClass {

	public static void main(String[] args) {
		Registry reg=new Registry();
	
		reg.addDoctor(2,"Zina","oculist");
		reg.addDoctor(3,"Aleks","psychologist");
		reg.addDoctor(5,"Elena","psychologist");
		
		reg.addPatient(2, "Yura", "illness#2");
		reg.addPatient(4, "Vasia", "illness#4");
		
		reg.addPatientToDoctor(1,0);
		reg.addPatientToDoctor(1,1);
		reg.addPatientToDoctor(1, 2);
		reg.addPatientToDoctor(1,3);
		reg.addPatientToDoctor(1, 4);
	
		reg.addPatientToDoctor(5,0);
		reg.addPatientToDoctor(0,1);
		reg.addPatientToDoctor(5, 2);
		reg.addPatientToDoctor(5, 4);
		
		reg.removePatient(1);
		reg.removePatientFromDoctor(1, 0);
		
		reg.showAllDoctors();;
		reg.showAllPatients();
		reg.showPatientOfDoctor(1);
		reg.showPatientOfDoctor(5);
	}

}
