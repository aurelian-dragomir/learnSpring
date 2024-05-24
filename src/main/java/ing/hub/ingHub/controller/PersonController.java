package ing.hub.ingHub.controller;

import ing.hub.ingHub.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
