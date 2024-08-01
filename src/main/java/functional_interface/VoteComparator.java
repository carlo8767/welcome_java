package functional_interface;

import java.util.Comparator;
import java.util.List;

public class VoteComparator{

    // COMPARATOR CONTAINS A COMPARE METHOD
    // TO SORT A LIST BASE ON 
    String name;
    Integer vote;


    public VoteComparator(String name, Integer vote){
        this.name = name;
        this.vote = vote;
    }


    public void sortByVote (List<VoteComparator> vcList){
        vcList.sort(Comparator.comparing(VoteComparator::getVote));
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }
}



