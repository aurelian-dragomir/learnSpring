package com.learn.spring.repository.jpa.library;

import com.learn.spring.entity.jpa.library.LibBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibBookRepository extends JpaRepository<LibBook, String> {
}
