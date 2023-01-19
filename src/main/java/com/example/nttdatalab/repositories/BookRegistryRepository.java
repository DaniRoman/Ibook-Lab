package com.example.nttdatalab.repositories;

import com.example.nttdatalab.collection.BookRegistry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRegistryRepository extends MongoRepository<BookRegistry, String> {

}
