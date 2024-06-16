package ing.hub.ingHub.repository.jpa.one2one;

import ing.hub.ingHub.entity.jpa.one2one.Details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsRepository extends JpaRepository<Details, Long> {
}
