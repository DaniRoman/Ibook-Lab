package com.example.nttdatalab.exceptions.advises;

public class EditorialNotFoundException extends RuntimeException{
    public EditorialNotFoundException(Long id){
        super("The Editorial with ID " + id + " was not found");
    }

}
