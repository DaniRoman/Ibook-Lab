package com.example.nttdatalab.controllers;

import com.example.nttdatalab.dto.EditorialDto;
import com.example.nttdatalab.services.impServices.IEditoralService;
import com.example.nttdatalab.utils.CustomLogger;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/editorials")
public class EditorialController {

    private final IEditoralService editoralService;
    @Autowired
    CustomLogger customLogger;


    @PostMapping(value = "/save", produces = { "application/hal+json" })
    @ResponseStatus(HttpStatus.CREATED)
    public EditorialDto createNewEditorial(@RequestBody EditorialDto editorialDto){

        EditorialDto editorial = editoralService.saveEditorial(editorialDto);
        Link selfLink = linkTo(methodOn(EditorialController.class).readEditorialById(editorial.getId())).withSelfRel();
        editorial.add(selfLink);

        log.info(customLogger.info("Editorial " + editorialDto.getName() + "has been created" ));
        return editorial;
    };

    @GetMapping
    public CollectionModel<EditorialDto> readEditorials(){

      List<EditorialDto> editorialDtosList = editoralService.findAll();

        for (final EditorialDto editorialDto : editorialDtosList) {
            Link selfLink = linkTo(methodOn(EditorialController.class)
                    .readEditorialById(editorialDto.getId())).withSelfRel();
            editorialDto.add(selfLink);
        }
        Link link = linkTo(methodOn(EditorialController.class).readEditorials()).withSelfRel();
        CollectionModel<EditorialDto> result = CollectionModel.of(editorialDtosList, link);

        log.info(customLogger.info("All Editorials has been showed"));
        return result;
    };

    @GetMapping("/id/{id}")
    public EditorialDto readEditorialById(@PathVariable("id") Long id){

        EditorialDto editorialDto = editoralService.findById(id);

        Link selfLink = linkTo(EditorialController.class).slash(editorialDto.getId()).withSelfRel();
        editorialDto.add(selfLink);

        log.info(customLogger.info("Editorial" + editorialDto.getName() + " with id " + editorialDto.getId() + " search by id has been done" ));

        return editorialDto;
    };

    @GetMapping("/name/{name}")
    public EditorialDto readEditorialByName(@PathVariable("name") String name){

        EditorialDto editorialDto = editoralService.findByName(name);
        Link selLink = linkTo(EditorialController.class).slash(editorialDto.getName()).withSelfRel();
        editorialDto.add(selLink);

        log.info(customLogger.info("Editorial" + editorialDto.getName() +" search by name has been done" ));
        return editorialDto;
    };

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public EditorialDto updateEditorial(@PathVariable("id") Long id, @RequestBody EditorialDto editorialDto){

        EditorialDto editorialDto2 = editoralService.updateEditorial(id, editorialDto);
        Link selLink = linkTo(methodOn(EditorialController.class).readEditorialById(editorialDto2.getId())).withSelfRel();
        editorialDto2.add(selLink);

        log.info(customLogger.info("Book " + editorialDto2.getName() + "has been updated"));
        return editorialDto2;

    };

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEditorial(@PathVariable("id") Long id){

        editoralService.deleteEditorial(id);
        log.info(customLogger.info("Editorial with id " + id + "has been deleted"));

    };







}
