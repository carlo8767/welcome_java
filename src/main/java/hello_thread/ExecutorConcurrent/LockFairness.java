package hello_thread.ExecutorConcurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockFairness {

    private final Semaphore available = new Semaphore(10, true);
    ReentrantLock reentrantLock;
    Lock lock;

    public LockFairness (){
        // set Fairness to true
        this.reentrantLock = new ReentrantLock(true);

    }


    public  void sayHello(long threadNumber) throws InterruptedException {
        reentrantLock.lock();
        System.out.println("Thread "+ threadNumber + " is workinig on ");
        Thread.sleep(2000);
        reentrantLock.unlock();
    }


    public  void sayHelloLock() throws InterruptedException {
        reentrantLock.lock();
        System.out.println("enter in hello");
        Thread.sleep(2000);
        reentrantLock.unlock();
    }




    public static void main (String[] args) throws InterruptedException {
        LockFairness ms = new LockFairness();
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("Thread One");
                    ms.sayHello(Thread.currentThread().threadId());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("Thread Two");
                    ms.sayHello(Thread.currentThread().threadId());

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });



        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    System.out.println("Thread Three");
                    ms.sayHello(Thread.currentThread().threadId());

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        executorService.shutdown();
        if(!executorService.awaitTermination(10, TimeUnit.SECONDS)){
            System.out.println("Not Complete");
            executorService.shutdown();
        }
        else {
            System.out.println("Complete");
        }


    }


}
