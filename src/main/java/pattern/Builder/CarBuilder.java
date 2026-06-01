package pattern.Builder;

public  abstract class CarBuilder {

    protected Car cars;

    public void createNewCar(){
        this.cars = new Car();
    }

    public Car getCar (){
        return  this.cars;
    }


    abstract void createColor();
    abstract void createEngine();
    abstract void createTyre();


}

