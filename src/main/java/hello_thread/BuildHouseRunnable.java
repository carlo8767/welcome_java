package hello_thread;

public class BuildHouseRunnable implements Runnable{



    @Override
    public void run() {
        for(int i=0; i<10;i++){
            System.out.println("We are building the house");
            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
