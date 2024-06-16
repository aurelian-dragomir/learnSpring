package ing.hub.ingHub.jpa.one2many;

import ing.hub.ingHub.entity.jpa.one2many.Comment;
import ing.hub.ingHub.entity.jpa.one2many.Post;
import ing.hub.ingHub.jpa.BaseJpaTest;
import ing.hub.ingHub.repository.jpa.one2many.CommentRepository;
import ing.hub.ingHub.repository.jpa.one2many.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;


public class One2ManyTest extends BaseJpaTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void testSavePostWithComments() {
        executeInTransaction(() -> {
            Post post = new Post("post title 1");
            Comment comment1 = new Comment("comment 1");
            Comment comment2 = new Comment("comment 2");
            post.addComments(Arrays.asList(comment1, comment2));
            postRepository.save(post);
            return null;
        });
    }

    @Test
    public void testSaveSinglePost() {
        executeInTransaction(() -> {
            Post post = new Post("post title 1");
            postRepository.save(post);
            return null;
        });
    }

    @Test
    public void testAddCommentsForExistingPost() {
        executeInTransaction(() -> {
            Post post = new Post("post title 1");
            postRepository.save(post);
            post = postRepository.getReferenceById(post.getId());
            Comment comment = new Comment("comment 1");
            comment.setPost(post);
            commentRepository.save(comment);
            return null;
        });
    }

    @Test
    public void testDeletePostWithComments() {
        Long id = executeInTransaction(() -> {
            Post post = new Post("post title 1");
            Comment comment1 = new Comment("comment 1");
            Comment comment2 = new Comment("comment 2");

            post.addComments(Arrays.asList(comment1, comment2));
            postRepository.save(post);
            return post.getId();
        });

        executeInTransaction(() -> {
            Post post = postRepository.findPostWithComments(id);
            postRepository.delete(post);
            return null;
        });
    }

    @Test
    public void testDeleteSingleComment() {
        Long id = executeInTransaction(() -> {
            Post post = new Post("post title 1");
            Comment comment1 = new Comment("comment 1");
            Comment comment2 = new Comment("comment 2");

            post.addComments(Arrays.asList(comment1, comment2));
            postRepository.save(post);
            return comment1.getId();
        });

        executeInTransaction(() -> {
            Comment comment = commentRepository.findById(id).get();
            commentRepository.delete(comment);
            return null;
        });
    }

    @Test
    public void testLazyLoadingOfComment() {
        Long id = executeInTransaction(() -> {
            Post post = new Post("post title 1");
            Comment comment1 = new Comment("comment 1");
            Comment comment2 = new Comment("comment 2");

            post.addComments(Arrays.asList(comment1, comment2));
            postRepository.save(post);
            return post.getId();
        });

        executeInTransaction(() -> {
            postRepository.findById(id).get();
            return null;
        });
    }

    @Test
    public void testLazyLoadingOfPost() {
        Long id = executeInTransaction(() -> {
            Post post = new Post("post title 1");
            Comment comment1 = new Comment("comment 1");
            Comment comment2 = new Comment("comment 2");

            post.addComments(Arrays.asList(comment1, comment2));
            postRepository.save(post);
            return comment1.getId();
        });

        executeInTransaction(() -> {
            commentRepository.findById(id);
            return null;
        });
    }
}
