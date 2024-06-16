package inheritance;
public class Dog extends  Animal {


    public Dog(String name, String size){
        super(name, size);
    }

    protected  String soundAnimal(){
        return "bau";
    }
}
