package com.springboot.journalEntry.service;

import com.springboot.journalEntry.model.JournalModel;
import com.springboot.journalEntry.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    public void saveEntry(JournalModel entry){
        journalRepository.save(entry);
    }

    public List<JournalModel> getEntries(){
        return journalRepository.findAll();
    }

    public Optional<JournalModel> getEntryById(Long id){
        return journalRepository.findById(id);
    }

    public void deleteById(Long id){
        journalRepository.deleteById(id);
    }
}
