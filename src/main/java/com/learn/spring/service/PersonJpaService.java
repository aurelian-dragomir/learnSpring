package ing.hub.ingHub.service;

import ing.hub.ingHub.entity.Person;
import ing.hub.ingHub.model.PersonDetailsDto;
import ing.hub.ingHub.model.PersonDto;
import ing.hub.ingHub.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static ing.hub.ingHub.utils.Utils.throwIfPresent;

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
