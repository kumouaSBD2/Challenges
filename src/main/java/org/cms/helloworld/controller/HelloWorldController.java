package org.cms.helloworld.controller;

import org.cms.helloworld.model.Planet;
import org.cms.helloworld.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
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

  @GetMapping(path = {"/", ""})
  public List<Planet> getAllPlanets() {
    List<Planet> result = helloWorldService.getPlanets();
    return result;
  }

  @GetMapping(path = "/{id}")
  public String getAllModifiedPlanets(
      @RequestParam(name = "shout", required = false) boolean shout,
      @RequestParam(name = "reverse", required = false) boolean reverse,
      @PathVariable(name = "id") Long id) {

    String base = "Hello, %s!";
    List<Planet> result = helloWorldService.getPlanets();
    String message =
        result.contains(result.get(Math.toIntExact(id)))
            ? String.format(base, result.get(Math.toIntExact(id)).getPlanetName())
            : String.format(base, "world");

    if (shout) message = message.toUpperCase(Locale.ROOT);

    if (reverse) message = StringUtils.reverse(message);

    return message;
  }

  @PutMapping(value = "/updatePlanet/{id}")
  public Planet updatePlanet(@PathVariable(value = "id") Long id, @RequestBody String planetName) {
    return helloWorldService.updatePlanet(id, planetName);
  }

  @PostMapping(value = "/addPlanet")
  public Planet addPlanet(@RequestBody Planet planet) {
    return helloWorldService.addPlanet(planet);
  }

  @DeleteMapping(value = "/{id}")
  public void deletePlanet(@PathVariable(value = "id") Long id) {
    helloWorldService.deletePlanet(id);
  }
}
