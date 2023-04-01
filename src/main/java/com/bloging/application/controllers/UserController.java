package com.bloging.application.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloging.application.entityVos.UserVo;
import com.bloging.application.services.UserServices;

@RestController
@RequestMapping("/api/user/")
public class UserController {

	@Autowired
	private UserServices userServices;

	@PostMapping("/create-user")
	public ResponseEntity<UserVo> createUser( @Valid   @RequestBody UserVo userVo) {
		UserVo createUser = userServices.createUser(userVo);
		return new ResponseEntity<>(createUser, HttpStatus.CREATED);
	}

	@GetMapping("/allUsers")
	public ResponseEntity<List<UserVo>> allUsers() {
		List<UserVo> listOfUsers = userServices.getallUsers();
		return new ResponseEntity<>(listOfUsers, HttpStatus.CREATED);
	}

	@GetMapping("/getuser/{userId}")
	public ResponseEntity<UserVo> userById(@PathVariable("userId") Integer userId) {
		UserVo userInfo = userServices.getUserById(userId);
		return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
	}

	//ADmin Role
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<String> deleteById(@PathVariable("userId") Integer userId) {
		userServices.deleteUser(userId);
		return new ResponseEntity<>("User Deleted With Id " + userId, HttpStatus.OK);
	}

	@PutMapping("/updateuser/{userId}")
	public ResponseEntity<UserVo> updateUserById( @RequestBody UserVo userVo, @PathVariable("userId") Integer userId) {
		UserVo updateUser = userServices.updateUser(userVo, userId);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}

}
