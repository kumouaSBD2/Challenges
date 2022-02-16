package com.example.HelloWorld.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Locale;
import java.util.Optional;
import java.util.Stack;

@Service
@Slf4j
public class HelloWorldService {

  //  private final static Logger logger = LoggerFactory.getLogger(HelloWorldService.class);

  public String greetings(
      Optional<String> subject,
      boolean shout,
      boolean reverse,
      boolean latest,
      Stack<String> stack) {

    String base = "Hello, %s!";

    String message = String.format("Hello, %s!", subject.isPresent() ? subject.get() : "world");

    if (subject.isPresent()) {
      stack.push(subject.get());
    }

    if (latest && subject.isEmpty())
      return stack.isEmpty() ? message : String.format(base, stack.pop());

    if ((latest || !latest) && subject.isPresent()) return message;

    if (shout) return message.toUpperCase(Locale.ROOT);

    if (reverse) return new StringBuilder(message).reverse().toString();

    return message;
  }
}
