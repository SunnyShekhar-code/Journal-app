package net.learningspringboot.JournalApp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import net.learningspringboot.JournalApp.repository.JournalEntryRepository;
import net.learningspringboot.JournalApp.entity.JournalEntity;
import net.learningspringboot.JournalApp.entity.User;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntity journalEntity, String userName) {
        try{
            User user = userService.findByUserName(userName);
            journalEntity.setDate(LocalDateTime.now());
            JournalEntity saved = journalEntryRepository.save(journalEntity);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("something went wrong during saving",e);
        }
        
    }

    public void saveEntry(JournalEntity journalEntity) {
        journalEntryRepository.save(journalEntity);
    }

    public List<JournalEntity> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed=false;
        try{
            User user = userService.findByUserName(userName);
            removed= user.getJournalEntries().removeIf(j -> j.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while deleting the entry",e);
        }
        return removed;
    }

    public List<JournalEntity> findByUserName(String userName){
        List<JournalEntity> li=new ArrayList<>();
        return li;
    }

}
