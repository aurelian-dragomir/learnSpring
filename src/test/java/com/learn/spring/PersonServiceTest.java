package com.learn.spring;

import com.learn.spring.entity.Person;
import com.learn.spring.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class PersonServiceTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testAddNewPerson() {
        personRepository.save(new Person("Vasile"));
        Optional<Person> p = personRepository.findByName("Vasile");
        assertTrue(p.isPresent());
    }
}
