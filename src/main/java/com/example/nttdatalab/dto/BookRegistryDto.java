package com.example.nttdatalab.dto;

import com.example.nttdatalab.collection.BookRegistry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRegistryDto {

    private String id;
    private String message;
    private Date date;

    public BookRegistryDto (BookRegistry bookRegistry){
        id = bookRegistry.getId();
        message = bookRegistry.getMessage();
        date = bookRegistry.getDate();
    }

}
