public class Employee extends Man{

    private String position;
    private int room;
    private String department;
    public Employee(){
    System.out.println("Employee ");
}
public Employee(String position, int room){
    this.room=room;
    this.position=position;
    System.out.println("Employee ");
}
public int getRoom(){
    return room;
}
public void setRoom(int room){
    this.room=room;
}
public String getDepartment(){
    return department;
}
public void setDepartment(String department){
    this.department=department;
}
public String getPosition(){
    return position;
}
public void setPosition(String position){
    this.position=position;
}
public void show(){
    System.out.println("Employee: "+"Name- "+getName()+", Position- "+getPosition    ()+", Department- "+getDepartment()+", Room- "+getRoom());
    }
}