package functional_interface;

import java.util.List;

public class VoteComparator{


    String name;
    Integer vote;


    public VoteComparator(String name, Integer vote){
        this.name = name;
        this.vote = vote;
    }


    public void sortByVote (List<VoteComparator> vcList){
        vcList.sort((VoteComparator v1, VoteComparator v2)->
                v1.getVote().compareTo(v2.getVote()));
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



