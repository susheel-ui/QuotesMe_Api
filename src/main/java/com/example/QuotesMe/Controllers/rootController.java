package com.example.QuotesMe.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@RequestMapping("/QuoteMe")
public class rootController {
    @GetMapping()
        public String HomePage(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Welcome to MY Quote Me App";
    }
}
