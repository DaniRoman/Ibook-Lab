package com.example.nttdatalab.dto;

import com.example.nttdatalab.models.Editorial;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDtoRequest {
    private Optional<String> title;
    private Optional<String> author;
    private Optional<Date> publish;
    private Optional<Integer> pages;
    private Optional <String> description;

    private Optional<Editorial> editorial;

}
