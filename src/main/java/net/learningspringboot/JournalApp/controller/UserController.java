package net.learningspringboot.JournalApp.controller;

// import java.time.LocalDateTime;
// import java.util.List;

// import org.bson.types.ObjectId;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.bind.annotation.*;
// import net.learningspringboot.JournalApp.entity.User;
// import net.learningspringboot.JournalApp.repository.UserRepository;
// import net.learningspringboot.JournalApp.service.UserService;

// @RestController
// @RequestMapping("/user")
// public class UserController {
//     @Autowired
//     private UserService userService;

//     @Autowired
//     private UserRepository userRepository;
    

 
//     @DeleteMapping("/user")
//     public ResponseEntity<?> deleteUserByName(){
//         Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//         userRepository.deleteByUserName(authentication.getName());
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     }

//     @PutMapping
//     public ResponseEntity<?> udateUser(@RequestBody User obj){
//         Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//         String username= authentication.getName();
//         User userInDb= userService.findByUserName(username);

//             userInDb.setUserName(obj.getUserName());
//             userInDb.setPassword(obj.getPassword());
//             userService.saveEntry(userInDb);

//         userService.saveEntry(userInDb);
//         return new ResponseEntity<>(userInDb,HttpStatus.CREATED);
//   }
// }

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.learningspringboot.JournalApp.entity.User;
import net.learningspringboot.JournalApp.repository.UserRepository;
import net.learningspringboot.JournalApp.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getUser(){
        Authentication authhentication = SecurityContextHolder.getContext().getAuthentication();
        User user=userRepository.findByUserName(authhentication.getName());
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserByName() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        userRepository.deleteByUserName(authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User obj) {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();
        User userInDb = userService.findByUserName(username);
            userInDb.setUserName(obj.getUserName());
            userInDb.setPassword(obj.getPassword());

        userService.saveNewUser(userInDb);
        return ResponseEntity.status(HttpStatus.CREATED).body(userInDb);
    }
}

