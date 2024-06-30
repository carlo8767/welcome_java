package functionalInterface;


import functional_interface.PredicateServiceElection;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;


import java.util.*;


public class TestPredicateElection {

    PredicateServiceElection predicateServiceElection;

    @Test
    public void verificationoThresholdNumberFalse (){
        Map<String, Integer> unitedStates = new HashMap<String, Integer>();
        unitedStates.put("Ohio",5000);
        unitedStates.put("Texax", 1000);
        Integer threshold = 58000;
        predicateServiceElection = new PredicateServiceElection();
        for(Map.Entry<String, Integer> extract : unitedStates.entrySet()){
            boolean evaluation = predicateServiceElection.verifyElection(x -> x > threshold , extract.getValue());
            if(!evaluation){
                System.out.println("Attention "+ extract.getKey()+ " do not reach the threshold");

            }
            assertFalse(evaluation);

        }
    }

    @Test
    public void verificationThresholdNumberTrue (){
        Map<String, Integer> unitedStates = new HashMap<String, Integer>();
        unitedStates.put("Ohio",6000);
        unitedStates.put("Texax", 1000);
        Integer threshold = 58000;
        predicateServiceElection = new PredicateServiceElection();
        for(Map.Entry<String, Integer> extract : unitedStates.entrySet()){
            boolean evaluation = predicateServiceElection.verifyElection(x -> x < threshold , extract.getValue());
            if(evaluation){
                System.out.println("Attention "+ extract.getKey()+ " do not reach the threshold");

            }
            assertTrue(evaluation);

        }
    }


    // YOU CAN USE REDUCE TO PERFORM VARIOUS OPERATION IN PARALLEL
    @Test
    public void sumOperation (){
        Map<String, Integer> unitedStates = new HashMap<String, Integer>();
        unitedStates.put("Ohio",6000);
        unitedStates.put("Texax", 1000);
        unitedStates.put(("California"),1000);
        Integer sumNation =  unitedStates.entrySet().stream().map(Map.Entry::getValue)
                .reduce(0, Integer::sum);
        assertEquals(8000,sumNation);


        }
    }
