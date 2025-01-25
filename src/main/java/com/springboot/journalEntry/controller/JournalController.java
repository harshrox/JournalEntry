package com.springboot.journalEntry.controller;

import com.springboot.journalEntry.model.JournalModel;
import com.springboot.journalEntry.model.UserModel;
import com.springboot.journalEntry.service.JournalService;
import com.springboot.journalEntry.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<List<JournalModel>> fetchEntries(@PathVariable String username){
        UserModel user = userService.findByUsername(username);
        List<JournalModel> allEntries = user.getJournalEntries();
        if(allEntries != null && !allEntries.isEmpty()){
            return new ResponseEntity<>(allEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{username}")
    public ResponseEntity<String> createEntry(@RequestBody JournalModel entry, @PathVariable String username){
        try{
            journalService.saveEntry(entry, username);
            return new ResponseEntity<>("Data saved.", HttpStatus.OK);
        }
        catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>("Data already present.", HttpStatus.ALREADY_REPORTED);
        }

    }

    @GetMapping("id/{id}")
    public ResponseEntity<JournalModel> fetchEntryById(@PathVariable ObjectId id){
        JournalModel journalModel = journalService.getEntryById(id).orElse(null);
        if(journalModel != null){
            return new ResponseEntity<>(journalModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{username}/{id}")
    public ResponseEntity<String> deleteEntryById(@PathVariable ObjectId id, @PathVariable String username){
        JournalModel doesExist = journalService.getEntryById(id).orElse(null);
        if(doesExist == null){
            return new ResponseEntity<>("Data does not exist.", HttpStatus.NOT_FOUND);
        }
        else{
            journalService.deleteById(id, username);
            return new ResponseEntity<>("Data deleted.", HttpStatus.OK);
        }

    }

    @PutMapping("id/{username}/{id}")
    public ResponseEntity<String> updateEntry(
            @PathVariable ObjectId id,
            @RequestBody JournalModel newEntry,
            @PathVariable String username
    ){
        JournalModel oldEntry = journalService.getEntryById(id).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
            journalService.saveEntry(oldEntry);
            return new ResponseEntity<>("Data updated.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Data does not exist.", HttpStatus.NOT_MODIFIED);

    }

}
