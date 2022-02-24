package org.cms.helloworld.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Planet {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private long id;

  @Column(name = "PLANET_NAME")
  private String planetName;

  public Planet() {
  }

  public Planet(long id, String planetName) {
    this.id = id;
    this.planetName = planetName;
  }

  public Planet(String planetName) {
    this.planetName = planetName;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getPlanetName() {
    return planetName;
  }

  public void setPlanetName(String planetName) {
    this.planetName = planetName;
  }

  @Override
  public String toString() {
    return "Planet{" +
            "id=" + id +
            ", planetName='" + planetName + '\'' +
            '}';
  }
}
