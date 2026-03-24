package pattern.Builder;

public class Ferrari extends  CarBuilder{

    @Override
    void createColor() {
        this.cars.setColor("red");
    }

    @Override
    void createEngine() {
        this.cars.setEngine("Ferrari");
    }

    @Override
    void createTyre() {
       this.cars.setTyre("Bridgestone");
    }


}
