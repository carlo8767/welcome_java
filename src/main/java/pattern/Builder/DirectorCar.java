package pattern.Builder;

public class DirectorCar {


    BuilderCar builderCar;

    public DirectorCar(BuilderCar builderCar){
        this.builderCar = builderCar;
    }


    public void createEngione(){
        this.builderCar.buildEngine();
    }

    public Car getCar(){
        return this.builderCar.getCar();
    }
}
