package com.bloging.application.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bloging.application.entity.Categories;
import com.bloging.application.entity.Post;
import com.bloging.application.entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	Page<Post> findByUser(User user, Pageable pageRequest);

	Page<Post> findByCategories(Categories categories, Pageable pageRequest);

	Page<Post> findByTitleContaining(String title, Pageable pageRequest);
}
