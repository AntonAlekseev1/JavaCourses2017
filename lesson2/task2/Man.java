public class Man{

    private String name;
public Man() {
    System.out.print("Man--");	
}
public Man(String name){
    this.name=name;
    System.out.print("Man--");
}
public String getName(){
    return name;
}
public void setName(String name){
    this.name=name;
   }
}