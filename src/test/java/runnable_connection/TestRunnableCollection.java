package runnable_connection;

import hello_thread.BuildHouseRunnable;
import hello_thread.SellHouseRunnable;
import org.junit.jupiter.api.Test;


public class TestRunnableCollection {




    @Test
    public void multiThread(){
        Runnable buildHouse = new BuildHouseRunnable();
        Runnable sellHouse = new SellHouseRunnable();
        Thread threadOne = new Thread(buildHouse);
        Thread threadTwo = new Thread(sellHouse);
        threadOne.start();
        threadTwo.start();
    }

}
