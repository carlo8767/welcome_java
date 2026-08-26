package java_21.virtualThread;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashCuncurrent {


    public static void main (String[] args) throws InterruptedException {

        HashMap<String,Integer> hashMap = new HashMap<>();
        hashMap.put("A", 1);
        hashMap.put("B", 2);
        hashMap.put("C", 3);

        Map<Integer, Object> table = new ConcurrentHashMap<>();
        table.put(1, "nodata");
        table.put(2, 23);



    }
}
