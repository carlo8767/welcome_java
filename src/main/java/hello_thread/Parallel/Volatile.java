package hello_thread.Parallel;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Volatile {

    static boolean keepLooping = true;


    public static void main(String[] args) throws InterruptedException {




        ExecutorService ex = Executors.newVirtualThreadPerTaskExecutor();
        ex.execute(new Runnable() {
            @Override
            public void run() {
                while(keepLooping){
                    System.out.println("keep t1 looping");
                }
            }
        });

        ex.execute(new Runnable() {
            @Override
            public void run() {
                while(keepLooping){
                    System.out.println("keep t2 looping");
                }
            }
        });
        ex.shutdown();
        keepLooping = false;
        ex.awaitTermination(100000, TimeUnit.SECONDS);


        Runtime n = Runtime.getRuntime();
        System.out.println(n);





    }

}
