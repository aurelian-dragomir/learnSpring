package ing.hub.ingHub.repository.jpa.library;

import ing.hub.ingHub.entity.jpa.library.LibBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibBookRepository extends JpaRepository<LibBook, String> {
}
