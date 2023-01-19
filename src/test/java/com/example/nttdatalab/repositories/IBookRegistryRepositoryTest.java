package com.example.nttdatalab.repositories;

import com.example.nttdatalab.exceptions.advises.BookRegistryNotFoundException;
import com.example.nttdatalab.collection.BookRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class IBookRegistryRepositoryTest {

    @Autowired
    BookRegistryRepository bookRegistryRepository;
    @BeforeEach
    void setUp() {

        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        List<BookRegistry> listOfRegistry = List.of(
                new BookRegistry("logMessage", currentDate),
                new BookRegistry("secondlogMessage", currentDate)
        );

        bookRegistryRepository.saveAll(listOfRegistry);

    }

    @Test
    void saveIBookRegistry(){
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        BookRegistry bookRegistry =   new BookRegistry("logMessage", currentDate);

        BookRegistry bookRegistry1 = bookRegistryRepository.save(bookRegistry);

        assertNotNull(bookRegistry1);
    }

    @Test
    void getAllIBookRegistry(){

        List<BookRegistry> bookRegistryList = bookRegistryRepository.findAll();

        int expected = 2;
        int actual = bookRegistryList.size();
        assertEquals(expected, actual);

    }
    @Test
    void getIBookRegistryById(){

        Integer id = 1;
        Long longId = Long.valueOf(id);
        Optional bookRegistry = bookRegistryRepository.findById("1");
        assertTrue(bookRegistry.isPresent());
    }
    @Test
    void updateIBookRegistry(){

        Integer id = 1;
        Long longId = Long.valueOf(id);
        BookRegistry bookRegistry = bookRegistryRepository.findById("1").orElseThrow(()-> new BookRegistryNotFoundException(longId));

        bookRegistry.setMessage("New Log message");
        bookRegistryRepository.save(bookRegistry);

        String expected = "New Log message";
        String actual = bookRegistry.getMessage();

        assertEquals(expected, actual);

    }
    @Test
    void deleteIBookRegistry(){

        Integer id = 2;
        Long longId = Long.valueOf(id);

        Boolean existBeforeDelete = bookRegistryRepository.findById("1").isPresent();

        bookRegistryRepository.deleteById("1");

        Boolean notExistAfterDelete = bookRegistryRepository.findById("1").isPresent();

        assertTrue(existBeforeDelete);
        assertFalse(notExistAfterDelete);
    }

}