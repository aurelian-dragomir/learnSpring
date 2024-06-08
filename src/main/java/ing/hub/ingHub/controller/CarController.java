package ing.hub.ingHub.controller;

import ing.hub.ingHub.model.BmwCar;
import ing.hub.ingHub.model.Car;
import ing.hub.ingHub.model.Dacia;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @GetMapping
    public List<Car> getAllCars() {
        return Arrays.asList(new BmwCar(), new Dacia());
    }
}
