package com.example.nttdatalab.services.impServices;

import com.example.nttdatalab.dto.BookRegistryDto;

import java.util.List;

public interface IBookRegistryService {
    public BookRegistryDto save(BookRegistryDto bookRegistryDto);
    public List<BookRegistryDto> getAll();
}
