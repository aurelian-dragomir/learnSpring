package com.learn.spring.service;

import com.learn.spring.model.PersonDetailsDto;
import com.learn.spring.model.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.learn.spring.utils.KafkaTopics.OUT_TOPIC;
import static com.learn.spring.utils.Utils.throwIfPresent;

@Service
public class PersonKafkaService {

    @Autowired
    private PersonJpaService personJpaService;

    @Autowired
    private KafkaTemplate<Long, PersonDto> personKafkaTemplate;

    @Transactional
    public void processPersonDetails(PersonDetailsDto personDetails, Optional<Boolean> jpaEx, Optional<Boolean> genEx) {
        PersonDto person = personJpaService.save(personDetails, jpaEx);
        personKafkaTemplate.send(OUT_TOPIC, person.id(), person);
        throwIfPresent(genEx, "general exception");
    }

    public void processPersonDetails(PersonDetailsDto personDetails) {
        processPersonDetails(personDetails, Optional.empty(), Optional.empty());
    }
}
