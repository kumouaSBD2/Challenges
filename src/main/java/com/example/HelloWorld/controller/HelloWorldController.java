package com.example.HelloWorld.controller;

import com.example.HelloWorld.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import java.util.Locale;
import java.util.Optional;
import java.util.Stack;
import java.util.StringJoiner;

@RestController
@RequestMapping(path = "/hello")
public class HelloWorldController {

  private final HelloWorldService helloWorldService;
  private String subject = "world";
  private Stack<String> stack = new Stack<>();

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

    if (shout) return helloWorldService.greetings(subject, latest, stack).toUpperCase(Locale.ROOT);

    if (reverse)
      return new StringBuilder(helloWorldService.greetings(subject, latest, stack))
          .reverse()
          .toString();

    return helloWorldService.greetings(subject, latest, stack);
  }

  @PutMapping(path = "/{planet}")
  public String setSubject(@PathVariable(name = "planet") String subject) {

    this.subject = subject;

    helloWorldService.addPlanet(subject, stack);

    return subject;
  }

  @DeleteMapping
  public String deleteSubject(@RequestParam(name = "pop") String pop) {
    return pop != null ? helloWorldService.popPlanet(stack) : "Hello";
  }
}
