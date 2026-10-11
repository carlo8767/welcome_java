package hello_thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;

public class Barrier {



    public static void main (String[] args){

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        CountDownLatch countDownLatch = new CountDownLatch(5);
        Exchanger<Integer> exchanger = new Exchanger<>();
        ThreadGroup threadGroup = new ThreadGroup("hello0");
        // volatile deamon thread
        // barrier phaser, latches, exchangers
    }
}
