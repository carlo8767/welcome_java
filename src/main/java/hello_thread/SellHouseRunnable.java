package hello_thread;

public class SellHouseRunnable implements  Runnable{

   /*
   AVOID OVERRIDING
    syncornized (Class target)
    */




    @Override
    public void run() {
        for(int i=0; i<10;i++){
            System.out.println("We are selling the house"+ Thread.currentThread());
            try {

                Thread.sleep(2500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}


