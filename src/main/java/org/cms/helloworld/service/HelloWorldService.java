package org.cms.helloworld.service;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.cms.helloworld.model.Planet;
import org.cms.helloworld.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class HelloWorldService {

  private final HelloWorldRepository helloWorldRepository;

  @Autowired
  public HelloWorldService(HelloWorldRepository helloWorldRepository) {
    this.helloWorldRepository = helloWorldRepository;
  }

  public List<Planet> findAll() {
    return helloWorldRepository.findAll();
  }

  public Planet getById(Long id) {
    return helloWorldRepository.getById(id);
  }

  public Planet save(Planet planet) {
    return helloWorldRepository.saveAndFlush(planet);
  }

  public void deleteById(Long id) {
    helloWorldRepository.deleteById(id);
  }

  public Planet upsert(Long id, String planetName) {
    return helloWorldRepository.save(Planet.builder().id(id).planetName(planetName).build());
  }
}
