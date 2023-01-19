package com.example.nttdatalab.services;

import com.example.nttdatalab.dto.EditorialDto;
import com.example.nttdatalab.models.Editorial;
import com.example.nttdatalab.repositories.EditorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EditoralServicesTest {

    @Autowired
    EditorialRepository editorialRepository;
    @Test
    void saveEditorial() {

        Editorial editorial = new Editorial("Hola");

        Editorial editorialDb = editorialRepository.save(editorial);

        EditorialDto editorialDtoResponse = new EditorialDto(editorialDb);
        System.out.println("");
        //return editorialDtoResponse;

    }
}