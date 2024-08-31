package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MainProgress {

    public static void main(String [] args){

        List<String> listNames = new ArrayList<String>();
        listNames.add("Jane");
        listNames.add("Kate");
        listNames.add("Mario");
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
    }
}
