package org.cms.helloworld.controller;

import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.cms.helloworld.model.Planet;
import org.cms.helloworld.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping(path = "/hello")
public class HelloWorldController {

  private final HelloWorldService helloWorldService;

  @Autowired
  public HelloWorldController(HelloWorldService helloWorldService) {
    this.helloWorldService = helloWorldService;
  }

  @GetMapping(path = {"/", ""})
  public List<Planet> getAllPlanets() {
    return helloWorldService.findAll();
  }

  @GetMapping(path = "/{id}")
  public String getAllModifiedPlanets(
      @RequestParam(name = "shout", required = false) boolean shout,
      @RequestParam(name = "reverse", required = false) boolean reverse,
      @PathVariable(name = "id") Long id) {

    String base = "Hello, %s!";
    List<Planet> result = helloWorldService.findAll();
    Planet planet = helloWorldService.getById(id);

    String message =
        result.contains(planet)
            ? String.format(base, planet.getPlanetName())
            : String.format(base, "world");

    if (shout) message = message.toUpperCase(Locale.ROOT);

    if (reverse) message = StringUtils.reverse(message);

    return message;
  }

  @PutMapping(value = "/{id}")
  public Planet updatePlanet(@PathVariable(value = "id") Long id, @RequestBody String planetName) {
    return helloWorldService.upsert(id, planetName);
  }

  @PostMapping(value = "/{planetName}")
  public Planet addPlanet(@PathVariable(name = "planetName") String planetName) {
    return helloWorldService.save(Planet.builder().planetName(planetName).build());
  }

  @DeleteMapping(value = "/{id}")
  public void deletePlanet(@PathVariable(value = "id") Long id) {
    helloWorldService.deleteById(id);
  }
}
