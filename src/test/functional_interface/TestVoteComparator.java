package test.functional_interface;

import functional_interface.VoteComparator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.*;

public class TestVoteComparator {


    VoteComparator vs;


    @Test
    public void testVoteComparator(){
        // SOERT COMPARE TO ALLOW ME TO SORT A LIST FROM SMALLER TO LARGER OR LARGER TO SMALLER
        vs = new VoteComparator("Italian", 35);
        List<VoteComparator> listVoteComparator = new ArrayList<VoteComparator>();
        listVoteComparator.add(new VoteComparator("English", 58));
        listVoteComparator.add(new VoteComparator("German", 98));
        listVoteComparator.add(new VoteComparator("Math", 10));
        listVoteComparator.add(new VoteComparator("Programming", 25));
        vs.sortByVote(listVoteComparator);
        // sort from smaller to larger
        String lastValue = listVoteComparator.getLast().getName();
        assertEquals("German", lastValue);
    }

    @Test
    public void testListVoteComparator(){
        // ALLOW ME TO SORT A LIST FROM SMALLER TO LARGER OR LARGER TO SMALLER
        List<VoteComparator> listVoteComparator = new ArrayList<VoteComparator>();
        listVoteComparator.add(new VoteComparator("English", 1));
        listVoteComparator.add(new VoteComparator("German", 1));
        listVoteComparator.add(new VoteComparator("Math", 0));
        listVoteComparator.add(new VoteComparator("Programming", 10));
        listVoteComparator.sort((VoteComparator v1, VoteComparator v2)-> v2.getVote().compareTo(v1.getVote()));
        String lastValue = listVoteComparator.getLast().getName();
        assertEquals("Math", lastValue);
    }


}
