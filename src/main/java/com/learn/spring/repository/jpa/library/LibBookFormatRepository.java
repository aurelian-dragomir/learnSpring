package com.learn.spring.repository.jpa.library;

import com.learn.spring.entity.jpa.library.LibBookFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibBookFormatRepository extends JpaRepository<LibBookFormat, Long> {
}
