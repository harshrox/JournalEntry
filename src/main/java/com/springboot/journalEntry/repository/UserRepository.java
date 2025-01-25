package com.springboot.journalEntry.repository;

import com.springboot.journalEntry.model.JournalModel;
import com.springboot.journalEntry.model.UserModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserModel, ObjectId> {
    /**
     * Automatically maps to a query finding UserModel by the 'username' field.
     * Spring Data generates the query based on the method name following the pattern "findBy".
     * Method naming convention: Camel Case
     */
    UserModel findByUsername(String username);
}
