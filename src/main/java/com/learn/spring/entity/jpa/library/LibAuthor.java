package com.learn.spring.entity.jpa.library;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "lib_author")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "name")
public class LibAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
    private Set<LibBook> books = new LinkedHashSet<>();

    public LibAuthor(String name) {
        this.name = name;
    }
}
