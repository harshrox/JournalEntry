package com.springboot.journalEntry.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")  // MongoDB collection name
@Data  // For Getters and Setters using Lombok
@NoArgsConstructor  // Lombok annotation to generate a no-argument constructor
public class JournalModel {

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;


}
