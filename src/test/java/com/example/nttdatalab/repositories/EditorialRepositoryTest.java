package com.example.nttdatalab.repositories;

import com.example.nttdatalab.exceptions.advises.EditorialNotFoundException;
import com.example.nttdatalab.models.Book;
import com.example.nttdatalab.models.Editorial;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class EditorialRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private EditorialRepository editorialRepository;
    @BeforeEach
    void setUp() {
        List<Editorial> EditorialList = List.of(
                new Editorial("Panini Manga"),
                new Editorial("Last Drop")
        );

        editorialRepository.saveAll(EditorialList);
    }

    @Test
    void createEditorialInDbTest(){

        List<Editorial> editorialList = List.of(
                new Editorial("Blacked doors"),
                new Editorial("Recrutism")
        );

        List<Editorial> savedEditorials = editorialRepository.saveAll(editorialList);
        assertNotNull(savedEditorials);

    }
    @Test
    void readEditorialInDbTest(){
        List<Editorial> result = editorialRepository.findAll();
        int expected = 2;
        int actual = result.size();

        assertEquals(expected, actual);
    };

    @Test
    void findById(){
        Long longId = 1L;
        Editorial editorialFromDb = editorialRepository.findById(1L).orElseThrow(()-> new EditorialNotFoundException(longId));
        assertNotNull(editorialFromDb);
    }

    @Test
    void updateEditorialTest(){

        String name = "Panini Manga";
        Editorial editorial = editorialRepository.findEditorialByName(name).orElseThrow(()->new EditorialNotFoundException(1l));

       List<Book> booksList = List.of(
                new Book("El guardian invisible"),
                new Book("El cementerio de los libros olvidados")
        );

        List<Book> savedBooks =  bookRepository.saveAll(booksList);

        editorial.setBooks(savedBooks);

        Editorial updatedEditorial = editorialRepository.save(editorial);

        int expected = 2 ;
        int actual = updatedEditorial.getBooks().size();

        assertEquals(expected, actual);
    }

    @Test
    void deleteEditorialTest(){

        Integer id = 2;
        Long longId = Long.valueOf(id);
        var e = editorialRepository.findAll();
        System.out.println("");

        Boolean existBeforeDelete = editorialRepository.findById(longId).isPresent();

        editorialRepository.deleteById(longId);

        Boolean notExistAfterDelete = editorialRepository.findById(longId).isPresent();

        assertTrue(existBeforeDelete);
        assertFalse(notExistAfterDelete);
    }

    @Test
    void findEditorialByNameTest(){

        String name = "Panini Manga";
        var e = editorialRepository.findAll();

        Editorial editorial = editorialRepository.findEditorialByName(name).orElseThrow(()->new EditorialNotFoundException(1l));
        String expected = name;
        String actual = editorial.getName();

        assertEquals(expected, actual);
    }

}