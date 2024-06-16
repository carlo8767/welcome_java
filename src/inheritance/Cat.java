package inheritance;

public class Cat extends  Animal{


    public Cat(String name, String size){
        super(name, size);
    }

    protected  String soundAnimal(){
        return "miaow";
    }


}
