package com.learn.spring.controller;

import com.learn.spring.model.BmwCar;
import com.learn.spring.model.Car;
import com.learn.spring.model.Dacia;
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
