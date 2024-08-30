package stream;

import java.time.Duration;
import java.time.Instant;


import java.util.List;
import java.util.stream.IntStream;


public class RandomNumber {


    public long generateRandomListParallelNumber (int lengthList){
        Instant start = Instant.now();
        List<Integer> list = List.of(IntStream.range(0,lengthList).parallel().sum());
        System.out.println("The parallel sum is "+list.getFirst());
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis();
    }

    public long generateRandomListSequentialNumber (int lengthList){
        Instant start = Instant.now();
        List<Integer> list = List.of(IntStream.range(0,lengthList).sum());
        System.out.println("The sequential sum is " +list.getFirst());
        Instant end = Instant.now();
        return Duration.between(start, end).toMillis();
    }
}
