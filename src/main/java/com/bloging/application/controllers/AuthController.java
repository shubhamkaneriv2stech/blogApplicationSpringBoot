package com.bloging.application.controllers;

import java.net.URI;
import java.security.Principal;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloging.application.config.JwtTokenHelper;
import com.bloging.application.dao.UserRepo;
import com.bloging.application.entity.User;
import com.bloging.application.entityVos.JwtAuthRequest;
import com.bloging.application.entityVos.JwtAuthResponse;
import com.bloging.application.entityVos.UserVo;
import com.bloging.application.exception.CustomException;
import com.bloging.application.services.UserServices;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserServices userService;
	@Autowired
	private ModelMapper modelMapper;

	// get loggedin user data
	@Autowired
	private UserRepo userRepo;

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setUser(modelMapper.map(userDetails, UserVo.class));
	
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid Detials !!");
			throw new CustomException("Invalid username or password !!");
		}

	}

	// register new user api

	@PostMapping("/register")
	public ResponseEntity<UserVo> registerUser(@Valid @RequestBody UserVo userVo) {
		UserVo registeredUser = this.userService.registerNewUser(userVo);
		return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
	}

	@GetMapping("/current-user/")
	public ResponseEntity<UserVo> getUser(Principal principal) {
		User user = this.userRepo.findByEmail(principal.getName()).get();
		return new ResponseEntity<>(modelMapper.map(user, UserVo.class), HttpStatus.OK);
	}

}