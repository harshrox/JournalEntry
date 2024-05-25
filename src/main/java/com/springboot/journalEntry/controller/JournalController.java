package com.springboot.journalEntry.controller;

import com.springboot.journalEntry.model.JournalModel;
import com.springboot.journalEntry.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @GetMapping
    public List<JournalModel> fetchEntries(){
        return journalService.getEntries();
    }

    @PostMapping
    public String createEntry(@RequestBody JournalModel entry){
        JournalModel doesExist = journalService.getEntryById(entry.getId()).orElse(null);
        if(doesExist == null){
            entry.setDate(LocalDateTime.now());
            journalService.saveEntry(entry);
            return "Data saved.";
        }
        else{
            return "Data already present.";
        }

    }

    @GetMapping("id/{id}")
    public JournalModel fetchEntryById(@PathVariable Long id){
        return journalService.getEntryById(id).orElse(null);
    }

    @DeleteMapping("id/{id}")
    public String deleteEntryById(@PathVariable Long id){
        JournalModel doesExist = journalService.getEntryById(id).orElse(null);
        if(doesExist == null){
            return "Data does not exist.";
        }
        else{
            journalService.deleteById(id);
            return "Data deleted.";
        }

    }

    @PutMapping("id/{id}")
    public String updateEntry(@PathVariable Long id, @RequestBody JournalModel newEntry){
        JournalModel oldEntry = journalService.getEntryById(id).orElse(null);
        if(oldEntry != null){
            oldEntry.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : oldEntry.getContent());
            oldEntry.setDate(LocalDateTime.now());
            journalService.saveEntry(oldEntry);
            return "Data updated.";
        }
        else{
            return "Data does not exit.";
        }

    }

}
