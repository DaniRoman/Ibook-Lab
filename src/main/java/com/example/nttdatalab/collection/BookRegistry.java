package com.example.nttdatalab.collection;

import com.example.nttdatalab.dto.BookRegistryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class BookRegistry {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private String id;

    private String message;
    private Date date;

    public BookRegistry (BookRegistryDto bookRegistryDto){
        id = bookRegistryDto.getId();
        message = bookRegistryDto.getMessage();
        date = bookRegistryDto.getDate();
    }
    public BookRegistry(String message, Date date) {
        this.message = message;
        this.date = date;
    }
}
