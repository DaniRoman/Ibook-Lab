package com.example.nttdatalab.dto;

import com.example.nttdatalab.models.Book;
import com.example.nttdatalab.models.Editorial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto extends RepresentationModel<BookDto> {

    private Long id;
    private String title;
    private String author;
    private Date publish;
    private Integer pages;
    private String description;
    private Editorial editorial;

    public BookDto(Book book) {
        id = book.getId();
        title = book.getTitle();
        author = book.getAuthor();
        publish = book.getPublish();
        pages = book.getPages();
        description = book.getDescription();
        editorial = book.getEditorial();
    }

    public BookDto(String title, String author, Date publish, Integer pages, String description, Editorial editorial) {
        this.title = title;
        this.author = author;
        this.publish = publish;
        this.pages = pages;
        this.description = description;
        this.editorial = editorial;
    }
}
