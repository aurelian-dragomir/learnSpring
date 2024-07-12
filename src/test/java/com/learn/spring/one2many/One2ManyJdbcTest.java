package ing.hub.ingHub.jdbc.one2many;

import ing.hub.ingHub.entity.jdbc.one2many.CommentDto;
import ing.hub.ingHub.entity.jdbc.one2many.PostDto;
import ing.hub.ingHub.repository.jdbc.CommentDtoRepository;
import ing.hub.ingHub.repository.jdbc.PostDtoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Import({PostDtoRepository.class, CommentDtoRepository.class})
public class One2ManyJdbcTest extends BaseJdbcTest {

    @Autowired
    private PostDtoRepository postRepository;

    @Autowired
    private CommentDtoRepository commentRepository;

    @Test
    public void testSavePost() {
        executeInTransaction(() -> {
            PostDto postDto = new PostDto("java ex");
            postRepository.save(postDto);
            return null;
        });
    }

    @Test
    public void testSavePostWithComment() {
        executeInTransaction(() -> {
            PostDto postDto = new PostDto("post1");
            postRepository.save(postDto);

            CommentDto comment1 = new CommentDto("comment1", postDto.getId());
            CommentDto comment2 = new CommentDto("comment2", postDto.getId());
            commentRepository.batchInsert(Arrays.asList(comment1, comment2));
            return null;
        });
    }

    @Test
    public void testDeletePostWithComment() {
        Long id = executeInTransaction(() -> {
            PostDto postDto = new PostDto("java ex");
            postRepository.save(postDto);

            CommentDto commentDto = new CommentDto("java is the new cobol", postDto.getId());
            commentRepository.save(commentDto);
            return postDto.getId();
        });

        executeInTransaction(() -> {
            commentRepository.deleteByPostId(id);
            postRepository.delete(id);
            return null;
        });
    }

    @Test
    public void testBatchInsertPost() {
        executeInTransaction(() -> {
            List<PostDto> posts = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                posts.add(new PostDto("title " + i));
            }

            postRepository.batchInsert(posts);

            return null;
        });
    }
}
