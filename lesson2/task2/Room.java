public class Room{

private int number;
    public Room(){
        System.out.println("Room");
}
public Room(int number){
    this.number=number;
    System.out.println("Room");
}
public int getNumber(){
    return number;
}
public void setNumber(int number){
    this.number=number;
   }
}