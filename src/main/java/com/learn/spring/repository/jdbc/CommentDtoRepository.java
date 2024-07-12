package com.learn.spring.repository.jdbc;

import com.learn.spring.entity.jdbc.one2many.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;
import java.util.Optional;

public class CommentDtoRepository implements JdbcRepository<CommentDto, Long> {

    @Autowired
    private JdbcClient jdbcClient;

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Optional<CommentDto> findById(Long id) {
        return jdbcClient.sql("select * from comment where id=:id")
                .param("id", id)
                .query(CommentDto.class)
                .optional();
    }

    @Override
    public List<CommentDto> findALl() {
        return jdbcClient.sql("select * from comment")
                .query(CommentDto.class)
                .list();
    }

    @Override
    public CommentDto save(CommentDto commentDto) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRows = jdbcClient.sql("insert into comment(text, post_id) values(:text, :postId)")
                .paramSource(new BeanPropertySqlParameterSource(commentDto))
                .update(keyHolder);

        if (affectedRows > 0) {
            commentDto.setId(keyHolder.getKey().longValue());
            return commentDto;
        }
        throw new RuntimeException("Saving comment failed");
    }

    @Override
    public boolean update(CommentDto commentDto) {
        return jdbcClient.sql("update comment set text=:text, post_id=:post_id where id=:id")
                .paramSource(commentDto)
                .update() > 0;
    }

    @Override
    public boolean delete(Long id) {
        return jdbcClient.sql("delete from comment where id=:id")
                .param("id", id)
                .update() > 0;
    }

    public List<CommentDto> findAllForPostId(Long postId) {
        return jdbcClient.sql("select * from comment where post_id=:postId")
                .param("postId", postId)
                .query(CommentDto.class)
                .list();
    }

    public boolean deleteByPostId(Long postId) {
        return jdbcClient.sql("delete from comment where post_id=:postId")
                .param("postId", postId)
                .update() > 0;
    }

    public int[] batchInsert(List<CommentDto> comments) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        SqlParameterSource[] params = comments.stream()
                .map(comment -> new BeanPropertySqlParameterSource(comment))
                .toList()
                .toArray(new SqlParameterSource[comments.size()]);
        int[] affectedRows = jdbcTemplate.batchUpdate("insert into comment(text, post_id) values(:text, :postId)"
                , params, keyHolder);
        int a = 0;
        return affectedRows;
    }
}
