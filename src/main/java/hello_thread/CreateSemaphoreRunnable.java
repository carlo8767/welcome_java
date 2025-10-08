package hello_thread;

import java.util.concurrent.Semaphore;

public class CreateSemaphoreRunnable implements  Runnable {

    Semaphore sem;


    public CreateSemaphoreRunnable (Semaphore sem){
        this.sem = sem;
    }



    @Override
    public void run() {


            try {
                sem.acquire();
                for(int i=0; i<10;i++) {

                    System.out.println("The semaphore is ok" + Thread.currentThread());
                    CreateSemaphoreShared.count += 1;
                    // IT DOES NOT MATTER, THE THREAD WILL WAIT UNTIL THE END
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupt"+ Thread.currentThread());
                throw new RuntimeException(e);
            }
            finally {
                // RELEASE THE RESOURCES
                sem.release();
            }
        }


    public static void main (String [] args) throws InterruptedException {
        // ONLY ONE THREAD AT TIME
        Semaphore s = new Semaphore(1);

        CreateSemaphoreRunnable rn1 = new CreateSemaphoreRunnable(s);
        CreateSemaphoreRunnable rn2 = new CreateSemaphoreRunnable(s);

        Thread t1 = new Thread(rn1, "Thread-1");
        Thread t2 = new Thread(rn2, "Thread-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + CreateSemaphoreShared.count);

    }
}

