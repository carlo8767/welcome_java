package functionalInterface;

import functional_interface.FunctionalElection;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

public class TestFunctionNations {


    FunctionalElection functionalElection;


    @Test
    public void verifyConversionElection (){

        Map<String,Integer> mapNationsVote = new HashMap<>();
        mapNationsVote.put("Texax", 101000);
        mapNationsVote.put("Ohio", 201000);
        functionalElection = new FunctionalElection();
        List<String> listNations =  functionalElection.convertMapNationsToList(mapNationsVote);
        assertEquals("Texax",listNations.getFirst());
    }

}
