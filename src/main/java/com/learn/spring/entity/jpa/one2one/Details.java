package com.learn.spring.entity.jpa.one2one;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Details {

    @Id
    private Long id;

    private Integer age;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    private Student student;

    public Details(Integer age) {
        this.age = age;
    }
}
