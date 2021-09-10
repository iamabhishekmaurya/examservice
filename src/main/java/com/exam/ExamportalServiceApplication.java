package com.exam;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamportalServiceApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ExamportalServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		User user = new User();
//		user.setUserName("abhishek23");
//		user.setEmail("abc@gmail.com");
//		user.setPassword("abc");
//		user.setFirstName("Abhishek");
//		user.setLastName("Maurya");
//		user.setPhone("980770192");
//		user.setProfile("default.png");
//
//		Role role = new Role();
//		role.setRoleId(1L);
//		role.setRoleName("Admin");
//
//		Set<UserRole> userRoles = new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//
//		userRoles.add(userRole);
//
//		User user1 = userService.createUser(user, userRoles);
//		System.out.println(user1.getUserName());
	}
}
