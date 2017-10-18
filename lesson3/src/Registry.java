
public class Registry  implements IRegistry {
	private int countDoctor;
	private int countPatient=0;
	private Doctor[] doctorList={new Doctor("Sergey","Sawbones"),new Doctor("Boris","internist"),null,null,null,null,null,null};
	public Patient[] patientList={new Patient("Dasha","illnes#1"),new Patient("Dima","illnes#2"),null,null,null,null,null,null};
	 
	 public Patient getPatient(int i) {
		 return patientList[i];
	 }
	 public Doctor getDoctor(int id) {
			return doctorList[id];
		}

	public void addDoctor(int id, String name,String specialization) {
		doctorList[id]=new Doctor(name, specialization);
	
	}
	public void showAllDoctors() {

		for(int i=0;i<doctorList.length;i++) {
			if(doctorList[i]!=null)
				countDoctor=countDoctor+1;
		}
		System.out.println("amount doctors = "+countDoctor);
		
		for(int i=0;i<doctorList.length;i++) {
			if(doctorList[i]!=null)
			System.out.println("Doctor: "+doctorList[i].getName()+" Specialization "+doctorList[i].getSpecialization());
			else
				System.out.println("vacancy is open");
		}
		System.out.println();
		}
	public void addPatient(int id,String name,String illness) {

		patientList[id]=new Patient(name,illness);
}
	public void showAllPatients() {

		for(int i=0;i<patientList.length;i++) {
			if(patientList[i]!=null)
				countPatient=countPatient+1;
		}
		System.out.println("amount patients = "+countPatient);
		
		for(int i=0;i<patientList.length;i++) {
			if(patientList[i]!=null)
			System.out.println(patientList[i].getName()+" "+patientList[i].getllness());
		}
		System.out.println();
	}
	public void addPatientToDoctor(int id,int patientId) {
		getDoctor(id).addPatient(patientId);
	//	System.out.println("Doctor: "+doctorList[id].getName()+" Specialization "+doctorList[id].getSpecialization());
	//	System.out.println(patientList[patientId].getName()+" "+patientList[patientId].getllness());
	}
	
	public void removePatient(int id) {
		patientList[id]=null;
	}
	public void showPatientOfDoctor(int id) {
		System.out.println("Doctor: "+doctorList[id].getName()+" Specialization "+doctorList[id].getSpecialization());
		doctorList[id].showAllPatientsOfDoctor();;
	/*	for(int i=0;i<patientList.length;i++) {
			if(patientList[i]!=null)
		System.out.println(patientList[i].getName()+" "+patientList[i].getllness());
		}*/
	}
}