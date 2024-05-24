package ing.hub.ingHub.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Person(String name) {
        this.name = name;
    }

    @Column
    private String name;
}
