public class Menu{
public static void main(String[]args){
Employee employee=new Employe("developer","Dima",employess);
employee.setDepartment("JavaTechnologies");
System.out.println(employee.show());
Employee employee2=new Employe("tester","Vania",employess);
employee2.setDepartment("TestsDepartment");
System.out.println(employee2.show());
ArrayList<Employee> employees = new ArrayList<Employee>();
        employees.add(employee);
        employees.add(employee2);
}
}