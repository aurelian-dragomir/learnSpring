package ing.hub.ingHub.repository.jpa.one2many;

import ing.hub.ingHub.entity.jpa.one2many.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
