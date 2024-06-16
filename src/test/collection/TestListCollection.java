package test.collection;

import collection.ListCollection;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;
import java.util.stream.Collectors;

public class TestListCollection {


    ListCollection listCollection;


    // TEST SET
    @Test
    public void testIntersection(){
        Set<Integer> firstListNumber = new HashSet<Integer>();
        for(int i=0; i<10;i++){
            firstListNumber.add(i);
        }
        Set<Integer> secondListNumber = new HashSet<Integer>();
        for(int i=0; i<5;i++){
            secondListNumber.add(i);
        }
        listCollection = new ListCollection();
        Set<Integer> intersectionSet = listCollection.getIntersection(firstListNumber,secondListNumber );
        assertEquals(5, intersectionSet.size());
    }

    @Test
    public void testDifference(){
        Set<Integer> firstListNumber = new HashSet<Integer>();
        for(int i=0; i<11;i++){
            firstListNumber.add(i);
        }
        Set<Integer> secondListNumber = new HashSet<Integer>();
        for(int i=0; i<10;i++){
            secondListNumber.add(i);
        }
        listCollection = new ListCollection();
        Set<Integer> intersectionSet = listCollection.getDifference(firstListNumber,secondListNumber );
        assertEquals(1, intersectionSet.size());
    }
    @Test
    public void testUnion(){
        Set<Integer> firstListNumber = new HashSet<Integer>();
        for(int i=0; i<10;i++){
            firstListNumber.add(i);
        }
        Set<Integer> secondListNumber = new HashSet<Integer>();
        for(int i=10; i<20;i++){
            secondListNumber.add(i);
        }
        listCollection = new ListCollection();
        Set<Integer> intersectionSet = listCollection.getUnion(firstListNumber,secondListNumber );
        assertEquals(20, intersectionSet.size());
    }

    @Test
    public void testRemoveDuplicate (){
        String duplication = "Carrraro";
        listCollection = new ListCollection();
        Set<String> removeDuplicate = listCollection.returnCharDuplicate(duplication);
        String verification = removeDuplicate.stream().collect(Collectors.joining());
        assertEquals("ar", verification);
    }

    // TEST LINKED LIST
    @Test
    public void testRemoveElement(){
        listCollection = new ListCollection();
        List<String> listNations = new LinkedList<String>();
        listNations.add("Italy");
        listNations.add("Germany");
        listNations.add("Australia");
        listNations.add("Italy");
        listNations = listCollection.removeNations( listNations, "Italy");
        assertEquals(2, listNations.size());
    }

    @Test
    public void testAddFirstElement(){
        listCollection = new ListCollection();
        List<String> listNations = new LinkedList<String>();
        listNations.add("Italy");
        listNations.add("Germany");
        listNations.add("Australia");
        listNations.add("Italy");
        listCollection.addFirstElement(listNations, "Brasil");
        assertEquals(listNations.getFirst(), "Brasil");
    }

    @Test
    public void testAddSpecificElement(){
        listCollection = new ListCollection();
        List<String> listNations = new LinkedList<String>();
        listNations.add("Italy");
        listNations.add("Germany");
        listNations.add("Australia");
        listNations.add("Italy");
        listCollection.addElementSpecficIndex(listNations, "Brasil", 3 );
        assertEquals(listNations.get(3), "Brasil");
    }

    @Test
    public void testPerformance(){
        listCollection = new ListCollection();
        List<Integer> listNations = new ArrayList<Integer>();
        for(int i = 0; i<100000; i++){
            listNations.add(i);
        }
        String  performanceArrayList = ""+listCollection.performanceArrayVsLinked(listNations, 2);
        System.out.println(performanceArrayList);
        List<Integer> linkedList = new LinkedList<Integer>();
        for(int i = 0; i<100000; i++){
            linkedList.add(i);
        }
        String  performanceLinkedList = ""+listCollection.performanceArrayVsLinked(listNations, 2);
        System.out.println(performanceArrayList);
    }

    @Test
    public void testMap(){
        listCollection = new ListCollection();
        List<String> listItaly = new ArrayList<String>();
        listItaly.add("Inter");
        listItaly.add("Bologna");
        listItaly.add("Fiorentina");
        listItaly.add("Roma");
        listItaly.add("Juventus");
        listItaly.add("Napoli");
        List<String> listEngland= new ArrayList<String>();
        listEngland.add("Arsenal");
        listEngland.add("ManchesterCity");
        listEngland.add("ManchesterUnited");
        listEngland.add("Liverpool");
        listEngland.add("Leeds");
        listEngland.add("Watford");
        Map<String, List<String>> listTeams = new HashMap<String, List<String>>();
        listTeams.put("Italy", listItaly);
        listTeams.put("England", listEngland);
        listItaly = listCollection.extractTeam(listTeams, "Italy");
        assertEquals(listItaly.getFirst(), "Inter");

    }
}
