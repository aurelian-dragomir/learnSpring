package com.learn.spring;

import ing.hub.ingHub.model.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerBootstrapServerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String HOST = "http://localhost:%d/car";

    @Test
    public void testListSize() {
        Car[] cars = restTemplate.getForObject(HOST.formatted(port), Car[].class);
        assertTrue(cars.length == 2);
    }
}
