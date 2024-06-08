package ing.hub.ingHub;

import ing.hub.ingHub.entity.Person;
import ing.hub.ingHub.model.PersonDto;
import ing.hub.ingHub.repository.PersonRepository;
import ing.hub.ingHub.service.PersonService;
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
    private PersonService personService;

    @Test
    public void testGetPerson() throws Throwable {
        String name = "ion";
        when(personRepository.findByName(any())).thenReturn(Optional.of(new Person(name)));
        Optional<PersonDto> personDto = personService.findByName(name);
        assertTrue(personDto.isPresent());
        assertTrue(personDto.orElseThrow(this::personNotFoundException).name().equals(name));
    }

    private Throwable personNotFoundException() {
        return new RuntimeException("Person not found!");
    }
}
