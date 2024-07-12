package com.learn.spring.entity.jpa.many2many2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private Set<CustomerProduct> customerProducts = new LinkedHashSet<>();

    public Product(String name) {
        this.name = name;
    }
}