package com.example.HelloWorld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping
public class HelloWorldController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "shout", required = false) boolean shout) {

        String message = "Hello, world!";

        if(shout == true){
            return message.toUpperCase(Locale.ROOT);
        } else{
            return message;
        }

    }

}
