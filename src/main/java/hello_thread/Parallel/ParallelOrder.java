package hello_thread.Parallel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParallelOrder {




    public static void main (String[] args){
        Integer [] array = {1,2,3,4,5,6};
        List<Integer> listOfIntegers =
                new ArrayList<>(Arrays.asList(array));

        listOfIntegers.stream()
                .parallel()
                .forEach(x-> System.out.println("First Order is not deterministic "+ x));

        listOfIntegers.stream()
                .parallel()
                .forEach(x-> System.out.println("Second Order is not deterministic "+ x));


        // LAZY STREAM
        try {
            List<String> listOfStrings =
                    new ArrayList<>(Arrays.asList("one", "two"));


            String concatenatedString = listOfStrings
                    .stream()


                    .peek(s -> listOfStrings.add("three"))

                    .reduce((a, b) -> a + " " + b)
                    .get();

            System.out.println("Concatenated string: " + concatenatedString);

        } catch (Exception e) {
            System.out.println("Exception caught: " + e.toString());
        }
    }
}
