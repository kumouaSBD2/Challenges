package com.example.HelloWorld.controller;

import com.example.HelloWorld.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.Optional;
import java.util.Stack;
import java.util.StringJoiner;

@RestController
@RequestMapping(path = "/hello")
public class HelloWorldController {

  private final HelloWorldService helloWorldService;
  private String subject = "world";
  public Stack<String> stack = new Stack<>();

  @Autowired
  public HelloWorldController(HelloWorldService helloWorldService) {
    this.helloWorldService = helloWorldService;
  }

  @GetMapping(path = {"/", "/{planet}", ""})
  public String greet(
      @PathVariable(name = "planet") Optional<String> subject,
      @RequestParam(name = "shout", required = false) boolean shout,
      @RequestParam(name = "reverse", required = false) boolean reverse,
      @RequestParam(name = "latest", required = false) boolean latest) {

    return helloWorldService.greetings(subject, shout, reverse, latest, stack);

    //    return  String.format("Hello, %s!", this.subject);

    // if planet not set, and ?latest=true not set, return "Hello, world!"
    // if planet not set, and ?latest=true, return "Hello, subject!"
    // if planet set, and ?latest=true or ?latest=false, return "Hello, planet!"
    // if shout set, return response in ALL CAPS
    // if reverse set, return string reversed
    // handle the String subject = "world"; first as a member variable of the HelloWorldService
    // then migrate the "subjectLatest" to a stack, pop stack repeatedly with `/hello?pop` until
    // stack is empty
    // if stack empty, return Hello, world!

    // return helloWorldService.helloWorld(shout);

  }

  @PutMapping(path = "/{planet}")
  public String setSubject(String subject) {

    this.subject = subject;
    return subject;
  }

  //  @GetMapping("/hello/")
  //  public ResponseEntity helloError() {
  //    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  //  }
  //
  //  @GetMapping("/hello/{planet}")
  //  public String helloPlanet(
  //      @PathVariable String planet,
  //      @RequestParam(required = false) String scream,
  //      @RequestParam(required = false) String reverse) {
  //
  //    return helloWorldService.helloPlanet(planet, scream, reverse);
  //  }

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
