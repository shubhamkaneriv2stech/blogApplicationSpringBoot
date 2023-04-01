package com.bloging.application.dao;

import java.util.List;

import com.bloging.application.entity.Comments;
import com.bloging.application.entity.Post;
import com.bloging.application.util.FilterCriteria;

public interface CategoriesDao {

	boolean ischeckedTitleIsUnique(String title, Integer id);

	List<Post>  fetchAllPostByCriteria(FilterCriteria filterCriteria);

	List<Comments>  fetchCommentsByID(Integer postId);
	
	Integer deleteUserFromRolesMapping(Integer id);
	
	
}
