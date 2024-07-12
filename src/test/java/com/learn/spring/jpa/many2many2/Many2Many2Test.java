package com.learn.spring.jpa.many2many2;

import com.learn.spring.entity.jpa.many2many2.Customer;
import com.learn.spring.entity.jpa.many2many2.CustomerProduct;
import com.learn.spring.entity.jpa.many2many2.Product;
import com.learn.spring.jpa.BaseJpaTest;
import com.learn.spring.repository.jpa.many2many2.CustomerProductRepository;
import com.learn.spring.repository.jpa.many2many2.CustomerRepository;
import com.learn.spring.repository.jpa.many2many2.ProductRepository;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class Many2Many2Test extends BaseJpaTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerProductRepository customerProductRepository;

    @Test
    public void testAddCustomerAndProduct() {
        executeInTransaction(() -> {
            Customer customer = new Customer("ion");
            Product product = new Product("iphone 15");
            customerRepository.save(customer);
            productRepository.save(product);

            CustomerProduct customerProduct = new CustomerProduct(customer, product, 3);
            customerProduct.addCustomer(customer);
            customerProduct.addProduct(product);
            customerProductRepository.save(customerProduct);

            return null;
        });
    }

    @Test
    public void testAddCustomerProductLater() {
        Pair<Long, Long> ids = executeInTransaction(() -> {
            Customer customer = new Customer("ion");
            Product product = new Product("iphone 15");
            customerRepository.save(customer);
            productRepository.save(product);
            return Pair.of(customer.getId(), product.getId());
        });

        executeInTransaction(() -> {
            CustomerProduct customerProduct = new CustomerProduct(ids.getKey(), ids.getValue(), 3);
            customerProduct.setCustomer(customerRepository.getReferenceById(ids.getKey()));
            customerProduct.setProduct(productRepository.getReferenceById(ids.getValue()));

            customerProductRepository.save(customerProduct);

            return null;
        });
    }

    @Test
    public void testRemoveCustomerProductWithJpql() {
        Pair<Long, Long> ids = executeInTransaction(() -> {
            Customer customer = new Customer("ion");
            Product product = new Product("iphone 15");
            customerRepository.save(customer);
            productRepository.save(product);

            CustomerProduct customerProduct = new CustomerProduct(customer, product, 3);
            customerProduct.addCustomer(customer);
            customerProduct.addProduct(product);
            customerProductRepository.save(customerProduct);

            return Pair.of(customer.getId(), product.getId());
        });

        executeInTransaction(() -> {

            CustomerProduct customerProduct = customerProductRepository.findForCustomerAndProduct(ids.getLeft(),
                    ids.getRight()).get();
            customerProduct.setProduct(null);
            customerProduct.setCustomer(null);
            customerProductRepository.delete(customerProduct);

            return null;
        });
    }

    @Test
    public void testRemoveCustomerProduct() {
        Pair<Long, Long> ids = executeInTransaction(() -> {
            Customer customer = new Customer("ion");
            Product product = new Product("iphone 15");
            customerRepository.save(customer);
            productRepository.save(product);

            CustomerProduct customerProduct = new CustomerProduct(customer, product, 3);
            customerProduct.addCustomer(customer);
            customerProduct.addProduct(product);
            customerProductRepository.save(customerProduct);

            return Pair.of(customer.getId(), product.getId());
        });

        executeInTransaction(() -> {

            Customer customer = customerRepository.findById(ids.getLeft()).get();
            Product product = productRepository.findById(ids.getRight()).get();
            CustomerProduct customerProduct = customer.getLinkForProduct(product).get();

            customerProduct.setCustomer(null);
            customerProduct.setProduct(null);
            customerProductRepository.delete(customerProduct);

            return null;
        });
    }

    @Test
    public void testRemoveCustomer() {
        Pair<Long, Long> ids = executeInTransaction(() -> {
            Customer customer = new Customer("ion");
            Product product = new Product("iphone 15");
            customerRepository.save(customer);
            productRepository.save(product);

            CustomerProduct customerProduct = new CustomerProduct(customer, product, 3);
            customerProduct.addCustomer(customer);
            customerProduct.addProduct(product);
            customerProductRepository.save(customerProduct);

            return Pair.of(customer.getId(), product.getId());
        });

        executeInTransaction(() -> {
            Customer customer = customerRepository.findById(ids.getLeft()).get();
            customerProductRepository.deleteAllInBatch(customer.getCustomerProducts());
            customerRepository.delete(customer);
            return null;
        });
    }
}
