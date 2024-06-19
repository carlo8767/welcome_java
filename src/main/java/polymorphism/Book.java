package polymorphism;

import java.lang.reflect.Array;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

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
    // SAME SIGNATURE DIFFERENT PARAMETER
    public List<String> extractBookTitle (Book book, List<String> listbook){
        listbook.add(book.getTitle());
        return listbook ;
    }

    public List<String> extractBookTitle (List<Book> list){
        List<String> listTitle;
        // OPTIONAL VERIFY IF AN ELEMENT IS NULL
        Optional<List<Book>> listOptional = Optional.ofNullable(list);
            if (listOptional.isPresent()){
                listTitle =  listOptional.get().stream()
                        .map(Book::getTitle)
                        .filter(xTitle -> !xTitle.isEmpty())
                        .toList();
                if (listTitle.isEmpty()){
                    throw new NoSuchElementException();
                }
                else{
                    return  listTitle;
                }

            }
            else{
                throw new NullPointerException();
            }
        }



    public String extractBookTitle (Book book){
        Optional<Book> optionalBook = Optional.ofNullable(book);
        if (optionalBook.isPresent()){
            return optionalBook.get().getTitle();
        }
        else {
            throw new NullPointerException();
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
