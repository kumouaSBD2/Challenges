package com.example.HelloWorld.model;

import lombok.Data;

@Data
public class Person {
    private String name;

    public Person(String name) {
        this.setName(name);
    }
}
