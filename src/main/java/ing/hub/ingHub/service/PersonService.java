package ing.hub.ingHub.service;

import ing.hub.ingHub.entity.Person;
import ing.hub.ingHub.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {
    private PersonRepository personRepository;

    public void save(String name) {
        Person person = new Person(name);
        personRepository.save(person);
    }
}
