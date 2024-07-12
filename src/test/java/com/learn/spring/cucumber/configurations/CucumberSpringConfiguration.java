package com.learn.spring.cucumber.configurations;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("h2")
public class CucumberSpringConfiguration {
    @Container
    @ServiceConnection
    static OracleContainer oracleContainer = new OracleContainer("gvenzl/oracle-xe:21-slim-faststart");

    @Container
    @ServiceConnection
    static KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"));

    @BeforeAll
    public static void setUp() {
        System.out.println("++++++++ BEFORE ALL ++++++++");

        oracleContainer
                .withReuse(true)
                .start();

        kafkaContainer
                .withReuse(true)
                .start();
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("++++++++ AFTER ALL ++++++++");

        oracleContainer.stop();
        kafkaContainer.stop();
    }
}