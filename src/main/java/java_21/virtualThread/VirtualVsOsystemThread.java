package java_21.virtualThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VirtualVsOsystemThread {




    public static void main (String[] args) throws InterruptedException {

        ExecutorService os = Executors.newFixedThreadPool(10);

        ExecutorService vs = Executors.newVirtualThreadPerTaskExecutor();


        os.submit(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i< 1000; i++){
                    System.out.println("Os");
                }
            }
        });
        /*
        vs.execute(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i< 100000; i++){
                    System.out.println("Completed");
                }
            }
        });
        vs.shutdown();
        vs.awaitTermination(1, TimeUnit.DAYS);*/

    }
}
