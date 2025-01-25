package com.springboot.journalEntry.repository;

import com.springboot.journalEntry.model.JournalModel;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalRepository extends MongoRepository<JournalModel, ObjectId> {
}
