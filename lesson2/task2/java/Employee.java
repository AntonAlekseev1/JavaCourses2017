public class Employee extends Man{
private String position;
private Arraylist<Room> rooms;
private Department department;
public Employee(){
System.out.println("Employee ")
}
public Employee(String position, String name, Arraylist<Room> rooms){
this.rooms=rooms;
this.position=position;
this.name=name;
System.out.println("Employee ");
}
public Arraylist<Room> getRoom(){
return rooms;
}
public void setRoom(Arraylist<Room> rooms){
this.rooms=rooms;
}
public Department getDepartment(){
return department;
}
public void setDepartment(Department department){
this.department=department;
}
public String getPosition(){
return position;
}
public void setPosition(String position){
this.position=position;
}
public void show(){
System.out.println("Employee: "+getName()+", "+getPosition()+", "+getDepartment);
}
}