package com.example.nttdatalab.controllers;

import com.example.nttdatalab.dto.BookDto;
import com.example.nttdatalab.dto.EditorialDto;
import com.example.nttdatalab.exceptions.advises.EditorialNotFoundException;
import com.example.nttdatalab.exceptions.advises.EditorialTitleNotFoundException;
import com.example.nttdatalab.models.Book;
import com.example.nttdatalab.models.Editorial;
import com.example.nttdatalab.services.BookServices;
import com.example.nttdatalab.services.EditoralServices;
import com.example.nttdatalab.services.impServices.IEditoralService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EditorialController.class)
class EditorialControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IEditoralService editoralService;

    @Autowired
    ObjectMapper om;

    @Test
    void createNewEditorialTest() throws Exception {

        Editorial editorial = new Editorial("Test");

        Calendar calendar = Calendar.getInstance();
        Date publishedTime = calendar.getTime();

        List<Book> bookList = List.of(
                new Book(1l, "IT", "Dark", publishedTime, 300, "Fear", editorial),
                new Book(2l, "2", "2", publishedTime, 2, "2", editorial)
        );

        EditorialDto editorialDto = new EditorialDto("EditorialName", bookList);

        when(editoralService.saveEditorial(editorialDto)).thenReturn(editorialDto);
        mockMvc.perform(post("/editorials/save")
                        .contentType(MediaType.APPLICATION_JSON)

                       .content(om.writeValueAsString(editorialDto)))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("EditorialName"));
    }

    @Test
    public void readAllEditorialTest() throws Exception {

        mockMvc.perform(get("/editorials")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //.andExpect(MockMvcResultMatchers.jsonPath("$.*", hasSize(2)));

    }
    @Test
    public void readEditorialByIdControllerTest() throws Exception {

        mockMvc.perform(get("/editorials/id/{id}",1l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    public void trhowExeptionWhenIdNotFoundTest() throws Exception {
        Long longId = 1l;
        doThrow(EditorialNotFoundException.class).when(editoralService).findById(anyLong());
        mockMvc.perform(get("/editorials/id/{id}",1l)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void readEditorialByNameTest() throws Exception {
        String name = "";

        mockMvc.perform(get("/editorials/name/{name}","juan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //.andExpect(MockMvcResultMatchers.jsonPath("$.title").value(""));

    }

    @Test
    public void trhowExeptionWhenNameNotFoundTest() throws Exception {
        doThrow(EditorialTitleNotFoundException.class).when(editoralService).findByName(anyString());
        mockMvc.perform(get("/editorials/name/{name}","juan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void updateEditorialControllerTest() throws Exception {
        Long longId = 1l;
        EditorialDto updatedEditorialdto = new EditorialDto();
        mockMvc.perform(put("/editorials/update/{id}", longId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(updatedEditorialdto)))
                .andExpect(status().isCreated());
        //.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("title"));

    }

    @Test
    public void deleteEditorialTest() throws Exception{
        var longId = 1l;
        doNothing().when(editoralService).deleteEditorial(longId);
        mockMvc.perform(delete("/editorials/delete/{id}",longId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}