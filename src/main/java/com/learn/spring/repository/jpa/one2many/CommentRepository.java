package com.learn.spring.repository.jpa.one2many;

import com.learn.spring.entity.jpa.one2many.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
