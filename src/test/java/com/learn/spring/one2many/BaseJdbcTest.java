package ing.hub.ingHub.jdbc.one2many;

import ing.hub.ingHub.repository.jdbc.PostDtoRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Supplier;

@JdbcTest
public class BaseJdbcTest {

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
