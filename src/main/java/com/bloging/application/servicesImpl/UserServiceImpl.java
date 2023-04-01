package com.bloging.application.servicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloging.application.dao.CategoriesDao;
import com.bloging.application.dao.RoleRepo;
import com.bloging.application.dao.UserRepo;
import com.bloging.application.entity.Roles;
import com.bloging.application.entity.User;
import com.bloging.application.entityVos.UserVo;
import com.bloging.application.exception.CustomException;
import com.bloging.application.exception.ResourceNotFoundException;
import com.bloging.application.services.UserServices;
import com.bloging.application.util.AppContants;

@Service
@Transactional
public class UserServiceImpl implements UserServices {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CategoriesDao categoriesDao;

	@Override
	public UserVo createUser(UserVo userVo) {

		User saveUser = dtoUser(userVo);
		User save = userRepo.save(saveUser);

		return userToVo(save);
	}

	@Override
	public UserVo updateUser(UserVo userVo, Integer userId) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setAbout(userVo.getAbout() != null ? userVo.getAbout() : user.getAbout());
		user.setEmail(userVo.getEmail());
		user.setName(userVo.getName());
		user.setPassword(userVo.getPassword() != null ? this.passwordEncoder.encode(userVo.getPassword())
				: user.getPassword());

		User updatedUser = userRepo.save(user);
		return userToVo(updatedUser);
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		categoriesDao.deleteUserFromRolesMapping(userId);

		userRepo.delete(user);
	}

	@Override
	public List<UserVo> getallUsers() {
		List<User> findAllUsers = userRepo.findAll();

		return findAllUsers.stream().map(this::userToVo).collect(Collectors.toList());
	}

	private User dtoUser(UserVo userVo) {

		User user = modelMapper.map(userVo, User.class);

//		User user = new User();
//		user.setId(userVo.getId());
//		user.setAbout(userVo.getAbout());
//		user.setEmail(userVo.getEmail());
//		user.setName(userVo.getName());
//		user.setPassword(userVo.getPassword());
		return user;

	}

	private UserVo userToVo(User user) {
		UserVo userVo = modelMapper.map(user, UserVo.class);

//		UserVo userVo = new UserVo();
////		userVo.setId(user.getId());
////		userVo.setAbout(user.getAbout());
////		userVo.setEmail(user.getEmail());
////		userVo.setName(user.getName());
////		userVo.setPassword(user.getPassword());
		return userVo;

	}

	@Override
	public UserVo getUserById(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return userToVo(user);
	}

	@Override
	public UserVo registerNewUser(UserVo userVo) {

		User user = this.modelMapper.map(userVo, User.class);

		// encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));

		// roles
		Roles role = this.roleRepo.findById(AppContants.NORMAL_USER).get();

		user.getRoles().add(role);

		User newUser = null;

		try {
			newUser = this.userRepo.save(user);
		} catch (Exception e) {
			throw new CustomException("Duplicate Entry Or Something went Wrong While Saving data");
		}

		return this.modelMapper.map(newUser, UserVo.class);
	}

}
