package polymorphism;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class Book {

    private UUID uuid;
    private String title;
    private String author;

    public Book(UUID uuid, String title, String author) {
        this.uuid = uuid;
        this.title = title;
        this.author = author;
    }


    // POLYMORPHISM OVERLOADING
    public List<String> extractBookTitle (List<Book> list){

        List<String> listTitle;
        Optional<List<Book>> listOptional = Optional.of(list);
            if (listOptional.get().isEmpty()){
                throw  new NullPointerException();
            }
            else{
                listTitle =  list.stream().map(Book::getTitle)
                        .toList();
                return  listTitle;
            }
        }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
