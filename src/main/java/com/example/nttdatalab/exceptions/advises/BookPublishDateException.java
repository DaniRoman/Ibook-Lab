package com.example.nttdatalab.exceptions.advises;

import java.util.Date;

public class BookPublishDateException extends RuntimeException{
    public BookPublishDateException(Date date){
        super("The publish date" + date + "can't be after current date");
    }

}
