package com.bloging.application.services;

import java.util.List;

import com.bloging.application.entityVos.UserVo;

public interface UserServices {

	UserVo registerNewUser(UserVo userVo);

	UserVo createUser(UserVo userVo);

	UserVo updateUser(UserVo userVo, Integer userId);

	UserVo getUserById(Integer userId);

	void deleteUser(Integer userId);

	List<UserVo> getallUsers();

}
