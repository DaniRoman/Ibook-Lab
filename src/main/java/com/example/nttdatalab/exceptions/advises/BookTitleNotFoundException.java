package com.example.nttdatalab.exceptions.advises;

public class BookTitleNotFoundException extends RuntimeException{

    public BookTitleNotFoundException(String title){
        super("The Book with title " + title + " was not found");
    }
}
