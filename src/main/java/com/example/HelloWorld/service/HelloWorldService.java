package com.example.HelloWorld.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Stack;

@Service
@Slf4j
public class HelloWorldService {

  public String greetings(Optional<String> subject, boolean latest, Stack<String> stack) {

    String base = "Hello, %s!";

    String message = String.format(base, subject.isPresent() ? subject.get() : "world");

    if (subject.isPresent()) stack.push(subject.get());

    if (latest && subject.isEmpty())
      return stack.isEmpty() ? message : String.format(base, stack.peek());

    if ((latest || !latest) && subject.isPresent()) return message;

    return message;
  }

  public String addPlanet(String subject, Stack<String> stack) {
    return stack.push(subject);
  }

  public String popPlanet(Stack<String> stack) {
    return !stack.isEmpty() ? stack.pop() : "Hello, world!";
  }
}
