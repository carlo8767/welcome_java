package java_21.switchedPattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnusedVariable {


    public static void main (String [] args){

        ArrayList<Integer> listInteger = new ArrayList<>(Arrays.asList(1,2,3,5));

        var values = 5;
        for (Integer _ : listInteger){
            values+=1;
        }



    }
}
