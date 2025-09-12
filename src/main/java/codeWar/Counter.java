package codeWar;

import java.util.*;
import java.util.stream.Collectors;

public class Counter<T> {


    public Counter() {
        this.map = new HashMap<>();
    }

    private Map<T, Integer> map;

    public void add(T element) {
        if(map.containsKey(element)){
            map.put(element, map.get(element)+1);
        }
        else {
            map.put(element,1);
        }


    }

    public int get(T element) {
        return  map.get(element);
        // TODO Get the number of occurrences of the element
    }

    public int max(T element) {

     return 0;
    }
}
