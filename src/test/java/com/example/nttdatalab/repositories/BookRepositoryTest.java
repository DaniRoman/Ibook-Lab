package com.example.nttdatalab.repositories;

import com.example.nttdatalab.exceptions.advises.BookTitleNotFoundException;
import com.example.nttdatalab.models.Book;
import com.example.nttdatalab.models.Editorial;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.border.TitledBorder;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EditorialRepository editorialRepository;
    @BeforeEach
    void setUp() {

        Editorial editorial = new Editorial("Falled coiz");
        editorial.setId(Long.valueOf(1));

        Editorial editorial1 = editorialRepository.save(editorial);

        Calendar calendar = Calendar.getInstance();
        Date publishTime = calendar.getTime();

        List<Book> bookList = List.of(
                new Book ("IT", "Stephen King", publishTime, 500,"Horror clown that eat kids", editorial1),
                new Book ("Doctor Sleep", "Stephen King", publishTime, 600,"Horror Book", editorial1));

        bookRepository.saveAll(bookList);

    }

    @Test
    void createBooksInDbTest(){

        Calendar calendar = Calendar.getInstance();
        Date publishTime = calendar.getTime();

        Editorial editorial = new Editorial("Falled Angel");
        editorial.setId(Long.valueOf(2));

        Editorial editorial1 = editorialRepository.save(editorial);

        Book book =  new Book ("Doctor Sleep", "Stephen King", publishTime, 600,"Horror Book", editorial1);

        Book bookFromDb = bookRepository.save(book);
        assertNotNull(bookFromDb);
    }

    @Test
    void readBooksInDbTest(){
        List<Book> result = bookRepository.findAll();
        int expected = 2;
        int actual = result.size();
        assertEquals(expected, actual);
    };

    @Test
    void readOneBookById(){

        Integer id = 1;
        Long longId = Long.valueOf(id);
        Optional book = bookRepository.findById(longId);
        assertTrue(book.isPresent());
    }

    @Test
    void findBookByNameTest(){

        String title = "Doctor Sleep";
        Book book = bookRepository.findBookByTitle(title).orElseThrow(()->new BookTitleNotFoundException(title));
        String expected = title;
        String actual = book.getTitle();

        assertEquals(expected, actual);
    }

    @Test
    void findBookByEditorialNameTest(){

        String name = "Falled coiz";
        List<Book> book = bookRepository.findBookByEditorialName(name);
        String expected = name;
        String actual = book.get(0).getEditorial().getName();
        assertEquals(expected, actual);

    }

    @Test
    void updateBookTest(){

        String title = "Doctor Sleep";
        var book = bookRepository.findBookByTitle(title).orElseThrow(()->new BookTitleNotFoundException(title));

        Editorial editorial = new Editorial("Dark Horse");
        Editorial dbsavedEditorial =  editorialRepository.save(editorial);

        book.setEditorial(dbsavedEditorial);
        Book updatedBook = bookRepository.save(book);

        String expected = "Dark Horse";
        String actual = updatedBook.getEditorial().getName();

        assertEquals(expected, actual);
    }

    @Test
    void deleteBookTest(){

        Integer id = 1;
        Long longId = Long.valueOf(id);

        Boolean existBeforeDelete = bookRepository.findById(longId).isPresent();

        bookRepository.deleteById(longId);

        Boolean notExistAfterDelete = bookRepository.findById(longId).isPresent();

        assertTrue(existBeforeDelete);
        assertFalse(notExistAfterDelete);
    }






}