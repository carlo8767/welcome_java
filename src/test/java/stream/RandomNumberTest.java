package stream;

import org.junit.jupiter.api.Test;


public class RandomNumberTest {


    private static  final Integer  elapseTime = 1000000000;


    @Test
    public void verifyPerformance (){
        RandomNumber randomNumber = new RandomNumber();
        // var allow to not specifyu khjfyh
        var parallelTime = randomNumber.generateRandomListParallelNumber(elapseTime);
        var sequentialTime = randomNumber.generateRandomListSequentialNumber(elapseTime);
        if(parallelTime < sequentialTime){
            System.out.println("Parallel performs in "+parallelTime+
            " and the sequence performs "+ sequentialTime);
        }
        else {
            System.out.println("Sequence perform "+ sequentialTime+
            " and the parallel performs in "+ parallelTime);
        }
    }
}