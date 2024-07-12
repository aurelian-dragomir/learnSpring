package com.learn.spring.service;

import com.learn.spring.entity.Person;
import com.learn.spring.model.PersonDetailsDto;
import com.learn.spring.model.PersonDto;
import com.learn.spring.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


import static com.learn.spring.utils.Utils.throwIfPresent;

@Service
@AllArgsConstructor
public class PersonJpaService {
    private PersonRepository personRepository;

    @Transactional
    public PersonDto save(PersonDetailsDto personDetails, Optional<Boolean> jpaEx) {
        Person person = new Person();
        person.setName(personDetails.name());
        person.setAge(personDetails.age());
        person.setGender(personDetails.gender());
        personRepository.save(person);
        throwIfPresent(jpaEx, "jpa exception");
        return new PersonDto(person.getId(), person.getName(), person.getAge(),
                person.getGender());
    }

    @Transactional
    public PersonDto save(PersonDetailsDto personDetails) {
        return save(personDetails, Optional.empty());
    }

    @Transactional
    public Optional<PersonDto> findById(Long id) {
        return personRepository.findById(id)
                .map(p -> new PersonDto(p.getId(), p.getName(), p.getAge(), p.getGender()));
    }
}
