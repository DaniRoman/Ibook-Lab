package com.example.nttdatalab.services.impServices;

import com.example.nttdatalab.dto.BookDto;
import com.example.nttdatalab.dto.BookDtoRequest;
import com.example.nttdatalab.dto.EditorialDto;
import com.example.nttdatalab.models.Editorial;

import java.util.List;

public interface IEditoralService {
    public List<EditorialDto> findAll();
    public EditorialDto findById(Long id);
    public EditorialDto findByName(String name);
    public EditorialDto saveEditorial(EditorialDto EditorialDto);
    public EditorialDto updateEditorial(Long id, EditorialDto editorialDto);
    public void deleteEditorial(Long id);
}
