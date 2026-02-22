package net.learningspringboot.JournalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import net.learningspringboot.JournalApp.entity.User;

@Repository
public interface UserRepository
        extends MongoRepository<User, ObjectId> {
                User findByUserName(String username);
                void deleteByUserName(String username);
}

