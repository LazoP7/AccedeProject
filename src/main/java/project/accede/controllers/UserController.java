package project.accede.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.accede.dto.role.RoleDTO;
import project.accede.dto.role.UserRoleRequestDTO;
import project.accede.dto.user.query.UserDTO;
import project.accede.entities.Role;
import project.accede.services.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("createUser")
    public ResponseEntity<Void> createUser(@RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value="{id}")
    public ResponseEntity<UserDTO> getUserById (@PathVariable Integer id) {
        UserDTO userDTO = userService.getUserById (id);
        if (userDTO==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping(value = "search-by-username")
    public ResponseEntity<UserDTO> getUserByUsername (@RequestParam String username ) {
        UserDTO userDTO = userService.getUserByUsername(username);
        if (userDTO==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PutMapping("roles")
    public void setUserRoles (@RequestBody UserRoleRequestDTO requestDTO){
        Set<RoleDTO> rolesDTO = new HashSet<>(requestDTO.getRoles());
        userService.setRoles(requestDTO.getUserId(), rolesDTO);
    }

}
