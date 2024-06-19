package inheritance;

public class ServiceAnimal {

    Animal animal;



    public void setStrategySound(Animal animal){
        this.animal =animal;
    }

    public String executeStrategy(){
        return this.animal.soundAnimal();
    }



}
