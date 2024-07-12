package com.learn.spring.repository.jpa.many2many;

import com.learn.spring.entity.jpa.many2many.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
