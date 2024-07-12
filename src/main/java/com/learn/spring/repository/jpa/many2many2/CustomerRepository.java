package com.learn.spring.repository.jpa.many2many2;

import com.learn.spring.entity.jpa.many2many2.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
