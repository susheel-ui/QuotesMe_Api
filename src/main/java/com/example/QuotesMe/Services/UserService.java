package com.example.QuotesMe.Services;

import com.example.QuotesMe.Entities.Quotes;
import com.example.QuotesMe.Entities.User;
import com.example.QuotesMe.Repository.QuotesRepo;
import com.example.QuotesMe.Repository.UserRepo;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpLogging;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;

import static java.rmi.server.LogStream.log;

@Component
@Slf4j
public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepo repo;
    @Autowired
    private QuotesRepo quotesRepo;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Transactional // made it Transaction Service
    public ResponseEntity<?> SignUpUser(User usr){
        try{
        if(usr.getUsername() != null && !usr.getUsername().isEmpty() &&
            usr.getPassword() != null && !usr.getPassword().isEmpty()
        ){
            if(repo.findByUsername(usr.getUsername()) == null){
                    usr.setPassword(passwordEncoder.encode(usr.getPassword()));
                usr.setRoles(Arrays.asList("USER"));
                User save = repo.save(usr);
               log.info("userCreated user {},password {}",save.getUsername(),save.getPassword());

                return new ResponseEntity<>(save,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }


        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            return new ResponseEntity<>("Exception "+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }//SingUp

    public ResponseEntity<?> UpdateUser(User user){
        return new ResponseEntity<>(repo.save(user),HttpStatus.OK);
    }


    public ResponseEntity<?> logInUser(String usrName,String pwd){
        User usr = repo.findByUsername(usrName);
        if(usr!= null){
            if(usrName.equals(usr.getUsername()) && pwd.equals(usr.getPassword())){
                return new ResponseEntity<>(usr,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    public ResponseEntity<?> findByUsrId(String id){
        return new ResponseEntity<>(repo.findById(new ObjectId(id)),HttpStatus.OK);
    }
    public User findByUserName(String userName){
        return repo.findByUsername(userName);
    }
    public ResponseEntity<?> DeleteUser(String username){
        User byUsername = repo.findByUsername(username);
        ArrayList<Quotes> userQuotes =byUsername.getUserQuotes();
        repo.delete(byUsername);
        try{
            for (Quotes x :userQuotes){
                quotesRepo.delete(x);
            }
        }catch (Exception e){
            log.error("user deleted but not deleted "+e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
