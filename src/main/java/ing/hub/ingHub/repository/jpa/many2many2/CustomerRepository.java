package ing.hub.ingHub.repository.jpa.many2many2;

import ing.hub.ingHub.entity.jpa.many2many2.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
