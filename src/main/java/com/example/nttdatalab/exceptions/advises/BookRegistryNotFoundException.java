package com.example.nttdatalab.exceptions.advises;

public class BookRegistryNotFoundException extends RuntimeException{
    public BookRegistryNotFoundException(Long id){
        super("The BookRegistry with ID " + id + " was not found");
    }

}
