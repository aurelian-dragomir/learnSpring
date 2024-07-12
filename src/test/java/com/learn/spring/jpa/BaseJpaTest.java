package com.learn.spring.jpa;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Supplier;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public abstract class BaseJpaTest {
    @Autowired
    protected TransactionTemplate tx;

    public void printSeparator() {
        System.out.println("\n" + StringUtils.repeat('=', 100) + "\n");
    }

    public <T> T executeInTransaction(Supplier<T> supplier) {
        T val = tx.execute(__ -> supplier.get());
        printSeparator();
        return val;
    }
}
