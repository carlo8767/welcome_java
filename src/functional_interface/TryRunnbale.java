package functional_interface;

public class TryRunnbale {


    public static void main (String[] args){
        // YOU CAN GIVE PRIORITY AT BUILDING THE HOUSE
        // EXECUTION MULTITHREADING
        BuildHouseRunnable buildHouse = new BuildHouseRunnable();
        SellHouseRunnable sellHouse = new SellHouseRunnable();
        Thread threadOne = new Thread(buildHouse);
        Thread threadTwo = new Thread(sellHouse);
        threadOne.start();
        threadTwo.start();
    }

}
