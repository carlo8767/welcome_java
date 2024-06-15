package test.polymorphismTest;


import org.junit.Test;
import polymorphism.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.Assert.*;

public class TestBook {


    Book books;

    @Test
    public void extractBookTitleListNotEmpty (){
        List<Book> listBook = new ArrayList<Book>();
        listBook.add(new Book(UUID.randomUUID(), "fakeTitleOne", "fakeAuthorOne"));
        listBook.add(new Book(UUID.randomUUID(), "fakeTitleTwo", "fakeAuthorTwo"));
        books = new Book(UUID.randomUUID(), "","");
        List<String> listTitle = books.extractBookTitle(listBook);
        assertNotNull("Is empty", listTitle);
    }

    @Test
    public void extractBookTitleListEmpty (){
        String aspect;
        try{
            List<Book> listBook = new ArrayList<Book>();
            books = new Book(UUID.randomUUID(), "","");
            List<String> listTitle = books.extractBookTitle(listBook);
            aspect ="NotThrowNullPointerException";
            assertEquals("NullPointerException", aspect);

        }
        catch(NullPointerException nullPointerException){
            aspect = "NullPointerException";
            assertEquals("NullPointerException", aspect);
        }


    }






}
