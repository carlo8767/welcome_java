package pattern.Builder;

public class Meclaren extends CarBuilder {


    @Override
    void createColor() {
        this.cars.setColor("black");
;
    }

    @Override
    void createEngine() {
        this.cars.setEngine("Mercedes");
    }

    @Override
    void createTyre() {
        this.cars.setTyre("Michelin");
    }
}
