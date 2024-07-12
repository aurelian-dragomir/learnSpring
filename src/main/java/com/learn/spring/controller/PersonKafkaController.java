package ing.hub.ingHub.controller;

import ing.hub.ingHub.model.PersonDetailsDto;
import ing.hub.ingHub.service.PersonKafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonKafkaController {

    @Autowired
    private PersonKafkaService personKafkaService;

    @PostMapping
    public String processPersonDetails(@RequestBody PersonDetailsDto personDetails,
                                       @RequestParam("jpaEx") Optional<Boolean> jpaEx,
                                       @RequestParam("genEx") Optional<Boolean> genEx) {
        personKafkaService.processPersonDetails(personDetails, jpaEx, genEx);
        return "OK";
    }
}
