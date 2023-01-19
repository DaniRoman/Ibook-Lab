package com.example.nttdatalab.controllers;

import com.example.nttdatalab.dto.EditorialDto;
import com.example.nttdatalab.exceptions.advises.EditorialNotFoundException;
import com.example.nttdatalab.exceptions.advises.EditorialTitleNotFoundException;
import com.example.nttdatalab.models.Book;
import com.example.nttdatalab.services.EditoralServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EditorialController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class EditorialControllerTestCopy {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EditoralServices editoralServices;

    @Autowired
    ObjectMapper om;

    @Test
    public void createEditorialControllerTest() throws Exception{

        List<Book> bookList = List.of(
                new Book("Book1"),
                new Book("Book2")
        );

        EditorialDto editorialDto = new EditorialDto(1l, "La moderna", bookList);

        when(editoralServices.saveEditorial(editorialDto)).thenReturn(editorialDto);

        mockMvc.perform(post("/editorials/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(editorialDto)))
                .andExpect(status().isCreated());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("La moderna"));

    }

    @Test
    public void readAllEditorialTest() throws Exception {

        mockMvc.perform(get("/editorials")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)));

    }
    @Test
    public void readEditorialByIdTest() throws Exception {

        mockMvc.perform(get("/editorials/id/{id}",1l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
          //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void trhowExeptionWhenIdNotFoundTest() throws Exception {
        Long longId = 1l;
        doThrow(EditorialNotFoundException.class).when(editoralServices).findById(anyLong());
        mockMvc.perform(get("/editorials/id/{id}",1l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void readEditorialByTitleControllerTest() throws Exception {
        String title = "";

        mockMvc.perform(get("/editorials/title/{title}",title)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.title").value(""));

    }

    @Test
    public void trhowExeptionWhenNameNotFoundTest() throws Exception {
        Long longId = 1l;
        doThrow(EditorialTitleNotFoundException.class).when(editoralServices).findByName(anyString());
        mockMvc.perform(get("/editorials/title/{title}",1l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updateEditorialControllerTest() throws Exception {
        Long longId = 1l;
        EditorialDto updatedEditorialdto = new EditorialDto();
        mockMvc.perform(patch("/edidtorials/update/{id}", longId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(updatedEditorialdto)))
                .andExpect(status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title"));

    }

    @Test
    public void deleteEditorialControllerTest() throws Exception{
        var longId = 1l;
        doNothing().when(editoralServices).deleteEditorial(longId);
        mockMvc.perform(delete("/editorials/delete/{id}",longId)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
    }

}