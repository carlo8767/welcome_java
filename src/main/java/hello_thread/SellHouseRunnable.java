package hello_thread;

public class SellHouseRunnable implements  Runnable{
    @Override
    public void run() {
        for(int i=0; i<10;i++){
            System.out.println("We are selling the house");
            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}


