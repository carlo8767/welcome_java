package codeWar;

public class MainInt {
    public static void main(String[] args) {
        Counter<Integer> counter = new Counter<>();
        counter.add(1);
        System.out.println(counter.get(1));
        counter.add(1);
        counter.add(1);
        System.out.println(counter.get(1));
        counter.add(2);
        System.out.println(counter.get(0));
        counter.add(-1);
        System.out.println(counter.get(-1));
        System.out.println(counter.get(1));
        System.out.println(counter.get(2));

        // Used for exercise 2
        // System.out.print("Max: ")
        // System.out.println(counter.max());
    }
}
