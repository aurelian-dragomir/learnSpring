package com.learn.spring;

import com.learn.spring.model.Gender;
import com.learn.spring.model.PersonDetailsDto;
import com.learn.spring.model.PersonDto;
import com.learn.spring.service.PersonJpaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("h2")
public class PersonServiceBootstrapServerTest {
    @Autowired
    private PersonJpaService personService;

    @Test
    public void testAddPerson() {
        String name = "Aurelian";
        Long id = personService.save(new PersonDetailsDto(name, 37, Gender.MALE)).id();
        Optional<PersonDto> personDto = personService.findById(id);
        assertTrue(personDto.isPresent());
        assertTrue(personDto.orElseThrow().name().equals(name));
    }
}
