package com.example.HelloWorld.controller;

import com.example.HelloWorld.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

  @GetMapping("/hello")
  public String helloWorld(@RequestParam(required = false) String shout) {

    String message = "Hello, world!";

    return shout != null ? message.toUpperCase(Locale.ROOT) : message;
  }

  @GetMapping("/hello/")
  public ResponseEntity helloError() {
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/hello/{name}")
  public String helloPlanet(
      @PathVariable String name, @RequestParam(required = false) String scream) {

    String x = name.substring(0, 1).toUpperCase() + name.substring(1);

    String message = String.format("Hello, %s!", x);

    return scream != null ? message.toUpperCase(Locale.ROOT) : message;
  }

  @PostMapping("/hello/body")
  public String postHello(
      @RequestBody Person person, @RequestParam(required = false) String scream) {

    String x = person.getName().substring(0, 1).toUpperCase() + person.getName().substring(1);

    String message = String.format("Hello, %s!", x);

    return scream != null ? message.toUpperCase(Locale.ROOT) : message;
  }
}
