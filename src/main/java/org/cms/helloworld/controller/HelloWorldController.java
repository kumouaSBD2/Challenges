package org.cms.helloworld.controller;

import org.cms.helloworld.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping(path = "/hello")
public class HelloWorldController {

  private final HelloWorldService helloWorldService;

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

    String result = helloWorldService.greetings(subject, latest);

    if (shout) result = result.toUpperCase(Locale.ROOT);

    if (reverse) result = StringUtils.reverse(result);

    return result;
  }

  @DeleteMapping
  public String deleteSubject(@RequestParam(name = "pop") String pop) {
    return pop != null ? helloWorldService.popPlanet() : "Hello, world";
  }
}
