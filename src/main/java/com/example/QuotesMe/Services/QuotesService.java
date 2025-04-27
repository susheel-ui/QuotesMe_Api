package com.example.QuotesMe.Services;

import com.example.QuotesMe.Entities.Quotes;
import com.example.QuotesMe.Entities.User;
import com.example.QuotesMe.Repository.QuotesRepo;
import com.example.QuotesMe.Repository.UserRepo;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Component
@Slf4j
public class QuotesService {
    private static final Logger log = LoggerFactory.getLogger(QuotesService.class);
    @Autowired
    private QuotesRepo qouteRepo;
    @Autowired
    private UserRepo usrRepo;

    @Transactional
    private Quotes saveQuote(Quotes quotes) {
        return qouteRepo.save(quotes);
    }

    @Transactional
    public ResponseEntity<?> SaveQuote(String id, Quotes quotes) {
            User usrResById = usrRepo.findById(new ObjectId(id)).orElse(null);
            if (usrResById != null) {
                quotes.setAuthor(usrResById.getUsername());
                quotes.setDate(LocalDateTime.now().toString());
                Quotes result = qouteRepo.save(quotes);
                usrResById.getUserQuotes().add(result);
                usrRepo.save(usrResById);
                return new ResponseEntity<>(result, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    public ArrayList<Quotes> getAllQuotes() {
        return new ArrayList<>(qouteRepo.findAll());
    }

    public Quotes FindOne(ObjectId id) {
        return qouteRepo.findById(id).orElse(null);
    }

    public ResponseEntity<?> QuoteUpdateQuote(ObjectId id, Quotes newQuotes) {
        Quotes old = this.FindOne(id);
        if (old != null) {
            if (newQuotes.getBody() != null && !newQuotes.getBody().isEmpty())
                old.setBody(newQuotes.getBody());
            if (newQuotes.getAuthor() != null && !newQuotes.getAuthor().isEmpty())
                old.setAuthor(newQuotes.getAuthor());
            if (newQuotes.getCategories() != null && !newQuotes.getCategories().isEmpty())
                old.setCategories(newQuotes.getCategories());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Quotes quotes = this.saveQuote(old);
        return (quotes != null) ? new ResponseEntity<>(quotes, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @Transactional
    public ResponseEntity<?> DeleteQuotes(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User usrResById = usrRepo.findById(new ObjectId(authentication.getName())).orElse(null);
        Quotes quotes = qouteRepo.findById(new ObjectId(id)).orElse(null);
        if (usrResById != null && quotes != null) {
            qouteRepo.deleteById(new ObjectId(id));
            usrResById.getUserQuotes().remove(quotes);
            usrRepo.save(usrResById);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
