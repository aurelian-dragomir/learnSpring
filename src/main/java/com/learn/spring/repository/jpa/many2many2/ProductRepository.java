package com.learn.spring.repository.jpa.many2many2;

import com.learn.spring.entity.jpa.many2many2.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
