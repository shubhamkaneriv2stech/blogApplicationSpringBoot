package com.bloging.application.entityVos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PostVo {


	private Integer id;

	@NotEmpty(message = "title is required")
	@Size(min = 4, message = "title Must be min 4 ")
	private String title;

	@NotEmpty(message = "content is required")
	@Size(min = 4, message = "content Must be min 10 ")
	private String content;

	private String imageName;

	private String addedDate;

	private CategoriesVo categories;

	private UserVo user;
	
	private List<CommentsVo> comments = new ArrayList<>();
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	public CategoriesVo getCategories() {
		return categories;
	}

	public void setCategories(CategoriesVo categories) {
		this.categories = categories;
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public List<CommentsVo> getComments() {
		return comments;
	}

	public void setComments(List<CommentsVo> comments) {
		this.comments = comments;
	}

	
}
