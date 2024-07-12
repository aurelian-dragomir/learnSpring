package com.learn.spring.entity.jpa.many2many2;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(of = {"customer", "product"})
@Data
public class CustomerProduct {

    @EmbeddedId
    private CustomerProductId id;

    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("customerId")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    public CustomerProduct(Customer customer, Product product, int quantity) {
        this.id = new CustomerProductId(customer.getId(), product.getId());
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
    }

    public CustomerProduct(Long customerdId, Long productId, int quantity) {
        this.id = new CustomerProductId(customerdId, productId);
        this.quantity = quantity;
    }

    public CustomerProduct(int quantity) {
        this.quantity = quantity;
    }

    public void addCustomer(Customer customer) {
        customer.getCustomerProducts().add(this);
        setCustomer(customer);
    }

    public void addProduct(Product product) {
        product.getCustomerProducts().add(this);
        setProduct(product);
    }
}
