package com.example.nttdatalab.models;

import com.example.nttdatalab.dto.BookDto;
import com.example.nttdatalab.exceptions.advises.BookPublishDateException;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Book.TABLE_NAME)
public class Book {

    public static final String TABLE_NAME = "books";
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String title;
    private String author;
    private Date publish;
    private Integer pages;
    private String description;
    @ManyToOne
    @JoinColumn( name = "editorial_id")
    Editorial editorial;

    public Book(String title, String author, Date publish, Integer pages, String description, Editorial editorial) {
        this.title = title;
        this.author = author;
        setPublish(publish);
        this.pages = pages;
        this.description = description;
        this.editorial = editorial;
    }

    public Book(BookDto bookDto) {
        id = bookDto.getId();
        title = bookDto.getTitle();
        author = bookDto.getAuthor();
        setPublish(bookDto.getPublish());
        pages = bookDto.getPages();
        description = bookDto.getDescription();
        editorial = bookDto.getEditorial();
    }

    public Book(String title){
        this.title = title;
    }

    public void setPublish(Date publishDate) throws BookPublishDateException {

        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        var result = publishDate.compareTo(currentDate);
        if (result == 0) {
            //System.out.println("Date1 is equal to Date2");
            publish = publishDate;
        } else if (result > 0) {
            //System.out.println("Date1 is after Date2");
            throw new BookPublishDateException(publishDate);
        } else if (result < 0) {
            //System.out.println("Date1 is before Date2");
            publish = publishDate;
        }else {
            publish = currentDate;
        }

    }
}
