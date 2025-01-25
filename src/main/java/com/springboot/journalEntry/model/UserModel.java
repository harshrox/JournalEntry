package com.springboot.journalEntry.model;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
public class UserModel {

    @Id
    private ObjectId id;
    @Indexed(unique = true)  // To create auto indexing -> spring.data.mongodb.auto-index-creation=true
    @NonNull
    private String username;
    @NonNull
    private String password;

    @DBRef      // To link users collection with journal_entries collection
    private List<JournalModel> journalEntries = new ArrayList<>();

}

// When you index a field in a database, you're instructing the database to create a special data structure (an index) that speeds up searches on that field.