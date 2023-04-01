package com.bloging.application.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bloging.application.dao.CategoriesDao;
import com.bloging.application.dao.CatergoriesRepo;
import com.bloging.application.entity.Categories;
import com.bloging.application.entityVos.CategoriesVo;
import com.bloging.application.exception.CustomException;
import com.bloging.application.exception.ResourceNotFoundException;
import com.bloging.application.services.CategoriesServices;

@Service
public class CatergoriesServiceImpl implements CategoriesServices {

	@Autowired
	private CatergoriesRepo catergoriesRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	CategoriesDao categoriesDao;

	private Categories dtoToCategories(CategoriesVo categoriesVo) {

		return modelMapper.map(categoriesVo, Categories.class);

	}

	private CategoriesVo categoriesToVo(Categories categories) {
		return modelMapper.map(categories, CategoriesVo.class);

	}

	@Override
	public CategoriesVo createCategories(CategoriesVo categoriesVo) {

		checkedTitleIsUnique(categoriesVo.getTitle(), categoriesVo.getId());

		Categories categories = dtoToCategories(categoriesVo);
		Categories categoriesSave = catergoriesRepo.save(categories);

		return categoriesToVo(categoriesSave);
	}

	private void checkedTitleIsUnique(String title, Integer integer) {

		boolean isUnique = categoriesDao.ischeckedTitleIsUnique(title, integer);

		if (isUnique) {
			throw new CustomException("Must be unique Category name");
		}

	}

	@Override
	public CategoriesVo updateCategories(CategoriesVo categoriesVo, Integer categoriesId) {

		Categories categories = catergoriesRepo.findById(categoriesId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "Id", categoriesId));

		checkedTitleIsUnique(categoriesVo.getTitle(), categoriesId);

		categories.setDescription(categoriesVo.getDescription());
		categories.setTitle(categoriesVo.getTitle());
		return categoriesToVo(catergoriesRepo.save(categories));
	}

	@Override
	public CategoriesVo getCategoryById(Integer categoriesId) {
		Categories categories = catergoriesRepo.findById(categoriesId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "Id", categoriesId));
		return categoriesToVo(categories);
	}

	@Override
	public void deleteCategory(Integer categoriesId) {
		Categories categories = catergoriesRepo.findById(categoriesId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "Id", categoriesId));

		catergoriesRepo.delete(categories);
	}

	@Override
	public List<CategoriesVo> getallCategories() {
		List<Categories> findAllCaterories = catergoriesRepo.findAll();

		return findAllCaterories.stream().map(this::categoriesToVo).collect(Collectors.toList());

	}

}
