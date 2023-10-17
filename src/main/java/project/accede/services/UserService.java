package project.accede.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.accede.dto.role.RoleDTO;
import project.accede.dto.user.query.UserDTO;
import project.accede.entities.Role;
import project.accede.entities.User;
import project.accede.map.UserMapper;
import project.accede.repositories.RoleRepository;
import project.accede.repositories.UserRepository;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleRepository roleRepository;

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(MatchService.class );
    public void createUser(UserDTO userDTO) {
        userDTO.setProfDescr("");
        userDTO.setReputation(0);
        User user = userMapper.UserDTOtoUser(userDTO);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPW = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPW);
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.getReferenceById(3);
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
    }

    public UserDTO getUserById(Integer id) {
        User user = userRepository.getReferenceById(id);
        return userMapper.UsertoUserDTO(user);
    }

    @Transactional
    public void setRoles(int userId, Set<RoleDTO> rolesDTO){
        Set<Role> roles2 = new HashSet<>();
        for(RoleDTO role : rolesDTO){
            logger.info(Integer.toString(role.getId()));
            roles2.add(roleRepository.getReferenceById(role.getId()));
        }
        User user = userRepository.getReferenceById(userId);
        user.setRoles(roles2);
        userRepository.save(user);
    }


    public UserDTO getUserByUsername(String username) {
        return userMapper.UsertoUserDTO(userRepository.findByUsername(username));
    }
}
