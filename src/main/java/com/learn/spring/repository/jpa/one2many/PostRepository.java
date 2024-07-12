package ing.hub.ingHub.repository.jpa.one2many;

import ing.hub.ingHub.entity.jpa.one2many.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("from Post p join fetch p.comments where p.id=:id")
    Post findPostWithComments(@Param("id") Long id);

    @Query("select p.id from Post p")
    Page<Long> getPostIdsPage(Pageable page);

    @Query("from Post p join fetch p.comments where p.id in :ids")
    List<Post> getPostsForIds(@Param("ids") List<Long> ids);
}
