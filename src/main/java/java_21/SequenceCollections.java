package java_21;

import java.util.ArrayList;
import java.util.Arrays;

public class SequenceCollections {

    public static void main (String [] args) {

        // ADD FIST, ADD AT LAST
        var arraylist = new ArrayList<>(Arrays.asList("Dog", "Cat"));
        arraylist.addFirst("Tiger");
        arraylist.addLast("Lion");
        System.out.println(arraylist.getLast());
    }
}
