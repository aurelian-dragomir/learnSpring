package com.learn.spring.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public class BaseRepository<T, ID> {
    @Autowired
    private JdbcClient jdbcClient;

    public Optional<T> queryForOne(String sql, MapSqlParameterSource parameterSource, Class<T> clazz) {
        return jdbcClient.sql(sql)
                .paramSource(parameterSource)
                .query(clazz)
                .optional();
    }

    public List<T> queryForList(String sql, MapSqlParameterSource paramSource, Class<T> clazz) {
        return jdbcClient.sql(sql)
                .query(clazz)
                .list();
    }

    public T save(String sql, T t) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int affectedRows = jdbcClient.sql(sql)
                .paramSource(new BeanPropertySqlParameterSource(t))
                .update(keyHolder);

        if (affectedRows > 0) {
            setField(t, keyHolder.getKey().longValue());
            return t;
        }
        throw new RuntimeException("Saving post failed");
    }

    public boolean update(String sql, T t) {
        return jdbcClient.sql(sql)
                .paramSource(t)
                .update() > 0;
    }

    public boolean delete(String sql, ID id) {
        return jdbcClient.sql(sql)
                .param("id", id)
                .update() > 0;
    }

    private void setField(T t, Object value) {
        try {
            ReflectionUtils.setField(t.getClass().getField("id"), t, value);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
