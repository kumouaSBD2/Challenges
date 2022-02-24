package org.cms.helloworld.service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.cms.helloworld.model.Planet;
import org.cms.helloworld.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloWorldService {

  private final HelloWorldRepository helloWorldRepository;

  @Autowired
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
