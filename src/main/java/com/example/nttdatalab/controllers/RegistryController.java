package com.example.nttdatalab.controllers;

import com.example.nttdatalab.dto.BookRegistryDto;
import com.example.nttdatalab.repositories.BookRegistryRepository;
import com.example.nttdatalab.services.BookRegistryService;
import com.mongodb.DBObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/registers")
public class RegistryController {

    @Autowired
    BookRegistryService bookRegistryService;
    @GetMapping
    public List<BookRegistryDto> readAllregisters(){
        List<BookRegistryDto> registerList = bookRegistryService.getAll();
        return registerList;
    }
}
