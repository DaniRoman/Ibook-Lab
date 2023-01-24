package com.example.nttdatalab.utils;

import com.example.nttdatalab.collection.BookRegistry;
import com.example.nttdatalab.dto.BookRegistryDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@RequiredArgsConstructor
@Component
public class CustomLogger {

    @Autowired
    MongoTemplate mongoTemplate;

    public BookRegistryDto info(String message) {
        BookRegistry bookRegistry = new BookRegistry();
        bookRegistry.setMessage(message);
        bookRegistry.setDate(new Date());
        //mongoTemplate.insert(bookRegistry);

        BookRegistryDto bookRegistryDto = new BookRegistryDto(bookRegistry);

        return bookRegistryDto;
    }
}
