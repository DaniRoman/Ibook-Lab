package com.example.nttdatalab.exceptions.advises;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id){
        super("The Book with ID " + id + " was not found");
    }

}
