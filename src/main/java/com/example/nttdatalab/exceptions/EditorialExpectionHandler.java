package com.example.nttdatalab.exceptions;

import com.example.nttdatalab.exceptions.advises.BookNotFoundException;
import com.example.nttdatalab.exceptions.advises.BookTitleNotFoundException;
import com.example.nttdatalab.exceptions.advises.EditorialNotFoundException;
import com.example.nttdatalab.exceptions.advises.EditorialTitleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EditorialExpectionHandler {

    @ExceptionHandler(EditorialNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String editorialNotFoundHandler( EditorialNotFoundException ex){
        return ex.getMessage();
    }

    @ExceptionHandler(EditorialTitleNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String editorialTitleNotFoundHandler( EditorialTitleNotFoundException ex){
        return ex.getMessage();
    }




}
