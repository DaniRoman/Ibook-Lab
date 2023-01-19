package com.example.nttdatalab.services;

import com.example.nttdatalab.collection.BookRegistry;
import com.example.nttdatalab.dto.BookRegistryDto;
import com.example.nttdatalab.repositories.BookRegistryRepository;
import com.example.nttdatalab.services.impServices.IBookRegistryService;
import com.mongodb.DBObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class BookRegistryService implements IBookRegistryService {
    @Autowired
    BookRegistryRepository bookRegistryRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public BookRegistryDto save(BookRegistryDto bookRegistryDto) {

        BookRegistry bookRegistry = new BookRegistry(bookRegistryDto);
        bookRegistryRepository.save(bookRegistry);
        return null;
    }
    @Override
    public List<BookRegistryDto> getAll() {
        List<BookRegistryDto> result = mongoTemplate.findAll(BookRegistryDto.class, "bookRegistry");
        return result;
    }
}
