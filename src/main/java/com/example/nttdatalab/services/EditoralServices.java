package com.example.nttdatalab.services;


import com.example.nttdatalab.dto.BookDto;
import com.example.nttdatalab.dto.BookDtoRequest;
import com.example.nttdatalab.dto.BookRegistryDto;
import com.example.nttdatalab.dto.EditorialDto;
import com.example.nttdatalab.exceptions.advises.BookNotFoundException;
import com.example.nttdatalab.exceptions.advises.BookTitleNotFoundException;
import com.example.nttdatalab.exceptions.advises.EditorialNotFoundException;
import com.example.nttdatalab.exceptions.advises.EditorialTitleNotFoundException;
import com.example.nttdatalab.models.Book;
import com.example.nttdatalab.models.Editorial;
import com.example.nttdatalab.repositories.BookRepository;
import com.example.nttdatalab.repositories.EditorialRepository;
import com.example.nttdatalab.services.impServices.IBookService;
import com.example.nttdatalab.services.impServices.IEditoralService;
import com.example.nttdatalab.utils.CustomLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EditoralServices implements IEditoralService {

    private final EditorialRepository editorialRepository;
    @Autowired
    CustomLogger customLogger;

    private final KafkaTemplate<String, BookRegistryDto> kafkaTemplate;
    @Value("${kafka.topic.name}")
    private String topicRegistry;


    @Override
    public List<EditorialDto> findAll() {
        List<Editorial>  editorialList = editorialRepository.findAll();
        List<EditorialDto> editorialDtoList = new ArrayList<EditorialDto>();
        editorialList.stream().forEach(editorial -> editorialDtoList.add(new EditorialDto(editorial)));
        return editorialDtoList;
    }

    @Override
    public EditorialDto findById(Long id) {
        Editorial editorial = editorialRepository.findById(id).orElseThrow(()-> new EditorialNotFoundException(id));
        EditorialDto editorialDto = new EditorialDto(editorial);
        return editorialDto;
    }

    @Override
    public EditorialDto findByName(String name) {
        Editorial editorial = editorialRepository.findEditorialByName(name).orElseThrow(()-> new EditorialTitleNotFoundException(name));
        EditorialDto editorialDto = new EditorialDto(editorial);
        return editorialDto;
    }

    @Override
    public EditorialDto saveEditorial(EditorialDto editorialDto) {

        Editorial editorial = new Editorial(editorialDto);

        Editorial editorialDb = editorialRepository.save(editorial);

        EditorialDto editorialDtoResponse = new EditorialDto(editorialDb);

        BookRegistryDto bookRegistryDto = customLogger.info("Editorial " + editorialDto.getName() + "has been created" );
        kafkaTemplate.send(topicRegistry, bookRegistryDto);

        return editorialDtoResponse;
    }

    @Override
    public EditorialDto updateEditorial(Long id, EditorialDto editorialDto) {

        Editorial editorial = editorialRepository.findById(id).orElseThrow(()-> new EditorialNotFoundException(id));
        editorial.setName(editorialDto.getName());
        editorial.setBooks(editorialDto.getBooks());
        editorialRepository.save(editorial);
        EditorialDto responseEditorialDto = new EditorialDto(editorial);
        return responseEditorialDto;
    }

    @Override
    public void deleteEditorial(Long id) {
        editorialRepository.deleteById(id);
    }
}
