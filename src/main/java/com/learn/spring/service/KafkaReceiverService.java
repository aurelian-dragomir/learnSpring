package com.learn.spring.service;

import com.learn.spring.model.PersonDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.learn.spring.model.PersonDetailsDto;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaReceiverService {

    @Autowired
    private PersonKafkaService personKafkaService;

    @KafkaListener(containerFactory = "inContainerFactory", topics = "in", groupId = "in_group")
    @Transactional
    public void listenForIn(PersonDetailsDto personDetails) {
        log.info("**********\n**********\n**********\n Received message {} from topic in", personDetails);
        personKafkaService.processPersonDetails(personDetails);
    }

//    @KafkaListener(containerFactory = "inContainerFactory", topics = "in", groupId = "in_group")
//    @Transactional
//    public void listenForIn(PersonDetailsDto personDetails) {
//        log.info("**********\n**********\n**********\n Received message {} from topic in", personDetails);
//        personKafkaService.processPersonDetails(personDetails, Optional.of(true), Optional.empty());
//    }

//    @KafkaListener(containerFactory = "inContainerFactory", topics = "in", groupId = "in_group")
//    @Transactional
//    public void listenForIn(PersonDetailsDto personDetails) {
//        log.info("**********\n**********\n**********\n Received message {} from topic in", personDetails);
//        personKafkaService.processPersonDetails(personDetails, Optional.empty(), Optional.of(true));
//    }

    @KafkaListener(containerFactory = "outContainerFactory", topics = "out", groupId = "out_group")
    public void listenForOut(PersonDto person) {
        log.info("**********\n**********\n**********\n Received message {} from topic out", person);
    }
}
