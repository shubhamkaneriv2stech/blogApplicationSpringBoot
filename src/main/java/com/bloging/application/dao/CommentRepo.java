package com.bloging.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloging.application.entity.Comments;

public interface CommentRepo extends JpaRepository<Comments, Integer> {

}
