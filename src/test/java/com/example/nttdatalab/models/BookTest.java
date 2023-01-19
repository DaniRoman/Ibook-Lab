package com.example.nttdatalab.models;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void setPublish() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, 01, 19);
        Date beforeDate = calendar.getTime();

        Book book = new Book("test", "test", beforeDate, 100, "test", new Editorial("test"));
        System.out.println("pARA");
    }
}