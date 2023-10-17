package project.accede.security.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import project.accede.security.dto.JwtDTO;
import project.accede.security.dto.UserLogin;
import project.accede.security.dto.UserLogin2Fact;
import project.accede.security.jwt.JwtUtils;
import project.accede.security.ott.OTTUtility;
import project.accede.services.EmailService;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    OTTUtility ottUtility;

    @Autowired
    EmailService emailService;

    @PostMapping("signin")
    public ResponseEntity<JwtDTO> authenticateUser(@RequestBody UserLogin userLogin) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword());
        try {
            Authentication auth = authenticationManager.authenticate(authentication);
            JwtDTO token = jwtUtils.generateJwtToken(auth, userLogin.getRememberMe());

            log.info("Generisani token je : {}", token.getAccessToken());

            return  new ResponseEntity<>(token, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.error("Expection triggered: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("signin_2fact")
    public ResponseEntity<Void> authenticateUser2Fact(@RequestBody UserLogin userLogin) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword());
        try {
            Authentication auth = authenticationManager.authenticate(authentication);
            Integer ott = ottUtility.generateOtt(auth.getName());
            log.info("Generisani ott je : {}", ott);
            emailService.sendMessage(userLogin.getUsername(),"OTT code", ott.toString());
            return  new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.error("Exception triggered: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("signin_ott")
    public ResponseEntity<JwtDTO> authenticateUserWithOTT(@RequestBody UserLogin2Fact userLogin2Fact) {
        if(userLogin2Fact.getOtt().equals(ottUtility.getOTTByKey(userLogin2Fact.getUsername()))) {
            JwtDTO token = jwtUtils.generateJwtTokenWith2Fact(userLogin2Fact.getUsername());

            return new ResponseEntity<>(token, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }
}
