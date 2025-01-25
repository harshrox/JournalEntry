package com.springboot.journalEntry.config;

import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// Helps to create a separate context for all those methods which use @Transactional annotation
@EnableTransactionManagement
public class TransactionConfig {
    public PlatformTransactionManager ptm(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }
}
