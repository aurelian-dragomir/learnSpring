package com.learn.spring;

import ing.hub.ingHub.entity.Person;
import ing.hub.ingHub.model.Gender;
import ing.hub.ingHub.model.PersonDto;
import ing.hub.ingHub.repository.PersonRepository;
import ing.hub.ingHub.service.PersonJpaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceMockTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonJpaService personService;

    @Test
    public void testGetPerson() throws Throwable {
        String name = "Aurelian";
        Long id = 1L;
        Person person = new Person();
        person.setId(1L);
        person.setName(name);
        person.setAge(37);
        person.setGender(Gender.MALE);
        when(personRepository.findById(any())).thenReturn(Optional.of(person));
        Optional<PersonDto> personDto = personService.findById(id);
        assertTrue(personDto.isPresent());
        assertTrue(personDto.orElseThrow(this::personNotFoundException).name().equals(name));
    }

    private Throwable personNotFoundException() {
        return new RuntimeException("Person not found!");
    }
}
