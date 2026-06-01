package pattern.flyweigth;

public class AngryBird implements Flyweigth{



    String angry;
    String colour;


    public AngryBird(String angry, String colour){
        this.angry = angry;
        this.colour = colour;
    }


    @Override
    public void extrinsicNoShareDraw() {
       System.out.println(this.colour);
    }




}
