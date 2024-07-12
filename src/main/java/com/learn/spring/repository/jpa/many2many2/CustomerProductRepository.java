package com.learn.spring.repository.jpa.many2many2;

import com.learn.spring.entity.jpa.many2many2.CustomerProduct;
import com.learn.spring.entity.jpa.many2many2.CustomerProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerProductRepository extends JpaRepository<CustomerProduct, CustomerProductId> {

    @Query("from CustomerProduct cp where cp.customer.id=:cId and cp.product.id=:pId")
    Optional<CustomerProduct> findForCustomerAndProduct(@Param("cId") Long cId, @Param("pId") Long pId);
}
