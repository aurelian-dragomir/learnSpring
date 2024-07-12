package com.learn.spring.controller;

import com.learn.spring.model.PersonDetailsDto;
import com.learn.spring.service.PersonKafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonKafkaController {

    @Autowired
    private PersonKafkaService personKafkaService;

    @PostMapping
    public String processPersonDetails(@RequestBody PersonDetailsDto personDetails,
                                       @RequestParam("jpaEx") Optional<Boolean> jpaEx,
                                       @RequestParam("genEx") Optional<Boolean> genEx) {
        personKafkaService.processPersonDetails(personDetails, jpaEx, genEx);
        return "OK";
    }
}
