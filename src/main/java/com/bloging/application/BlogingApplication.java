package com.bloging.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bloging.application.dao.RoleRepo;
import com.bloging.application.entity.Roles;
import com.bloging.application.util.AppContants;

@SpringBootApplication
public class BlogingApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;
	public static void main(String[] args) {
		SpringApplication.run(BlogingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println(this.passwordEncoder.encode("Shubham@9"));

		try {

			Roles role = new Roles();
			role.setId(AppContants.ADMIN_USER);
			role.setName("ROLE_ADMIN");

			Roles role1 = new Roles();
			role1.setId(AppContants.NORMAL_USER);
			role1.setName("ROLE_NORMAL");

			List<Roles> roles = List.of(role, role1);

			List<Roles> result = this.roleRepo.saveAll(roles);

			result.forEach(r -> {
				System.out.println(r.getName());
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
