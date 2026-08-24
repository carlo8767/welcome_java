package hello_thread.ExecutorConcurrent;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class QueueThread implements Callable {
    @Override
    public Integer call() throws Exception {
        Lock l = new ReentrantLock();

        Integer c = 1;

        try {
            var a = 3;
            for (int i = 1; i < 3; i++) {

                a *= i;
                Thread.sleep(1000);

                System.out.println("Thread id" + Thread.currentThread()+" "+Thread.currentThread().isVirtual());

            }
            l.lock();
            c+=a;
            l.unlock();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return c;
    }


    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        try {

            QueueThread queueThread = new QueueThread();
            Future completableFutureA = executorService.submit(queueThread);
            Future completableFutureB = executorService.submit(queueThread);
            executorService.shutdown();// DISABLE NEW TASK TO BE SUBMITTED
            // BLOCK THE SHUTDOWN UNTIL ALL THE TASK ARE COMPLETED
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdown();
                System.out.println("task are not completed");
            }
            else {
                executorService.shutdown();
                System.out.println("task completed and the value are " + completableFutureA.get().toString() );
            }




        } catch (Exception ex) {
            System.out.println("Shutdown");
            executorService.shutdownNow();
        }
    }
}