package hello_thread.ExecutorConcurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecuteTaskPromo {



    public static void main (String[] args) throws Exception {


        ExecutorService machina = Executors.newVirtualThreadPerTaskExecutor();



        Callable callable = new Callable() {
            @Override
            public Integer call() throws Exception {
                for(int i=0; i<1000; i++){
                    Thread.sleep(100);
                    System.out.println("Task executed virtual thread: " + Thread.currentThread().getName());
                }
                return 1;
            }
        };

        Future<Integer> f = machina.submit(callable);




        // Manage properly thread lifecyle and scheduling
        // CREATE 10 WORKER
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("Its running");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Its running t2");

            }
        });




        // Create a Callable task using Lambda Expression
        // RETURN A RESULT YOU CAN RETURN A VALUE !!!
        Callable<Integer> task = () ->{
            System.out.println("Calculating...");
            for(int i=0; i<1000; i++){
                Thread.sleep(100);
                System.out.println("Task executed by thread: " + Thread.currentThread().getName());
            }

            return 10 * 2;
        };
        // Submit the task and get a Future object teruatnt
        Future<Integer> future = executorService.submit(task);
        System.out.println(future.get());
        executorService.execute(t1);
        executorService.execute(t2);
       //  ExecutorService executor = Executors.newCachedThreadPool();
      // ONLY ONE THREAD  ExecutorService executor = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            System.out.println("Task executed by thread: " + Thread.currentThread().getName());
        });
        executorService.shutdown();
        // FIXED THREAD POOL VS CACHE POOL
        // NO OS THREAD!! IDEALLY FOR CHAT CONTINUE WAIT AND RUN !!
        ExecutorService executor =
                Executors.newVirtualThreadPerTaskExecutor();
        executor.submit(() -> System.out.println("virtual"));

        try (var ex = Executors.newVirtualThreadPerTaskExecutor()) {
            ex.submit(() -> {
                Thread.sleep(1000); // parked, not blocked
                return "done";
            });
        }


    }
}
