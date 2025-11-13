package solid;

public class Veterinarian{

    private Animal animal;

    public Veterinarian (Animal animal){
        this.animal = animal;
    }

    public Animal getAnimalCure() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public static void main (String args []) {
        
        for (int i = 0; i < 1; i++){
            System.out.println(i);
        }
                Dog dog = new Dog();
                Cat cat = new Cat();
                Bird bird = new Bird();
                Veterinarian a = new Veterinarian(dog);
                a.getAnimalCure().TypeCure();;
                a.setAnimal(cat);
                a.getAnimalCure().TypeCure();
                a.setAnimal(bird);
                a.getAnimalCure().TypeCure();
            }
}
