package patttern.observer;

public interface TrafficLight {


    public void addObserver(VeichelsObserver veichelsObserver);
    public void removeObserver(VeichelsObserver veichelsObserver);
    public void notifyObserverGreen(VeichelsObserver veichelsObserver);

}
