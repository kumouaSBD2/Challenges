package org.cms.helloworld.service;

import java.util.List;
import java.util.Optional;

import org.cms.helloworld.model.Planet;
import org.cms.helloworld.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// @Slf4j
public class HelloWorldService {

  private final HelloWorldRepository helloWorldRepository;

  @Autowired
  public HelloWorldService(HelloWorldRepository helloWorldRepository) {
    this.helloWorldRepository = helloWorldRepository;
  }

  public List<Planet> getPlanets() {
    return helloWorldRepository.findAll();
  }

  public Planet addPlanet(Planet planet) {
    return helloWorldRepository.save(planet);
  }

  public void deletePlanet(Long id) {
    helloWorldRepository.deleteById(id);
  }

  public Planet updatePlanet(Long id, String planetName) {
    Planet planet = helloWorldRepository.getById(id);
    planet.setPlanetName(planetName);
    return helloWorldRepository.save(planet);
  }

  //  private static final Deque<String> stack = new LinkedList<>();
  //  private static final String BASE = "Hello, %s!";

  //  public String greetings(Optional<String> subject, boolean latest) {
  //    String message = String.format(BASE, subject.orElse("world"));
  //
  //    subject.ifPresent(stack::push);
  //
  //    if (latest && subject.isEmpty())
  //      return stack.isEmpty() ? message : String.format(BASE, stack.peek());
  //
  //    return message;
  //  }

  //  public String popPlanet() {
  //    return !stack.isEmpty() ? stack.pop() : "Hello, world!";
  //  }
}
