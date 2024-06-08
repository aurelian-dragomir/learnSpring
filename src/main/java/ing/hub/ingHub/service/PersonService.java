package ing.hub.ingHub.service;

import ing.hub.ingHub.entity.Person;
import ing.hub.ingHub.model.PersonDto;
import ing.hub.ingHub.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {
    private PersonRepository personRepository;

    public void save(String name) {
        Person person = new Person(name);
        personRepository.save(person);
    }

    public List<PersonDto> findAll() {
        return personRepository.findAll().stream()
                .map(p -> new PersonDto(p.getId(), p.getName()))
                .toList();
    }

    public Optional<PersonDto> findByName(String name) {
        return personRepository.findByName(name).map(p -> new PersonDto(p.getId(), p.getName()));
    }
}
