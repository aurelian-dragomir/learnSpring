package com.learn.spring.repository.jpa.many2many;

import com.learn.spring.entity.jpa.many2many.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
