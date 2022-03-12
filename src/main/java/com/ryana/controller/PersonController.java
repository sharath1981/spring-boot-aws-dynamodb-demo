package com.ryana.controller;

import com.ryana.domain.Person;
import com.ryana.repository.PersonRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@RestController
@RequestMapping("persons")
public class PersonController {

    private final PersonRepository repository;

    @PostMapping
    public Person savePerson(@RequestBody final Person person) {
        return repository.save(person);
    }

    @GetMapping("{id}")
    public Person findPerson(@PathVariable final String id) {
        return repository.findByPersonId(id);
    }

    @DeleteMapping
    public String deletePerson(@RequestBody final Person person) {
        return repository.delete(person);
    }

    @PutMapping
    public String updatePerson(@RequestBody final Person person) {
        return repository.update(person);
    }
    
}
