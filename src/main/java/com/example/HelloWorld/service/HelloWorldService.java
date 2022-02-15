package com.example.HelloWorld.service;

import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class HelloWorldService {

  public String helloWorld(boolean shout) {

    String message = "Hello, world!";

    return shout ? message.toUpperCase(Locale.ROOT) : message;
  }

  public String helloPlanet(String planet, String scream, String reverse) {

    String fixedPlanet = planet.substring(0, 1).toUpperCase() + planet.substring(1);

    String message = String.format("Hello, %s!", fixedPlanet);

    if (scream != null) {
      return message.toUpperCase(Locale.ROOT);
    }

    if (reverse != null) {
      return new StringBuilder(message).reverse().toString();
    }

    return message;
  }
}
