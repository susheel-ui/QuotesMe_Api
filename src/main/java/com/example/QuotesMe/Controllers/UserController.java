package com.example.QuotesMe.Controllers;

import com.example.QuotesMe.Entities.User;
import com.example.QuotesMe.Services.UserService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> Login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Transactional
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();
            User userInDb = userService.findByUserName(userName);
            if( user.getUsername() != null && !user.getUsername().isEmpty() ){
                userInDb.setUsername(user.getUsername());
            }else{
                userInDb.setUsername(userInDb.getUsername());
            }
            if (user.getPassword() != null && !user.getPassword().isEmpty()){
                userInDb.setPassword(user.getPassword());
                log.info("password has different");
            }else{
                log.info("password not given");
            }
            log.info("password entered {}",user.getPassword());
            log.info("already user password {}",userInDb.getPassword());
            ResponseEntity<?> responseEntity = userService.UpdateUser(userInDb);
            User savedUser = (User) responseEntity.getBody();
            assert savedUser != null;
            log.info("user Updated username :  {} , password : {}",savedUser.getUsername(),savedUser.getPassword());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            log.error("exception occurred during update User {}",e.getMessage());
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

    }

    @DeleteMapping
    public ResponseEntity<?> deleteUsr() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ResponseEntity<?> responseEntity = userService.DeleteUser(authentication.getName());
        return new ResponseEntity<>(responseEntity.getBody(), responseEntity.getStatusCode());
    }
}
