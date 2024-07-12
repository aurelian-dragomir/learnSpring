package com.learn.spring.repository.jpa.library;

import com.learn.spring.entity.jpa.library.LibBookDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibBookDetailRepository extends JpaRepository<LibBookDetail, String> {
}
