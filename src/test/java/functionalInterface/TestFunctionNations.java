package functionalInterface;

import functional_interface.FunctionalElection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class TestFunctionNations {

    FunctionalElection functionalElection;

    @Test
    public void veriyfLinkedLest (){
        List <Integer>  list = new LinkedList();
        list.add(3);
        list.forEach(System.out::println);
    }

    @Test
    public void testVerifyConversionElection (){

        Map<String,Integer> mapNationsVote = new HashMap<>();
        mapNationsVote.put("Texax", 101000);
        mapNationsVote.put("Ohio", 201000);
        functionalElection = new FunctionalElection();
        List<String> listNations =  functionalElection.convertMapNationsToList(mapNationsVote);
        Assertions.assertEquals("Texax", listNations.getFirst());
    }

}
