package com.springboot.journalEntry.service;

import com.springboot.journalEntry.model.JournalModel;
import com.springboot.journalEntry.model.UserModel;
import com.springboot.journalEntry.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserService userService;

    // For Post Mapping
    @Transactional  //@Transactional ensures that the entire method runs within a transaction, rolling back on any exceptions.
    public void saveEntry(JournalModel entry, String username){
        try{
            entry.setDate(LocalDateTime.now());
            UserModel user = userService.findByUsername(username);
            JournalModel saved = journalRepository.save(entry);    // Saving Journal (saved has ID, entry doesn't)
            user.getJournalEntries().add(saved);                   // Adding Journal to User database
            userService.saveEntry(user);                           // Saving User
        }
        catch (Exception e){
            System.out.println("An error occurred while saving the data. Rolling back...");
        }
    }

    // For Put Mapping
    public void saveEntry(JournalModel entry){
        entry.setDate(LocalDateTime.now());
        journalRepository.save(entry);
    }

    // For Get Mapping
    public List<JournalModel> getEntries(){
        return journalRepository.findAll();
    }

    // For Get Mapping
    public Optional<JournalModel> getEntryById(ObjectId id){
        return journalRepository.findById(id);
    }

    // For Delete Mapping
    public void deleteById(ObjectId id, String username){
        UserModel user = userService.findByUsername(username);
        user.getJournalEntries().removeIf(journal -> journal.getId().equals(id));
        userService.saveEntry(user);
        journalRepository.deleteById(id);
    }
}
