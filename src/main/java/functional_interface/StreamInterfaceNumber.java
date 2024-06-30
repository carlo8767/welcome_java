package functional_interface;

import java.util.List;
import java.util.stream.Stream;

public class StreamInterfaceNumber {

    public void iterateInfiniteStreamNoFixedSize (Integer operand ){

        //The Streams API provides two static methods to generate a stream from a function:
        // Stream.iterate and Stream.generate
        // seed is the value that it will iterate at the end
        Stream.iterate(operand, n-> n *2)
                .limit(10)
                .forEach(System.out::println);


    }

    public  List<Double> generateInfiniteStreamNoFixedSize(){

        return Stream.generate(Math::random)
                .limit(5)
                .toList();

    }

    public List<Double> multiplicationList (Integer operand, Integer increment){
        if (operand != null){
            return Stream.iterate((double) operand,
                            n ->  n *increment )
                    .limit(10)
                    .toList();
        }
        else {
            throw  new NullPointerException();
        }
    }


}
