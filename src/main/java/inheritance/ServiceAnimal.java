package inheritance;

public class ServiceAnimal {

    Animal animal;



    public void setStrategySound(Animal animal){
        this.animal =animal;
    }

    public String executeStrategy(){
        return this.animal.soundAnimal();
    }

    static void main (String [] args){
        ServiceAnimal serviceAnimal = new ServiceAnimal();
        Animal animal1 = new Dog("Pipp", "big" );

        serviceAnimal.setStrategySound(animal1);
        String sound = serviceAnimal.executeStrategy();
        System.out.println(sound);
    }

}
