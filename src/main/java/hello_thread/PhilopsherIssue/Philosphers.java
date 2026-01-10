package hello_thread.PhilopsherIssue;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosphers {

    private final Lock leftFork;
    private final Lock rightFork;
    private final String name;

    public Philosphers(Lock leftFork, Lock rightFork, String name) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
    }


    public void think(){
        System.out.println(name+ "is thinking");
    }

    public void eatLock() {
        while (true) {
            if (leftFork.tryLock()) {
                try {
                    if (rightFork.tryLock()) {
                        try {
                            System.out.println(name + " is eating");
                            Thread.sleep(1000); // simulate eating
                            break; // done eating
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            rightFork.unlock();
                        }
                    }

                } finally {
                    leftFork.unlock();
                }
            }

            try {
                Thread.sleep(50); // wait a bit before retrying
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main (String [] args){
        // No fairness concrete ReentrantLock
        Lock fork1 = new ReentrantLock(false);
        Lock fork2 = new ReentrantLock(false);
        Lock fork3 = new ReentrantLock(false);
        Lock fork4 = new ReentrantLock(false);
        Lock fork5 = new ReentrantLock(false);

        Philosphers p1 = new Philosphers(fork1, fork2, "Socrates");
        Philosphers p2 = new Philosphers(fork2, fork3, "Platos");
        Philosphers p3 = new Philosphers(fork3, fork4, "Aristotle ");
        Philosphers p4 = new Philosphers(fork4, fork5, "Pythagoras");
        Philosphers p5 = new Philosphers(fork5, fork1, "Heraclitus");
        new Thread(p1::eatLock).start();
        new Thread(p2::eatLock).start();
        new Thread(p3::eatLock).start();
        new Thread(p4::eatLock).start();
        new Thread(p5::eatLock).start();



    }
}
