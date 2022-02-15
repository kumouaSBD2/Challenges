package com.example.HelloWorld.controller;

import com.example.HelloWorld.model.Person;
import com.example.HelloWorld.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
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

  private final HelloWorldService helloWorldService;

  @Autowired
  public HelloWorldController(HelloWorldService helloWorldService) {
    this.helloWorldService = helloWorldService;
  }

  @GetMapping("/hello")
  public String helloWorld(@RequestParam(required = false) boolean shout) {

    return helloWorldService.helloWorld(shout);
  }

  @GetMapping("/hello/")
  public ResponseEntity helloError() {
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  @GetMapping("/hello/{planet}")
  public String helloPlanet(
      @PathVariable String planet,
      @RequestParam(required = false) String scream,
      @RequestParam(required = false) String reverse) {

    return helloWorldService.helloPlanet(planet, scream, reverse);
  }

  //  @PostMapping("/hello/body")
  //  public String postHello(
  //      @RequestBody Person person, @RequestParam(required = false) String scream) {
  //
  //    String x = person.getName().substring(0, 1).toUpperCase() + person.getName().substring(1);
  //
  //    String message = String.format("Hello, %s!", x);
  //
  //    return scream != null ? message.toUpperCase(Locale.ROOT) : message;
  //  }
}
