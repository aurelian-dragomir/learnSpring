package ing.hub.ingHub.jpa.one2one;

import ing.hub.ingHub.entity.jpa.one2one.Student;
import ing.hub.ingHub.entity.jpa.one2one.Details;
import ing.hub.ingHub.jpa.BaseJpaTest;
import ing.hub.ingHub.repository.jpa.one2one.DetailsRepository;
import ing.hub.ingHub.repository.jpa.one2one.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class One2OneTest extends BaseJpaTest {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DetailsRepository detailsRepository;

    @Test
    public void testAddStudentWithDetails() {
        executeInTransaction(() -> {
            Student student = new Student("Ion");
            Details details = new Details(34);
            details.setStudent(student);

            studentRepository.save(student);
            detailsRepository.save(details);

            return null;
        });
    }

    @Test
    public void testAdDetailsForExistingStudent() {
        Long id = executeInTransaction(() -> {
            Student student = new Student("Ion");
            studentRepository.save(student);
            return student.getId();
        });

        executeInTransaction(() -> {
            Student student = studentRepository.getReferenceById(id);
            Details details = new Details(45);
            details.setStudent(student);
            detailsRepository.save(details);
            return null;
        });
    }

    @Test
    public void testDeleteStudentWithDetails() {
        Long id = executeInTransaction(() -> {
            Student student = new Student("Ion");
            Details details = new Details(34);
            details.setStudent(student);

            studentRepository.save(student);
            detailsRepository.save(details);

            return student.getId();
        });

        executeInTransaction(() -> {
            Student student = studentRepository.findById(id).get();
            Details details = detailsRepository.findById(id).get();
            details.setStudent(null);

            detailsRepository.delete(details);
            studentRepository.delete(student);

            return null;
        });
    }
}
