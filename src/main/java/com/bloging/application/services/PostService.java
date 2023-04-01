package com.bloging.application.services;

import com.bloging.application.entityVos.PostVo;
import com.bloging.application.exception.DataTableListResponse;

public interface PostService {

	PostVo createPost(PostVo postVo, Integer userId, Integer categoriesId);

	void deletePost(Integer postId);

	PostVo getPostById(Integer postId);

	DataTableListResponse<PostVo> getAllPostByUser(Integer userId, Integer pageNumber, Integer pageSize);

	DataTableListResponse<PostVo> getAllPostByCategories(Integer categoriesId, Integer pageNumber, Integer pageSize);

	DataTableListResponse<PostVo> searchPosts(Integer pageNumber, Integer pageSize, String keyword);

	// List<PostVo> getallPost(FilterCriteria filterCriteria);

	<T> DataTableListResponse<T> getallPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	PostVo updatePost(PostVo postDto, Integer postId);
}
