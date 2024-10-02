package com.example.DemoSpringBootAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DemoSpringBootAPI.Data.Entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
