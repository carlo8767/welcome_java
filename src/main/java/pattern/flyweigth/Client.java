package pattern.flyweigth;

public class Client {


    static void main(String[] args){



        String[] colours = {"red", "blue","yellow"};
        FactoryFlyweigth factoryFlyweigth = new FactoryFlyweigth();
        factoryFlyweigth.getExtrinsicAngryBird("red");
        for(int i =0; i<100; i++){
           Flyweigth flyweigth =  factoryFlyweigth.getExtrinsicAngryBird(colours[(int) (Math.random()*colours.length)]);
           flyweigth.extrinsicNoShareDraw();
        }
    }
}
