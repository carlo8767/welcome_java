package inheritance;

import inheritance.Animal;
import inheritance.Cat;
import inheritance.Dog;
import inheritance.ServiceAnimal;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestAnimal {

    ServiceAnimal serviceAnimal;

    @Test
    public void testStrategyCat(){
        Animal animal = new Cat("Cleopatra", "small");
        serviceAnimal = new ServiceAnimal();
        serviceAnimal.setStrategySound(animal);
        String sound = serviceAnimal.executeStrategy();
        assertEquals("miaow", sound,"should be miaow");
    }

    @Test
    public void testStrategyDog(){
        Animal animal = new Dog("Cleopatra", "small");
        serviceAnimal = new ServiceAnimal();
        serviceAnimal.setStrategySound(animal);
        String sound = serviceAnimal.executeStrategy();
        assertEquals("bau", sound,"should be bau");
    }
}
