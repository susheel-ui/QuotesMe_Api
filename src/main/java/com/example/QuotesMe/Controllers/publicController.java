package com.example.QuotesMe.Controllers;
import com.example.QuotesMe.Entities.User;
import com.example.QuotesMe.Services.QuotesService;
import com.example.QuotesMe.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class publicController {
    @Autowired
    private UserService userService;
    @Autowired
    private QuotesService quotesService;
    @PostMapping("/create-user")
    public ResponseEntity<?> signUp(@RequestBody User usr){
        return userService.SignUpUser(usr);
    }
    @GetMapping
    public ResponseEntity<?> healthCheck(){
        return new ResponseEntity<>("Working.....",HttpStatus.OK);
        // it`s use for check the server checking
    }
    @GetMapping("/get-all-quotes")
    public ResponseEntity<?> getAllQuotes() {
        return new ResponseEntity<>(quotesService.getAllQuotes(),HttpStatus.OK);
    }
}
