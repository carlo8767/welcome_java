package collection;

import java.util.PriorityQueue;p
import java.util.Queue;

public class PriorityQueenPerson implements  Comparable<PriorityQueenPerson> {





    String name;
    int priority;


    public PriorityQueenPerson( int priority, String name){
        this.name = name;
        this.priority = priority;
    }



    @Override
    public int compareTo(PriorityQueenPerson p) {
        // I CAN DECIDES THE ORDER WITH COMPARE TO BASE ON THE OUTCOME
        // - 1 YOU PRIORATIZE THE HIGHER PRIORITY
        if(this.getPriority()> p.getPriority()){
            return -1;
        }
        else if(this.getPriority() < p.getPriority()) {
            return  1;
        }
        else {
            return  0;
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    public static void main(String [] args){
        // It put the elements base on the order eventually you can create as weel a Deque Collaction
        Queue<PriorityQueenPerson> queePeople = new PriorityQueue<>();
        queePeople.add(new PriorityQueenPerson(2, "Carlo"));
        queePeople.add(new PriorityQueenPerson(4, "Pippo"));
        queePeople.add(new PriorityQueenPerson(1, "Giovanni"));
        queePeople.add(new PriorityQueenPerson(5, "Gianni"));
        queePeople.add(new PriorityQueenPerson(5, "Francesco"));

        var s = queePeople.remove(); // REMOVE THE HEAD
        System.out.println(s.getName());
    }
}
