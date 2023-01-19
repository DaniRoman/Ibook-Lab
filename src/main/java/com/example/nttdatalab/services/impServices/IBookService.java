package com.example.nttdatalab.services.impServices;

import com.example.nttdatalab.dto.BookDto;
import com.example.nttdatalab.dto.BookDtoRequest;
import com.example.nttdatalab.exceptions.advises.BookNotFoundException;
import com.example.nttdatalab.models.Book;
import com.example.nttdatalab.repositories.BookRepository;

import java.util.List;

public interface IBookService {
    public List<BookDto> findAll();
    public BookDto findById(Long id);
    public BookDto findByTitle(String title);
    public List<BookDto> findByEditorial(String editorial);
    public BookDto saveBook(BookDto bookDto);
    public BookDto updateBook(Long id, BookDtoRequest bookDtoRequest);
    public void deleteBook(Long id);
}
