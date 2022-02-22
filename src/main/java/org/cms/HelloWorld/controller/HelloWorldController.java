package org.cms.HelloWorld.controller;

import org.cms.HelloWorld.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Optional;
import java.util.Stack;

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

    String result = helloWorldService.greetings(subject, latest, stack);

    if (shout && reverse) return StringUtils.reverse(result).toUpperCase(Locale.ROOT);

    if (shout) return result.toUpperCase(Locale.ROOT);

    if (reverse) return StringUtils.reverse(result);

    return result;
  }

  @PutMapping(path = "/{planet}")
  public String setSubject(@PathVariable(name = "planet") String subject) {

    this.subject = subject;

    helloWorldService.addPlanet(subject, stack);

    return subject;
  }

  @DeleteMapping
  public String deleteSubject(@RequestParam(name = "pop") String pop) {
    return pop != null ? helloWorldService.popPlanet(stack) : "Hello, world";
  }
}
