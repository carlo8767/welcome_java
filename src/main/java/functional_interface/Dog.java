package functional_interface;

public class Dog implements  Animal{
    @Override
    public String sound() {
        return "bau";
    }
}
