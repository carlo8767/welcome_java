package functional_interface;


import java.util.function.Predicate;



public class PredicateServiceElection  {

    // PREDICATE IS A FUNCTIONAL INTERFACE THAT PROVIDE A TEST
    // METHOD THAT VERIFY A BOOLEAN EXPRESSION
    public boolean verifyElection (Predicate<Integer> predicateElection, Integer valueVote){
        return predicateElection.test(valueVote);
    }




}
