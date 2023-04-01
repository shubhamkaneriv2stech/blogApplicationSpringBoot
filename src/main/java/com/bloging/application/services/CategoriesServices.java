package com.bloging.application.services;

import java.util.List;

import com.bloging.application.entityVos.CategoriesVo;

public interface CategoriesServices {

	CategoriesVo createCategories(CategoriesVo categoriesVo);

	CategoriesVo updateCategories(CategoriesVo categoriesVo, Integer categoriesId);

	CategoriesVo getCategoryById(Integer categoriesId);

	void deleteCategory(Integer categoriesId);

	List<CategoriesVo> getallCategories();

}
