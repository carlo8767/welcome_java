package hello_thread.Parallel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ParallelSearch {



    public static void main (String[] args){



        List<Integer> ns = new ArrayList<>();
        for(int i =1; i< 10000;i++){
            ns.add(i);
        }
        long startTime = System.nanoTime();
        var a = ns.stream().parallel().mapToInt(Integer::intValue).sum();
        long estimatedTime = System.nanoTime() - startTime;
        System.out.println(estimatedTime);

        long startTimeNoParallel = System.nanoTime();
        var b = ns.stream().parallel().mapToInt(Integer::intValue).sum();
        long estimatedTimeNoParallel = System.nanoTime() - startTime;
        System.out.println(estimatedTimeNoParallel);

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        forkJoinPool.execute(new Runnable() {
            @Override
            public void run() {
                long startEst = System.nanoTime();
                List<Integer> ns = new ArrayList<>();
                for(int i =1; i< 10000;i++){
                    ns.add(i);
                }
                var sum = 0;
                for(Integer a : ns){
                    sum +=a;
                }

                long end = System.nanoTime() - startEst;
                System.out.println(end);

            }
        });
       // ForkJoinTask
       // forkJoinPool.invoke()

    }
}
