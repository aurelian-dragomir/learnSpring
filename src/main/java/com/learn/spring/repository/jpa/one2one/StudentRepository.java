package ing.hub.ingHub.repository.jpa.one2one;

import ing.hub.ingHub.entity.jpa.one2one.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
