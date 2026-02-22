package net.learningspringboot.JournalApp.controller;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import net.learningspringboot.JournalApp.entity.JournalEntity;
import net.learningspringboot.JournalApp.entity.User;
import net.learningspringboot.JournalApp.service.JournalEntryService;
import net.learningspringboot.JournalApp.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntryOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user= userService.findByUserName(userName);
        List<JournalEntity> li= user.getJournalEntries();
        if(li !=null){
            return new ResponseEntity<>(li,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity obj){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        try{
            journalEntryService.saveEntry(obj,userName);
            return new ResponseEntity<>(obj, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntity> getJournalEntryById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User user=userService.findByUserName(userName);
        List<JournalEntity> collect= (List<JournalEntity>) user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
        Optional<JournalEntity> journalEntry=journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
      }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            boolean removed=journalEntryService.deleteById(myId,userName);
            if(removed){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<?> putJournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntity obj){
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String userName=authentication.getName();
         User user = userService.findByUserName(userName);
         List<JournalEntity> collect= user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
         if(!collect.isEmpty()){
            Optional<JournalEntity> journalEntry= journalEntryService.findById(myId);
            if(journalEntry.isPresent()){
                JournalEntity old=journalEntry.get();
                old.setTitle(obj.getTitle()!=null && !obj.getTitle().equals("")? obj.getTitle():old.getTitle());
                old.setContent(obj.getContent()!=null && !obj.getContent().equals("") ? obj.getContent() : old.getContent());
                journalEntryService.saveEntry(old);
                return new ResponseEntity<>(old,HttpStatus.CREATED);
            }
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        
    }
    
  }

  

