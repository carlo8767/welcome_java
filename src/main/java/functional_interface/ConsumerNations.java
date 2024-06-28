package functional_interface;

import java.util.List;

public class ConsumerNations {


    public List<String> listNations;


    public void printListNations (ConsumerNations consumerNations){
        consumerNations.getListNations().forEach(System.out::println);
    }

    public List<String> getListNations() {
        return listNations;
    }

    public void setListNations(List<String> listNations) {
        this.listNations = listNations;
    }
}
