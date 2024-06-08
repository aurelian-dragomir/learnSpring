package ing.hub.ingHub.controller;

import ing.hub.ingHub.model.PersonDto;
import ing.hub.ingHub.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    @PostMapping
    public String savePerson(@RequestParam("name") String name) {
        personService.save(name);
        return "OK";
    }

    @GetMapping
    public List<PersonDto> getPeople(){
        return personService.findAll();
    }
}
