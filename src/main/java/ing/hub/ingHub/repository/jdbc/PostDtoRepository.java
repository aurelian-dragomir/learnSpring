package ing.hub.ingHub.repository.jdbc;

import ing.hub.ingHub.entity.jdbc.one2many.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Optional;

public class PostDtoRepository implements JdbcRepository<PostDto, Long> {

    @Autowired
    private JdbcClient jdbcClient;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<PostDto> findById(Long id) {
        return jdbcClient.sql("select * from post where id=:id")
                .param("id", id)
                .query(PostDto.class)
                .optional();
    }

    @Override
    public List<PostDto> findALl() {
        return jdbcClient.sql("select * from post")
                .query(PostDto.class)
                .list();
    }

    @Override
    public PostDto save(PostDto postDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRows = jdbcClient.sql("insert into post(title) values(:title)")
                .paramSource(new BeanPropertySqlParameterSource(postDto))
                .update(keyHolder);

        if (affectedRows > 0) {
            postDto.setId(keyHolder.getKey().longValue());
            return postDto;
        }
        throw new RuntimeException("Saving post failed");
    }

    @Override
    public boolean update(PostDto postDto) {
        return jdbcClient.sql("update post set title=:title where id=:id")
                .paramSource(postDto)
                .update() > 0;
    }

    @Override
    public boolean delete(Long id) {
        return jdbcClient.sql("delete from post where id=:id")
                .param("id", id)
                .update() > 0;
    }

    public int[] batchInsert(List<PostDto> posts) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource[] params = posts.stream()
                .map(post -> new BeanPropertySqlParameterSource(post))
                .toList()
                .toArray(new SqlParameterSource[posts.size()]);
        int[] affectedRows = jdbcTemplate.batchUpdate("insert into post(title) values(:title)", params, keyHolder);

        int a = 0;

        return affectedRows;
    }
}
