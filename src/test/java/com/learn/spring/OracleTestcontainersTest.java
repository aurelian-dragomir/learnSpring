package com.learn.spring;

import ing.hub.ingHub.model.Gender;
import ing.hub.ingHub.model.PersonDetailsDto;
import ing.hub.ingHub.model.PersonDto;
import ing.hub.ingHub.service.PersonJpaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest
public class OracleTestcontainersTest {

    @Autowired
    private PersonJpaService personJpaService;

    @Container
    @ServiceConnection
    static OracleContainer oracleContainer = new OracleContainer("gvenzl/oracle-xe:21-slim-faststart");

    @Test
    public void helloTestcontainers() {
        String name = "ion";
        Long id = personJpaService.save(new PersonDetailsDto(name, 37, Gender.MALE)).id();
        Optional<PersonDto> personDto = personJpaService.findById(id);
        assertTrue(personDto.isPresent());
        assertTrue(personDto.orElseThrow().name().equals(name));
    }

    @Test
    public void helloTestcontainers2() {
        String name = "vasile";
        Optional<PersonDto> personDto = personJpaService.findById(2L);
        assertTrue(personDto.isEmpty());
    }
}