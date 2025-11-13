package patttern.observer;

public class ConcreteTrafficLight implements TrafficLight{



    @Override
    public void addObserver(VeichelsObserver veichelsObserver) {

    }

    @Override
    public void removeObserver(VeichelsObserver veichelsObserver) {

    }

    @Override
    public void notifyObserverGreen(VeichelsObserver veichelsObserver) {

        veichelsObserver.notification(TrafficLightEvents.GREEN);

    }


    public  static  void main(String[] args){
        ConcreteTrafficLight s = new ConcreteTrafficLight();
        VeichelsObserver veichelsObserver = new ConcreteVeichels();
        s.notifyObserverGreen(veichelsObserver);


    }

}
