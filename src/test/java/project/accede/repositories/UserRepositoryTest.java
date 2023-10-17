package project.accede.repositories;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import project.accede.AccedeApplication;
import project.accede.entities.User;

import java.util.ArrayList;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = AccedeApplication.class)
public class UserRepositoryTest {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger( UserRepositoryTest.class );

    @Autowired
    UserRepository userRepository;

//    @Test
//    @Transactional
//    void createUserTest(){
//        User user = new User();
//        user.setId(1);
//        user.setName("Lazar");
//        user.setSurname("Pejovic");
//        user.setUsername("LazoP");
//        user.setMail("lazar.pejovic6@gmail.com");
//        user.setPassword("LogateAccede");
//        user.setReputation(0);
//        user.setAge(23);
//        user.setProfDescr("Lazar Pejovic profile description");
//        userRepository.save(user);
//        listUsers();
//    }
//
//    @Test
//    @Transactional
//    void listUsers(){
//        ArrayList<User> users = userRepository.getAllUsers();
//        for(User user : users){
//            logger.info(user.toString());
//        }
//    }
}
