package ing.hub.ingHub;

import ing.hub.ingHub.model.PersonDto;
import ing.hub.ingHub.service.PersonService;
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
    private PersonService personService;

    @Test
    public void testAddPerson() {
        String name = "ion";
        personService.save(name);
        Optional<PersonDto> personDto = personService.findByName(name);
        assertTrue(personDto.isPresent());
        assertTrue(personDto.orElseThrow().name().equals(name));
    }
}
