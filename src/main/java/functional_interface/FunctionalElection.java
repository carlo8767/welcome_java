package functional_interface;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


public class FunctionalElection {


    // A FUNCTION INTERFACE TAKE AS PARAMETER AN  OBJECT A AND CONVERT TO ON OBJECT F
    public List<String> convertMapNationsToList (Map<String, Integer> mapNationsVote){
        Function<Map<String, Integer>, ArrayList<String>> function = x ->  new ArrayList<String>(x.keySet());
        return function.apply(mapNationsVote);
    }



}
