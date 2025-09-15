package sealed_class;

// you can also use non sealed to allow to extend the class
public non-sealed class Cat implements Animal {
    @Override
    public String sound() {
        return "Mia";
    }
}
