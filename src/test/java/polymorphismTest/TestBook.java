package polymorphismTest;



import org.junit.jupiter.api.Test;
import polymorphism.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class TestBook {


    Book books;
    List<Book> listBook;

    @Test
    public void testExtractListBooks (){
        List<Book> listBook = new ArrayList<Book>();
        listBook.add(new Book(UUID.randomUUID(), "fakeTitleOne", "fakeAuthorOne"));
        listBook.add(new Book(UUID.randomUUID(), "fakeTitleTwo", "fakeAuthorTwo"));
        books = new Book(UUID.randomUUID(), "","");
        List<String> listTitle = books.extractBookTitle(listBook);
        assertNotNull(listTitle, "is empty");
    }

    @Test
    public void testExtractBookTitleNull (){
        String valueAspect;
        try{
            books = new Book(UUID.randomUUID(), "","");
            List<String> listTitle = books.extractBookTitle(listBook);
            valueAspect ="NotThrowNullPointerException";
            assertEquals("NullPointerException", valueAspect);

        }
        catch(NullPointerException nullPointerException){
            valueAspect = "NullPointerException";
            assertEquals("NullPointerException", valueAspect);
        }

    }

    @Test
    public void testExtractListBookNotFullEmpty (){
        List<Book> listBook = new ArrayList<Book>();
        listBook.add(new Book(UUID.randomUUID(), "fakeTitleOne", "fakeAuthorOne"));
        listBook.add(new Book(UUID.randomUUID(), "", ""));
        listBook.add(new Book(UUID.randomUUID(), "fakeTitleThree", "fakeAuthorThree"));
        books = new Book(UUID.randomUUID(), "","");
        List<String> listTitle = books.extractBookTitle(listBook);
        assertEquals(2, listTitle.size());
    }

    @Test
    public void testExtractListBookEmpty (){
        String valueAspect;
        try {
            List<Book> listBook = new ArrayList<Book>();
            listBook.add(new Book(UUID.randomUUID(), "", ""));
            books = new Book(UUID.randomUUID(), "","");
            List<String> listTitle = books.extractBookTitle(listBook);
            valueAspect ="NotNoSuchElementException";
            assertEquals("NoSuchElementException", valueAspect);
        }
        catch (NoSuchElementException e) {
            valueAspect ="NoSuchElementException";
            assertEquals("NoSuchElementException", valueAspect);
        }

    }

}
