package com.example.nttdatalab.services;


import com.example.nttdatalab.dto.BookDto;
import com.example.nttdatalab.dto.BookDtoRequest;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EditoralServices implements IEditoralService {

    private final EditorialRepository editorialRepository;


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
    public EditorialDto saveEditorial(EditorialDto EditorialDto) {

        Editorial editorial = new Editorial(EditorialDto);

        Editorial editorialDb = editorialRepository.save(editorial);

        EditorialDto editorialDtoResponse = new EditorialDto(editorialDb);
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
