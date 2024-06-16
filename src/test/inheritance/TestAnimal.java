package test.inheritance;

import inheritance.Animal;
import inheritance.Cat;
import inheritance.Dog;
import inheritance.ServiceAnimal;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestAnimal {

    ServiceAnimal serviceAnimal;

    @Test
    public void testStrategyCat(){
        Animal animal = new Cat("Cleopatra", "small");
        serviceAnimal = new ServiceAnimal();
        serviceAnimal.setStrategySound(animal);
        String sound = serviceAnimal.executeStrategy();
        assertEquals("should be miaow", sound,"miaow");
    }

    @Test
    public void testStrategyDog(){
        Animal animal = new Dog("Cleopatra", "small");
        serviceAnimal = new ServiceAnimal();
        serviceAnimal.setStrategySound(animal);
        String sound = serviceAnimal.executeStrategy();
        assertEquals("should be bau", sound,"bau");
    }
}
