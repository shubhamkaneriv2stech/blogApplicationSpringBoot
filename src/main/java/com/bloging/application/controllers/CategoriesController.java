package com.bloging.application.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloging.application.entityVos.CategoriesVo;
import com.bloging.application.services.CategoriesServices;

@RestController
@RequestMapping("/api/categories")
public class CategoriesController {

	@Autowired
	private CategoriesServices categoriesServices;

	@PostMapping("/create-category")
	public ResponseEntity<CategoriesVo> createCategory(@Valid @RequestBody CategoriesVo categoriesVo) {
		CategoriesVo createCategories = categoriesServices.createCategories(categoriesVo);
		return new ResponseEntity<>(createCategories, HttpStatus.CREATED);
	}

	@GetMapping("/allCategories")
	public ResponseEntity<List<CategoriesVo>> allCategories() {
		List<CategoriesVo> getallCategories = categoriesServices.getallCategories();
		return new ResponseEntity<>(getallCategories, HttpStatus.CREATED);
	}

	@GetMapping("/getCategory/{catrgoryId}")
	public ResponseEntity<CategoriesVo> userById(@PathVariable("catrgoryId") Integer catrgoryId) {

		CategoriesVo categoryById = categoriesServices.getCategoryById(catrgoryId);
		return new ResponseEntity<>(categoryById, HttpStatus.CREATED);
	}

	@DeleteMapping("/deletecategory/{catrgoryId}")
	public ResponseEntity<String> deleteById(@PathVariable("catrgoryId") Integer catrgoryId) {
		categoriesServices.deleteCategory(catrgoryId);
		return new ResponseEntity<>("Category Deleted With Id " + catrgoryId, HttpStatus.OK);
	}

	@PutMapping("/updatecategory/{catrgoryId}")
	public ResponseEntity<CategoriesVo> updateUserById(@Valid @RequestBody CategoriesVo categoriesVo,
			@PathVariable("catrgoryId") Integer catrgoryId) {
		CategoriesVo updateCategories = categoriesServices.updateCategories(categoriesVo, catrgoryId);
		return new ResponseEntity<>(updateCategories, HttpStatus.OK);
	}
}
