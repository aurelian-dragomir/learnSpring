package com.learn.spring.controller;

import com.learn.spring.model.PersonDetailsDto;
import com.learn.spring.model.PersonDto;
import com.learn.spring.service.PersonDetailsService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
@AllArgsConstructor
public class KafkaController {

    @Autowired
    private PersonDetailsService personDetailsService;

    @PostMapping("/person-detail")
    public void sendPersonDetails(@RequestBody PersonDetailsDto personDetails) {
        personDetailsService.send(personDetails);
    }

    @PostMapping("/{topicName}/person")
    public void sendPerson(@PathParam("topicName") String topicName,
                           @RequestBody PersonDto person) {

    }
}
