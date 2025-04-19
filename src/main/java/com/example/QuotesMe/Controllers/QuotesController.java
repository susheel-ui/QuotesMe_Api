package com.example.QuotesMe.Controllers;

import com.example.QuotesMe.Entities.Quotes;
import com.example.QuotesMe.Entities.User;
import com.example.QuotesMe.Services.QuotesService;
import com.example.QuotesMe.Services.UserService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
@RestController
@RequestMapping("/quotes")
public class QuotesController {
    private static final Logger log = LoggerFactory.getLogger(QuotesController.class);
    @Autowired
    private QuotesService qService;
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> saveQuote(@RequestBody Quotes myQuotes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User byUserName = userService.findByUserName(authentication.getName());
        return qService.SaveQuote(byUserName.getId().toString(), myQuotes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           Quotes q = qService.FindOne(new ObjectId(id));
        return (q != null ? new ResponseEntity<>(q, HttpStatus.OK):new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/user-quotes")
    public ResponseEntity<?> findQuotes(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName());
        ArrayList<Quotes> quotes = user.getUserQuotes();
        return new ResponseEntity<>(quotes,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  UpdateQuote(@PathVariable ObjectId id, @RequestBody Quotes newQuotes){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           return qService.QuoteUpdateQuote(id,newQuotes);
  }
    @DeleteMapping
    public ResponseEntity<?> DeleteQuote(@RequestParam String id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       return qService.DeleteQuotes(id);
    }


}
