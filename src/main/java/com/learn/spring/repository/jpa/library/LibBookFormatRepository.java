package ing.hub.ingHub.repository.jpa.library;

import ing.hub.ingHub.entity.jpa.library.LibBookFormat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibBookFormatRepository extends JpaRepository<LibBookFormat, Long> {
}
