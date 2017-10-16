import java.util.ArrayList;

public class Department{

    private String name;
    private ArrayList<Employee>  employees;

public Department(){
    this.employees=new ArrayList<Employee>(); 
    System.out.println("Departnent ");
}
public Department(String name,ArrayList<Employee> employees){
    this.name=name;
    this.employees=employees;
    System.out.println("Departnent ");
}
public String getName(){
    return name;
}
public void setName(String name){
    this.name=name;
}
public void addEmployee( Employee newEmployee){
    employees.add(newEmployee);
}
public void setEmployees(ArrayList<Employee> employees ) {
    this.employees=employees;
}
public ArrayList<Employee> getEmployees(){
    return employees;
}
public void removeEmployee(Employee employe){
    employees.remove(employe);
}
public void showDepartment() {
    for (Employee employee:employees) {
       employee.show();	
     }
   }
}