package pattern.Builder;

public class CarDirector {


    CarBuilder carBuilder;

    public void setCarBuild(CarBuilder carBuilder){
        this.carBuilder = carBuilder;
    }

    public void constructCarBuilder(){
        carBuilder.createNewCar();
        carBuilder.createColor();
        carBuilder.createEngine();
        carBuilder.createTyre();
    }

    public Car getCar(){
        return this.carBuilder.getCar();
    }


    public static  void  main (){
        CarBuilder carBuilderFerrari = new Ferrari();
        CarBuilder carBuilderMeclaren = new Meclaren();

        CarDirector carDirector = new CarDirector();
        carDirector.setCarBuild(carBuilderMeclaren);
        carDirector.constructCarBuilder();

        Car car = carDirector.getCar();
        System.out.println(car.getColor());

    }
}
