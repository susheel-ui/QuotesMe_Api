package com.example.QuotesMe.Controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class rootController {
    @GetMapping("/")
        public String HomePage(){
        return "Welcome to MY Quote Me App";
    }
}
