package com.learn.spring.cucumber.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HelloSteps {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private ResponseEntity<String> response;

    @Given("some customer details")
    public void some_customer_details() {
        response = restTemplate.getForEntity("http://localhost:" + port + "/person", String.class);
        assertTrue(response != null);
    }

    @When("I invoke the create customer operation with the customer details")
    public void i_invoke_the_create_customer_operation_with_the_customer_details() {
        assertTrue(response.getStatusCode().is2xxSuccessful());
    }

    @Then("the customer details should be persisted")
    public void the_customer_details_should_be_persisted() {
        assertTrue(response.getBody().equals("hello"));
    }

    @Given("we make a get request to \\/hello")
    public void we_make_a_get_request_to_hello() {
        response = restTemplate.getForEntity("http://localhost:" + port + "/person", String.class);
    }

    @When("i check the response")
    public void i_check_the_response() {
        assertTrue(response != null);
    }

    @Then("the status code should be {int}")
    public void the_status_code_should_be(Integer expectedSc) {
        assertTrue(response.getStatusCode().value() == expectedSc);
    }

    @Then("the body should be {string}")
    public void the_body_should_be(String responseBody) {
        assertTrue(response.getBody().equals(responseBody));
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("=================\n=================\n=================\n=================\n");
    }
}
