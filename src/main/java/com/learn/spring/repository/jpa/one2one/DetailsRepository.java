package com.learn.spring.repository.jpa.one2one;

import com.learn.spring.entity.jpa.one2one.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Long> {
}
