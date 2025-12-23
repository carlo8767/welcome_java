package pattern.Builder;

public class CarFast extends BuilderCar{


    public CarFast(Car car) {
        super(car);
    }

    @Override
    void buildEngine() {
        this.car.setEngine("Strong Engine");
    }

    @Override
    void buildComponent() {

    }
}
