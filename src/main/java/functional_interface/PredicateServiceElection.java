package functional_interface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Locale.filter;

public class PredicateServiceElection  {

    // WE CAN USE PASS THE FUNCTIONAL INTERFACE TO CREATE
    // VARIOUS EXPRESSION
    public boolean verifyElection (Predicate<Integer> predicateElection, Integer valueVote){
        return predicateElection.test(valueVote);
    }

}
