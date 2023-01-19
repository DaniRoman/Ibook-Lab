package com.example.nttdatalab.exceptions.advises;

public class EditorialTitleNotFoundException extends RuntimeException{

    public EditorialTitleNotFoundException(String name){
        super("The Editorial with title " + name + " was not found");
    }
}
