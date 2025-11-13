package java_21;

import java.util.ArrayList;
import java.util.List;

public record RecordPattern (String drink, String food) {

    public void  adpateer(double a){

    }

    public static void main (String [] args){
        RecordPattern record = new RecordPattern("beer", "pizza");
        if(record instanceof RecordPattern(var drink, var food)){
           System.out.println(drink+" "+food);
        }
        record.adpateer(1f);
    }
}
