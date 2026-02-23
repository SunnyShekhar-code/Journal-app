// package net.learningspringboot.JournalApp.service;

// import static org.mockito.Mockito.when;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.ArgumentMatcher;
// import org.mockito.ArgumentMatchers;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.test.context.bean.override.mockito.MockitoBean;

// import com.mongodb.assertions.Assertions;

// import net.learningspringboot.JournalApp.entity.User;
// import net.learningspringboot.JournalApp.repository.UserRepository;

// @SpringBootTest
// public class UserDetailServiceImplTests {


//     @InjectMocks
//     private UserDetailsServiceImpl userDetailsService;

//     @Mock
//     private UserRepository userRepository;

//     @BeforeEach
//     void setUp(){
//         MockitoAnnotations.initMocks(this);
//     }



//     @Test
//     public void loadUserByUsernametest(){
//         when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram")).build();
//         UserDetails user=userDetailsService.loadUserByUsername("ram");
//         Assertions.assertNotNull(user);
//     }
    
// }
