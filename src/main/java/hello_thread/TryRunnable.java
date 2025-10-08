package hello_thread;

public class TryRunnable {


    public static void main (String[] args){
        // YOU CAN GIVE PRIORITY AT BUILDING THE HOUSE
        // EXECUTION MULTITHREADING
        BuildHouseRunnable buildHouse = new BuildHouseRunnable();
        SellHouseRunnable sellHouse = new SellHouseRunnable();
        // CONTROLLING THE THREAD
        Thread threadOne = new Thread(buildHouse);
        threadOne.setName("Thread One");
        threadOne.setPriority(Thread.MAX_PRIORITY);
        Thread threadTwo = new Thread(sellHouse);
        threadOne.setName("Thread Two");
        threadOne.start();
        threadTwo.start();
        if (threadTwo.isAlive()){
            SellHouseRunnable sellAnotherHouse = new SellHouseRunnable();
            Thread thirdThread = new Thread(sellAnotherHouse);
            thirdThread.start();
        }
    }

}
