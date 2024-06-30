package functionalInterface;

import functional_interface.StreamInterfaceNumber;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestStreamInterfaceNumber {


    StreamInterfaceNumber streamInterfaceNumber;

    @Test
    public void testMultiplicationList (){

        streamInterfaceNumber = new StreamInterfaceNumber();
        List<Double> listMultiplications = streamInterfaceNumber.multiplicationList(3,10);
        assertEquals(30,listMultiplications.get(1));
    }


    @Test
    public void testGenerateInfiniteStreamNoFixedSize (){

        streamInterfaceNumber = new StreamInterfaceNumber();
        List<Double> listMultiplications = streamInterfaceNumber.generateInfiniteStreamNoFixedSize();
        listMultiplications.forEach(System.out::println);
        assertEquals(5,listMultiplications.size());
    }


}
