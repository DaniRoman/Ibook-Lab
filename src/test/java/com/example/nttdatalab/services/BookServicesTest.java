package com.example.nttdatalab.services;

import com.example.nttdatalab.dto.BookDto;
import com.example.nttdatalab.models.Book;
import com.example.nttdatalab.repositories.BookRepository;
import com.example.nttdatalab.services.impServices.IBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.MapKeyClass;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class BookServicesTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private IBookService bookService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByTitle() {
    }

    @Test
    void findByEditorial() {
    }

    @Test
    void BookService_CreateBook_ReturnDto() {


    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }
}