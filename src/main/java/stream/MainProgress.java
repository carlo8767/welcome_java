package stream;

import java.util.*;
import java.util.stream.Collectors;

public class MainProgress {

    public static void main(String [] args){

        List<String> listNames = new ArrayList<>();
        listNames.add("Jane");
        listNames.add("Kate");
        listNames.add("Marioo");
        listNames.add("Anna");
        listNames.add("Anna");
        // COUNT THE NAME THAT START BY K
        long value = listNames.stream().filter(Objects::nonNull).
                filter(x-> x.startsWith("K"))
                .count();
        System.out.println("The number of letter start with K is "+ value);
        // ORDER THE LIST ALPHABETICALLY
        listNames = listNames.stream().filter(Objects::nonNull).sorted().collect(Collectors.toList());
        System.out.println(listNames);
        // REMOVE SPECIFIC VALUE
        listNames =  listNames.stream().dropWhile(x-> x.equals("Anna")).toList();
        System.out.println(listNames);
        // GIVE THE INDEX AT DUPLICATE
        List<String> newlistNames = new ArrayList<>();
        newlistNames.add("Jane");
        newlistNames.add("Anna");
        newlistNames.add("Anna");
        newlistNames.add("Anna");
        Set<String> listDuplicate = new HashSet<>();
        for(int i =0; i<newlistNames.size(); i++) {
            if(!listDuplicate.add(newlistNames.get(i))){
                System.out.println("Find duplicate at index "+ i );
            }
        }
    }
}
