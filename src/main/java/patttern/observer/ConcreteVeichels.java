package patttern.observer;

import static patttern.observer.TrafficLightEvents.*;

public class ConcreteVeichels implements  VeichelsObserver {


    @Override
    public void notification(TrafficLightEvents trafficLightEvents) {
        switch(trafficLightEvents){
            case GREEN -> System.out.println("GO");
            case RED -> System.out.println("STOP");
            case YELLOW -> System.out.println("BEGIN TO STOP");
        }
    }

}
