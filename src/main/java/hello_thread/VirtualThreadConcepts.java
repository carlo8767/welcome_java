package hello_thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VirtualThreadConcepts {

public static void main (String[] args) throws InterruptedException {

    Thread.Builder builder = Thread.ofVirtual().name("worker-", 0);
    Runnable task = () -> {
        System.out.println("Thread ID: " + Thread.currentThread().threadId());
    };

// name "worker-0"
    Thread t1 = builder.start(task);
    t1.join();
    System.out.println(t1.getName() + " terminated");

// name "worker-1"
    Thread t2 = builder.start(task);
    t2.join();
    System.out.println(t2.getName() + " terminated");

    Runnable taskes = () -> {
        System.out.println("Thread ID: " + Thread.currentThread().threadId());
    };
    var executorw = Executors.newSingleThreadExecutor();
    var seconds = Executors.newVirtualThreadPerTaskExecutor();
    // execture will manage the task
    ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor(); // Virtual threads enabled

    for (int i = 1; i <= 5; i++) {
        int taskNumber = i;
        executor.submit(() -> {
            System.out.println("Virtual Thread executing task " + taskNumber + " by " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000); // Simulate some work
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    executor.shutdown(); // Always shut down the executor
}
}



