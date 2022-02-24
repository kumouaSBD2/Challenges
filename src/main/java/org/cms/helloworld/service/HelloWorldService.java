package org.cms.helloworld.service;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloWorldService {
  private static final Deque<String> stack = new LinkedList<>();
  private static final String BASE = "Hello, %s!";

  public String greetings(Optional<String> subject, boolean latest) {
    String message = String.format(BASE, subject.orElse("world"));

    subject.ifPresent(stack::push);

    if (latest && subject.isEmpty())
      return stack.isEmpty() ? message : String.format(BASE, stack.peek());

    return message;
  }


  public String popPlanet() {
    return !stack.isEmpty() ? stack.pop() : "Hello, world!";
  }
}
