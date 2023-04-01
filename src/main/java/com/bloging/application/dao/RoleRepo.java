package com.bloging.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloging.application.entity.Roles;

public interface RoleRepo  extends JpaRepository<Roles, Integer>{

}