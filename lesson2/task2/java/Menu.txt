import java.util.ArrayList;

public class Menu{
public static void main(String[]args){
Room room=new Room();
Employee employee=new Employee("junior developer",101);
employee.setName("Vasia");
employee.setRoom(103);
employee.setDepartment("JavaTechnologies");
employee.show();
Employee employee2=new Employee("midle developer",102);
employee2.setName("Sergey");
employee2.setDepartment("JavaTechnologies");
employee2.show();
ArrayList<Employee> employees = new ArrayList<Employee>();
employees.add(employee);
employees.add(employee2);
Department department=new Department();
department.setEmployees(employees);
department.showDepartment();
}
}