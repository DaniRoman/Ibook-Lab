package com.example.nttdatalab.controllers;

import com.example.nttdatalab.dto.BookDto;
import com.example.nttdatalab.dto.BookDtoRequest;
import com.example.nttdatalab.exceptions.advises.BookNotFoundException;
import com.example.nttdatalab.exceptions.advises.BookPublishDateException;
import com.example.nttdatalab.exceptions.advises.BookTitleNotFoundException;
import com.example.nttdatalab.models.Book;
import com.example.nttdatalab.models.Editorial;
import com.example.nttdatalab.repositories.BookRepository;
import com.example.nttdatalab.services.BookServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jdi.LongValue;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookServices bookServices;

    @Autowired
    ObjectMapper om;

    @Test
    public void createBookControllerTest() throws Exception{
        Editorial editorial = new Editorial("Test");

        Calendar calendar = Calendar.getInstance();
        Date publishedTime = calendar.getTime();

        BookDto bookDto = new BookDto(1l, "IT", "Dark", publishedTime, 300, "Fear", editorial);

        when(bookServices.saveBook(bookDto)).thenReturn(bookDto);

        mockMvc.perform(post("/books/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(bookDto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("IT"));

    }
    @Test
    public void trhowExeptionWhenPublishedDateTest() throws Exception{
        BookDto bookDto = new BookDto();
        doThrow(BookPublishDateException.class).when(bookServices).saveBook(any());
        mockMvc.perform(post("/books/save",bookDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void readAllBooksControllerTest() throws Exception {

        Editorial editorial = new Editorial("Test");

        Calendar calendar = Calendar.getInstance();
        Date publishedTime = calendar.getTime();

        List<BookDto> bookDtoList =  List.of(
                new BookDto(1l, "IT", "Dark", publishedTime, 300, "Fear", editorial),
                new BookDto(1l, "Doctor", "Dark", publishedTime, 677, "Fear", editorial)
        );

        when(bookServices.findAll()).thenReturn(bookDtoList);

        mockMvc.perform(get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)));

    }
    @Test
    public void readBookByIdControllerTest() throws Exception {
        Editorial editorial = new Editorial("Test");

        Calendar calendar = Calendar.getInstance();
        Date publishedTime = calendar.getTime();

        BookDto bookDto = new BookDto(1l, "IT", "Dark", publishedTime, 300, "Fear", editorial);


        when(bookServices.findById(1l)).thenReturn(bookDto);

        mockMvc.perform(get("/books/id/{id}",1l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void trhowExeptionWhenIdNotFoundTest() throws Exception {
        Long longId = 1l;
        doThrow(BookNotFoundException.class).when(bookServices).findById(anyLong());
        mockMvc.perform(get("/books/id/{id}",1l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void readBookByTitleControllerTest() throws Exception {
        Editorial editorial = new Editorial("Test");

        Calendar calendar = Calendar.getInstance();
        Date publishedTime = calendar.getTime();

        BookDto bookDto = new BookDto(1l, "IT", "Dark", publishedTime, 300, "Fear", editorial);


        when(bookServices.findByTitle("IT")).thenReturn(bookDto);

        mockMvc.perform(get("/books/title/{title}","IT")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("IT"));

    }

    @Test
    public void trhowExeptionWhenNameNotFoundTest() throws Exception {
        Long longId = 1l;
        doThrow(BookTitleNotFoundException.class).when(bookServices).findByTitle(anyString());
        mockMvc.perform(get("/books/title/{title}",1l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updataBookControllerTest() throws Exception {
        Long longId = 1l;

        Calendar calendar = Calendar.getInstance();
        Date publishedTime = calendar.getTime();
        Editorial editorial = new Editorial("editorial");
        BookDtoRequest updatedBookdto = new BookDtoRequest(Optional.of("title"), Optional.of("author"), Optional.of(publishedTime), Optional.of(100), Optional.of("description"), Optional.of(editorial));
        BookDto bookDto = new BookDto(longId,"title", "author", publishedTime, 100, "description", editorial);
        when(bookServices.updateBook(longId, updatedBookdto)).thenReturn(bookDto);

        mockMvc.perform(patch("/books/update/{id}", longId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(updatedBookdto)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title"));


    }

    @Test
    public void deleteBookControllerTest() throws Exception{
        var longId = 1l;
        doNothing().when(bookServices).deleteBook(longId);
        mockMvc.perform(delete("/books/delete/{id}",longId)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    }

}