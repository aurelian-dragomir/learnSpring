package com.learn.spring.entity.jpa.many2many2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<CustomerProduct> customerProducts = new LinkedHashSet<>();

    public Customer(String name) {
        this.name = name;
    }

    public Optional<CustomerProduct> getLinkForProduct(Product product) {
        CustomerProduct customerProduct = null;
        for (Iterator<CustomerProduct> it = customerProducts.iterator(); it.hasNext(); ) {
            customerProduct = it.next();
            if (customerProduct.getCustomer().equals(this) && customerProduct.getProduct().equals(product)) {
                break;
            }
        }
        return Optional.ofNullable(customerProduct);
    }
}
