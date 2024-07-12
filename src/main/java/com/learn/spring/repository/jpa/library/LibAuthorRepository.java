package com.learn.spring.repository.jpa.library;

import com.learn.spring.entity.jpa.library.LibAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibAuthorRepository extends JpaRepository<LibAuthor, Long> {
}
