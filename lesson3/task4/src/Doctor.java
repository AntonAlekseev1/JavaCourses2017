
public class Doctor  implements IDoctor {
	private String name;
	private String specialization;
	private int countPatient=0;
	private Patient[] patient=new Patient[25];
	private Registry reg;
	public Doctor() {
		
	}

	public Doctor(String name, String specialization) {
		this.name=name;
		this.specialization=specialization;
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

	public void addPatient(int id) {
		reg=new Registry();
		patient[id]= reg.getPatient(id);
	}
	public void removePatient(int id) {
		
		patient[id]= null;
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
