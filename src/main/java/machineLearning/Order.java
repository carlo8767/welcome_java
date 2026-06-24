package machineLearning;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Order {


    String customer;
    String product;

    public Order(String customer, String product, String category, int quantity, double unitPrice) {
        this.customer = customer;
        this.product = product;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }


    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getSentences() {
        return sentences;
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    String category;
    int quantity;
    double unitPrice;


    public List<String> sentences = List.of("The student enters the library and opens a book", "The student enters the classroom and opens a book", "The student enters the cafeteria and eats lunch");

    public static Set<String> creationMatrix(List<String> sentences) {

        String[][] transitionMatrix = new String[8][8];
        int count = 0;
        Set<String> singleWords = new HashSet<>();
        sentences.stream().map(String::toLowerCase)
                .forEach(word -> {
                    try {
                        Set<String> words = Arrays.stream(word.split(" ")).collect(Collectors.toSet());
                        singleWords.addAll(words);
                    } catch (Exception e) {
                        System.out.println("error!!");
                    }

                });
        return singleWords;

    }


    public static void otherMatrix(List<String> sentences) {
        Map<String, Integer> combination = new HashMap<>();
        for (String s : sentences) {
            String[] words = s.toLowerCase().split(" ");
            for (int i = 1; i < words.length; i++) {
                String search = words[i - 1] + " " + words[i];
                if (combination.containsKey(search)) {
                    combination.put(search, combination.get(search) + 1);
                } else {
                    combination.put(search, 1);
                }
            }
        }

        for (Map.Entry<String, Integer> entry : combination.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static void main(String[] args) {

        String[][] transitionMatrix = {{"They"}, {"Run"}, {"Away", "From"}};
        // First Row First Colum
        System.out.println(transitionMatrix[0][0]);

        List<String> sentences = List.of("The student enters the library and opens a book", "The student enters the classroom and opens a book", "The student enters the cafeteria and eats lunch");


        otherMatrix(sentences);


        List<Order> orders = List.of(
                new Order("Alice", "Laptop", "Electronics", 1, 1200),
                new Order("Bob", "Phone", "Electronics", 2, 800),
                new Order("Alice", "Book", "Books", 3, 15),
                new Order("David", "Desk", "Furniture", 1, 200),
                new Order("Bob", "Book", "Books", 5, 10),
                new Order("Charlie", "Headphones", "Electronics", 1, 150)
        );
        Double revenue = orders.stream()
                .map(order -> order.getQuantity()*order.getUnitPrice())
                .reduce(0.0, Double::sum);

        Double secondRevenue = orders.stream()
                .mapToDouble(order -> order.quantity*order.unitPrice)
                .sum();

        Map<String, Double> valueCategory =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                Order::getCategory,
                                Collectors.summingDouble(o -> o.getQuantity() * o.getUnitPrice())
                        ));


    }

}





