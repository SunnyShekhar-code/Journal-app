package net.learningspringboot.JournalApp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.learningspringboot.JournalApp.entity.User;
import net.learningspringboot.JournalApp.repository.UserRepository;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserRepository userRepository;

    // @Disabled
    // @BeforeEach
    // @BeforeAll
    @Test
    public void testFindUserbyname(){
        User user= userRepository.findByUserName("ram");
        assertTrue(!user.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "1,1,2",
        "3,6,9",
        "7,2,9"
    })
    public void testadd(int a, int b,int expected){
        assertEquals(expected, a+b);
    }

    
}
