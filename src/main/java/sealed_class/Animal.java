package sealed_class;


// Can I have a control in the class implementation
public sealed interface Animal permits Cat, Dog {

    String sound();


}
