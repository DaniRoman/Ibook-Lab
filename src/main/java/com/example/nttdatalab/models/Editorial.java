package com.example.nttdatalab.models;

import com.example.nttdatalab.dto.EditorialDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Editorial.TABLE_NAME)
public class Editorial {

    public static final String TABLE_NAME = "editorials";
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(nullable = false)

    private Long id;

    private String name;
    @OneToMany(mappedBy = "editorial")
    List<Book> books;

    public Editorial(String name){
        this.name = name;
    }

    public Editorial(EditorialDto editorialDto){
        id  = editorialDto.getId();
        name = editorialDto.getName();
        books = editorialDto.getBooks();
    }

}
