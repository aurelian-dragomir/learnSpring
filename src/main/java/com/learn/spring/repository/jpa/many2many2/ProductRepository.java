package ing.hub.ingHub.repository.jpa.many2many2;

import ing.hub.ingHub.entity.jpa.many2many2.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
