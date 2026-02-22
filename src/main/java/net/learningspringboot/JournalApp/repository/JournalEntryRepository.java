package net.learningspringboot.JournalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import net.learningspringboot.JournalApp.entity.JournalEntity;

@Repository
public interface JournalEntryRepository
        extends MongoRepository<JournalEntity, ObjectId> {
}

// controller --> service --> repository