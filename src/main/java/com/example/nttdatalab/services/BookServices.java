package com.example.nttdatalab.services;


import com.example.nttdatalab.dto.BookDto;
import com.example.nttdatalab.dto.BookDtoRequest;
import com.example.nttdatalab.dto.BookRegistryDto;
import com.example.nttdatalab.exceptions.advises.BookNotFoundException;
import com.example.nttdatalab.exceptions.advises.BookTitleNotFoundException;
import com.example.nttdatalab.models.Book;
import com.example.nttdatalab.repositories.BookRepository;
import com.example.nttdatalab.services.impServices.IBookService;
import com.example.nttdatalab.utils.CustomLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServices implements IBookService {

    private final BookRepository bookRepository;
    /*
    @Autowired
    private CustomLogger customLogger;*/


    public BookDto saveBook(BookDto bookDto){

        Book bookToDb = new Book(bookDto);

        Book book = bookRepository.save(bookToDb);

        BookDto bookDtoResponse = new BookDto(book);

        //BookRegistryDto bookRegistryDto = customLogger.info("Book " + bookDto.getTitle() + "has been created");
        //sentLogService.publish(bookRegistryDto);

        return bookDtoResponse;

    }
    public List<BookDto> findAll(){

        List<Book> bookList = bookRepository.findAll();
        List<BookDto> bookDtoList = new ArrayList<BookDto>();
        bookList.stream().forEach(book -> bookDtoList.add(new BookDto(book)));
        return bookDtoList;
    }

    public BookDto findById(Long id){
        Book book = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        BookDto bookDto = new BookDto(book);
        return bookDto;
    }

    public BookDto findByTitle(String title){
        Book book = bookRepository.findBookByTitle(title).orElseThrow(()->new BookTitleNotFoundException(title));
        BookDto bookDto = new BookDto(book);
        return bookDto;
    }

    public List<BookDto> findByEditorial(String name){
        List<BookDto> bookDtoList = new ArrayList<>();
        List<Book> bookList = bookRepository.findBookByEditorialName(name);
        bookList.stream().forEach(book -> bookDtoList.add(new BookDto(book)));
        return bookDtoList;
    }


    public BookDto updateBook(Long id, BookDtoRequest bookDtoRequest){

        Book bookToUpdate = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));

        if(bookDtoRequest.getTitle().isPresent()){
            bookToUpdate.setTitle(bookDtoRequest.getTitle().get());
        }
        else if (bookDtoRequest.getAuthor().isPresent()){
            bookToUpdate.setAuthor(bookDtoRequest.getAuthor().get());
        }
        else if(bookDtoRequest.getPublish().isPresent()){
            bookToUpdate.setPublish(bookDtoRequest.getPublish().get());
        }
        else if (bookDtoRequest.getPages().isPresent()){
            bookToUpdate.setPages(bookDtoRequest.getPages().get());
        }
        else if (bookDtoRequest.getDescription().isPresent()){
            bookToUpdate.setDescription(bookDtoRequest.getDescription().get());
        }
        else if (bookDtoRequest.getEditorial().isPresent()){
            bookToUpdate.setEditorial(bookDtoRequest.getEditorial().get());
        }
        Book savedBook = bookRepository.save(bookToUpdate);
        BookDto bookDto = new BookDto(savedBook);

        return bookDto;

    }

    public void deleteBook(Long id){
        Book bookToDel = bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        bookRepository.deleteById(id);
    }
}
