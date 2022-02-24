package org.cms.helloworld.repository;

import org.cms.helloworld.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelloWorldRepository extends JpaRepository<Planet, Long> {
  List<Planet> findByPlanetName(String planetName);
}
