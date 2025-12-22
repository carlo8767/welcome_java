package java_21.virtualThread;

public class CuncurrentlyThread {




    public static void main (String [] args) throws  InterruptedException{

        Thread.Builder builder = Thread.ofVirtual().name("worker-", 0);
        Runnable task = () -> {

            try {
                System.out.println("Thread ID: " + Thread.currentThread().threadId());
                Thread.sleep(2000);
                System.out.println("Thread ID: " + Thread.currentThread().threadId());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        // name "worker-0"
        Thread t1 = builder.start(task);
        t1.join();
        System.out.println(t1.getName() + " terminated");
        Thread.sleep(1000);
        // name "worker-1"
        Thread t2 = builder.start(task);
        t2.join();
        System.out.println(t2.getName() + " terminated");
    }
}
