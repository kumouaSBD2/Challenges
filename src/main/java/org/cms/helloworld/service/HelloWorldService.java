package org.cms.helloworld.service;

import lombok.extern.slf4j.Slf4j;
import org.cms.helloworld.model.Planet;
import org.cms.helloworld.repository.HelloWorldRepository;

import java.util.List;


@Slf4j
public class HelloWorldService {

  private  HelloWorldRepository helloWorldRepository;

  public HelloWorldService(HelloWorldRepository helloWorldRepository) {
    this.helloWorldRepository = helloWorldRepository;
  }

  public List<Planet> getPlanets() {
    return helloWorldRepository.findAll();
  }

  public Planet getPlanetById(Long id) {
    return helloWorldRepository.getById(id);
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
}
