package com.example.nttdatalab.dto;

import com.example.nttdatalab.models.Book;
import com.example.nttdatalab.models.Editorial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditorialDto extends RepresentationModel<EditorialDto> {

    private Long id;
    private String name;
    private List<Book> books;

    public EditorialDto(Editorial editorial){
        id = editorial.getId();
        name = editorial.getName();
        books = editorial.getBooks();
    }

    public EditorialDto(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public EditorialDto(String name){
        this.name = name;
    }
}
