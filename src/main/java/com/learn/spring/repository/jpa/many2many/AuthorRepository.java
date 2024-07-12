package ing.hub.ingHub.repository.jpa.many2many;

import ing.hub.ingHub.entity.jpa.many2many.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
