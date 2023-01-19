package com.example.nttdatalab.repositories;

import com.example.nttdatalab.models.Book;
import com.example.nttdatalab.models.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditorialRepository extends JpaRepository<Editorial, Long> {

     Optional<Editorial> findEditorialByName(String name);
}
