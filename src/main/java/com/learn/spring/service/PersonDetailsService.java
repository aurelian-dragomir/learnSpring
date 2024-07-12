package com.learn.spring.service;

import com.learn.spring.model.PersonDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.learn.spring.utils.KafkaTopics.IN_TOPIC;

@Service
public class PersonDetailsService {

    @Autowired
    @Qualifier("personDetailsKafkaTemplate")
    private KafkaTemplate<Void, PersonDetailsDto> personDetailsKafkaTemplate;

    @Transactional
    public void send(PersonDetailsDto personDetails) {
        personDetailsKafkaTemplate.send(IN_TOPIC, personDetails);
    }
}
