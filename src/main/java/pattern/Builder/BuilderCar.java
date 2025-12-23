package pattern.Builder;

abstract class BuilderCar {

    Car car;

    public BuilderCar(Car car){
        this.car = car;
    }


    public Car getCar (){
        return this.car;
    }

    abstract void  buildEngine();
    abstract void  buildComponent();
}
