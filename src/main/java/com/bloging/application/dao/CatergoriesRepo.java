package com.bloging.application.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloging.application.entity.Categories;
import com.bloging.application.entityVos.CommentsVo;

public interface CatergoriesRepo extends JpaRepository<Categories, Integer> {

	

}
