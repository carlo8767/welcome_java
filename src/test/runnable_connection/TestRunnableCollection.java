package test.runnable_connection;

import functional_interface.BuildHouseRunnable;
import functional_interface.SellHouseRunnable;
import org.junit.Test;

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
