
public class Doctor extends Arhiv implements IDoctor {
	private String name;
	private String specialization;
	private int countPatient=0;
	private Patient[] patient=new Patient[25];
	public Doctor() {
		
	}
/*	Patient getPatient(int id) {
		return patient[id];
	}*/
	public Doctor(String name, String specialization) {
		this.name=name;
		this.specialization=specialization;
	}
	public void showPatientOfDoctor(int id) {
	//	System.out.println("Doctor: "+doctorList[id].getName()+" Specialization "+doctorList[id].getSpecialization());
	//	doctorList[id].amountPatient();
		for(int i=0;i<patient.length;i++) {
		//	if(patient[i]!=null)
		System.out.println(patient[i].getName());
		}
		}
	@Override
	public String getName() {
		
		return name;
	}
	@Override
	public void setName(String name) {
		this.name=name;
		
	}
	@Override
	public String getSpecialization() {
		
		return specialization;
	}
	@Override
	public void setSpecialization(String specialization) {
		this.specialization=specialization;
		
	}

	public Patient addPatient(int id) {
		patient[id]=new Patient();

		patient[id]=getPatient(id);
		return patient[id];
	}

public void showAllPatientsOfDoctor() {

	for(int i=0;i<patient.length;i++) {
		if(patient[i]!=null)
			countPatient=countPatient+1;
	}
	System.out.println("recorded patients = "+countPatient);
	
	for(int i=0;i<patient.length;i++) {
		if(patient[i]!=null)	
		System.out.println(patient[i].getName()+" "+patient[i].getllness());
	}
}
}
