package functionalInterface;

import functional_interface.ConsumerNations;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class TestConsumerNations {


    ConsumerNations consumerNations;

    @Test
    public void printListNations () {
        List<String> listNations = new ArrayList<>();
        listNations.add("Switzerland");
        listNations.add("Germany");
        listNations.add("Galles");
        consumerNations = new ConsumerNations();
        consumerNations.setListNations(listNations);
        Consumer<ConsumerNations> consumerNationsLetter = x -> x.getListNations()
                .forEach(System.out::println);
        consumerNationsLetter.accept(consumerNations);
    }

    @Test
    public void replaceValue () {
        // I USE CONSUMER TO PRINT THE LIST OF NATIONS OF VARIOUS OBJECT
        List<String> listNations = new ArrayList<>();
        listNations.add("Switzerland");
        listNations.add("Germany");
        listNations.add("Galles");
        consumerNations = new ConsumerNations();
        consumerNations.setListNations(listNations);
        Consumer<ConsumerNations> consumerNationsLetter = x -> x.printListNations(x);
        consumerNationsLetter.accept(consumerNations);
    }


}
