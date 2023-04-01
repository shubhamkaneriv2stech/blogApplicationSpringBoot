package com.bloging.application.entityVos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class CategoriesVo {

	private Integer id;

	@NotEmpty(message = "Title is Required")
	@Size(min = 3, max = 40, message = "Title length is 3 to 40")
	private String title;

	@NotEmpty(message = "Description is Required")
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
