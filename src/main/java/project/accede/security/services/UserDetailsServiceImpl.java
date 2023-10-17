package project.accede.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.accede.dto.user.query.UserDTO;
import project.accede.entities.Role;
import project.accede.entities.User;
import project.accede.map.UserMapper;
import project.accede.services.UserService;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO userTemp = userService.getUserByUsername(username);
        User userOptional = userMapper.UserDTOtoUser(userTemp);
        if(userOptional == null){
            return null;
        }

        Set<Role> roles = userOptional.getRoles();
        List<GrantedAuthority> authorities = roles.stream().map(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
            return authority;
        }).toList();

        org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(
                userOptional.getUsername(),
                userOptional.getPassword(),
                authorities);
        return user;
    }
}
