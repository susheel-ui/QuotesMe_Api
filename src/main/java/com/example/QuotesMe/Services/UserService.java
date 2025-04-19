package com.example.QuotesMe.Services;

import com.example.QuotesMe.Entities.User;
import com.example.QuotesMe.Repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserService {
    @Autowired
    private UserRepo repo;
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Transactional // made it Transaction Service
    public ResponseEntity<?> SignUpUser(User usr){
        try{
        if(usr.getUsername() != null && !usr.getUsername().isEmpty() &&
            usr.getPassword() != null && !usr.getPassword().isEmpty()
        ){
            if(repo.findByUsername(usr.getUsername()) == null){
                usr.setPassword(passwordEncoder.encode(usr.getPassword()));
                User save = repo.save(usr);
                return new ResponseEntity<>(save,HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e){
            return new ResponseEntity<>("Exception "+e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }//SingUp

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
        if(byUsername!= null){
            repo.delete(byUsername);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
