package com.demo.security.controller;

import com.demo.security.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {
    @GetMapping("/api/persons")
    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Eldar", "Jahijagic"));
        persons.add(new Person("Nenad", "Karajlic"));

        return persons;
    }
}
