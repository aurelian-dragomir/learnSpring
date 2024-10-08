package com.learn.spring.jpa.one2many;

import com.learn.spring.entity.jpa.one2many.Comment;
import com.learn.spring.entity.jpa.one2many.Post;
import com.learn.spring.jpa.BaseJpaTest;
import com.learn.spring.repository.jpa.one2many.CommentRepository;
import com.learn.spring.repository.jpa.one2many.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;


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


    @Test
    public void testPaging() {
        executeInTransaction(() -> {
            for (int i = 1; i <= 10; i++) {
                Post post = new Post("post title " + i);
                Comment comment1 = new Comment("comment " + i);
                Comment comment2 = new Comment("comment " + (i + 1));
                post.addComments(Arrays.asList(comment1, comment2));
                postRepository.save(post);
            }
            return null;
        });

        executeInTransaction(() -> {

            List<Long> firstFivePosts = postRepository.getPostIdsPage(PageRequest.of(0, 5))
                    .get().toList();
            List<Post> posts = postRepository.getPostsForIds(firstFivePosts);
            posts.forEach(System.out::println);

            return null;
        });
    }
}
