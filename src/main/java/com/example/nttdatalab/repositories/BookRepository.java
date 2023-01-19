package com.example.nttdatalab.repositories;

import com.example.nttdatalab.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByTitle(String title);
    @Query("FROM Book b INNER JOIN  Editorial e ON b.editorial.name =  :name")
    List<Book> findBookByEditorialName(
            @Param("name") String name);



}
