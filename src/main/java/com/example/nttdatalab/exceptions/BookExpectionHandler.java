package com.example.nttdatalab.exceptions;

import com.example.nttdatalab.exceptions.advises.BookNotFoundException;
import com.example.nttdatalab.exceptions.advises.BookPublishDateException;
import com.example.nttdatalab.exceptions.advises.BookRegistryNotFoundException;
import com.example.nttdatalab.exceptions.advises.BookTitleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Optional;

@RestControllerAdvice
public class BookExpectionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String bookNotFoundHandler( BookNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(BookTitleNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String bookTitleNotFoundHandler( BookTitleNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(BookPublishDateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String bookPublishDateHandler( BookPublishDateException ex){ return ex.getMessage();}

    @ExceptionHandler(BookRegistryNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String bookRegistryDateHandler( BookRegistryNotFoundException ex){ return ex.getMessage();}

}
