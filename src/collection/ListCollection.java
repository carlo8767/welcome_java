package collection;


import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.*;
import java.util.stream.Collectors;

public class ListCollection {


    // SET DO NOT ALLOW DUPLICATE AND USE FOR MATHEMATICAL OPERATIONS
    public Set<Integer> getIntersection (Set<Integer> firsSet, Set<Integer> secondSet){
       Set <Integer>  setIntersect =  firsSet;
       // INTERSECTION OF TWO SET
       setIntersect.retainAll(secondSet);
        return setIntersect;
    }

    public Set<Integer> getDifference (Set<Integer> firsSet, Set<Integer> secondSet){
        Set <Integer>  setIntersect =  firsSet;
        // DIFFRENCE OF TWO SET
        setIntersect.removeAll(secondSet);
        return setIntersect;
    }

    public Set<Integer> getUnion (Set<Integer> firsSet, Set<Integer> secondSet){
        Set <Integer>  setIntersect =  firsSet;
        // DIFFRENCE OF TWO SET
        setIntersect.addAll(secondSet);
        return setIntersect;
    }


    public void noDuplicateDifference (){
        Set<Integer > listNumber = new HashSet<Integer>();
        for(int i=0; i<10;i++){
            listNumber.add(i);
        }
        Set<Integer> setSecondListNumber = new HashSet<Integer>();
        for(int i=3; i<13;i++){
            setSecondListNumber.add(i);
        }
        // intersection
        setSecondListNumber.removeAll(listNumber);
        setSecondListNumber.forEach(System.out::println);
    }


    public  Set<String> returnCharDuplicate(String duplicate){
        // TREE SET IS ORDER
        Set<String> decode = new TreeSet<String>();
        Set<String> duplicateChar = new TreeSet<>();
        for(int i =0; i<duplicate.length(); i++){
           String verify =""+duplicate.charAt(i);
           if(!decode.add(verify)){
               duplicateChar.add(verify);
           };
        }
        return  duplicateChar;
    }

    // LINKED LIST IS SIMILAR TO ARRAYLIST. HOWEVER, THEY ARE USEFUL TO PERFORM
    //  VARIOUS OPERATIONS, SUCH AS REMOVING AND ADD AT THE END OR AT FIRST
    // BECAUSE THEY SAVE THE ELEMENT WITH A REFERENCE AT THE PREVIOUS NODE.
    public List<String> removeNations (List<String> listNations, String nationRemove ){
        listNations= listNations.stream().filter(x-> !x.equals(nationRemove)).collect(Collectors.toList());
        return  listNations;
    }

    public List<String> addFirstElement (List<String> listNations, String nationAdd ){
        listNations.addFirst(nationAdd);
        return  listNations;
    }

    public List<String> addElementSpecficIndex (List<String> listNations, String nationAdd, int index){
        listNations.add(3,nationAdd);
        return  listNations;
    }


    // EVALUATION PERFORMANCE
    public String performanceArrayVsLinked (List<Integer> list, Integer number ){
        LocalTime start = LocalTime.now();
        LocalTime end = LocalTime.now();
        return ""+Duration.between(start, end).toNanos();
    }






}
