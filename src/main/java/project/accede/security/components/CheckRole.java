package project.accede.security.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.accede.entities.User;
import project.accede.repositories.UserRepository;

@Component
public class CheckRole {

    @Autowired
    UserRepository userRepository;
    public boolean isAdmin(Integer id){
        User user = userRepository.getReferenceById(id);
        return user.getRoles().contains("ROLE_ADMIN");
    }

    public boolean isOwner(Integer id){
        User user = userRepository.getReferenceById(id);
        return user.getRoles().contains("ROLE_OWNER");
    }

    public boolean isPlayer(Integer id){
        User user = userRepository.getReferenceById(id);
        return user.getRoles().contains("ROLE_PLAYER");
    }
}
