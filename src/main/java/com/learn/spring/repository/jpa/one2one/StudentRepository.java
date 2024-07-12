package com.learn.spring.repository.jpa.one2one;

import com.learn.spring.entity.jpa.one2one.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
