package com.example.nttdatalab.controllers;

import com.example.nttdatalab.dto.BookDto;
import com.example.nttdatalab.dto.BookDtoRequest;
import com.example.nttdatalab.services.BookServices;
import com.example.nttdatalab.utils.CustomLogger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookServices bookServices;
    private final CustomLogger customLogger;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestBody BookDto bookDtoParameter){
        BookDto  bookDtoParameterw = bookDtoParameter;

        BookDto bookDto = bookServices.saveBook(bookDtoParameter);

        Link selfLink = linkTo(methodOn(BookController.class).getBookById(bookDto.getId())).withSelfRel();
        bookDto.add(selfLink);

        Link linkToEditorial = linkTo(methodOn(EditorialController.class)
                .readEditorialById(bookDto.getEditorial().getId()))
                .withSelfRel();

        bookDto.add(linkToEditorial);
        //log.info(customLogger.info("Book " + bookDto.getTitle() + "has been created" ));
        return bookDto;
    }
    @GetMapping
    public List<BookDto> getBooks(){

        List<BookDto> bookDtoList = bookServices.findAll();

        for (final BookDto bookDto : bookDtoList) {
            Link selfLink = linkTo(methodOn(BookController.class)
                    .getBookById(bookDto.getId())).withSelfRel();
            bookDto.add(selfLink);
        }

        for (final BookDto bookDto : bookDtoList) {
            Link selfLink = linkTo(methodOn(EditorialController.class)
                    .readEditorialById(bookDto.getEditorial().getId())).withSelfRel();
            bookDto.add(selfLink);
        }

        //log.info(customLogger.info("All Books has been showed"));
        return bookDtoList;
    }

    @GetMapping("/id/{id}")
    public BookDto getBookById(@PathVariable("id")Long id){


        BookDto bookDto = bookServices.findById(id);

        Link selfLink = linkTo(BookController.class).slash(bookDto.getId()).withSelfRel();
        bookDto.add(selfLink);

        //log.info(customLogger.info("Book" + bookDto.getTitle() + " with id " + bookDto.getId() + " search by id has been done" ));
        return bookDto;

    }

    @GetMapping("/title/{title}")
    public BookDto getBookByTitle(@PathVariable("title") String title){

        BookDto bookDto = bookServices.findByTitle(title);

        Link selfLink = linkTo(BookController.class).slash(bookDto.getTitle()).withSelfRel();
        bookDto.add(selfLink);

        //log.info(customLogger.info("Book" + bookDto.getTitle() +" search by name has been done" ));
        return bookDto;
    }

    @GetMapping( "/editorial/{editorial}")
    public List<BookDto> getBookByEditorial(@PathVariable("editorial") String editorial){

        List<BookDto> bookDtoList = bookServices.findByEditorial(editorial);

        String str = "search books by editorial" + editorial + "produced the following result of books ";
        for (final BookDto bookDto : bookDtoList) {

            str += bookDto.getTitle();

            Link selfLink = linkTo(methodOn(BookController.class)
                    .getBookByEditorial(bookDto.getEditorial().getName())).withSelfRel();
            bookDto.add(selfLink);
        }

        //log.info(customLogger.info(str));

        return bookDtoList ;

    }

    @PatchMapping("/update/{id}")
    public BookDto updateBook(@PathVariable ("id") Long id, @RequestBody BookDtoRequest bookDtoRequest){

        BookDto bookDto = bookServices.updateBook(id,bookDtoRequest);
        Link selLink = linkTo(methodOn(BookController.class).getBookById(bookDto.getId())).withSelfRel();
        bookDto.add(selLink);

        //log.info(customLogger.info("Book " + bookDto.getTitle() + "has been updated"));
        return bookDto;

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable ("id")Long id){
        //log.info(customLogger.info("Book with id " + id + "has been deleted"));
        bookServices.deleteBook(id);
    }


}
