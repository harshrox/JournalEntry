package com.springboot.journalEntry.service;

import com.springboot.journalEntry.model.JournalModel;
import com.springboot.journalEntry.model.UserModel;
import com.springboot.journalEntry.repository.JournalRepository;
import com.springboot.journalEntry.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveEntry(UserModel entry){
        userRepository.save(entry);
    }

    public List<UserModel> getEntries(){
        return userRepository.findAll();
    }

    public Optional<UserModel> getEntryById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public UserModel findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
