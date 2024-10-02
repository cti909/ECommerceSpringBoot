package com.example.DemoSpringBootAPI.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoSpringBootAPI.Service.Dtos.User.CreateUserRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.User.RegisterUserRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.User.UserResponse;
import com.example.DemoSpringBootAPI.Service.Implements.UserService;
import com.example.DemoSpringBootAPI.Service.Interfaces.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	private final IUserService userService;

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<UserResponse>> getAllUsers() {
		try {
			var users = userService.getAllUsers();
			return ResponseEntity.ok(users);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable("id") Long id) {
		try {
			var user = userService.getUserById(id);
			return ResponseEntity.ok(user);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/register")
	public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterUserRequest registerUser) {
		try {
			var user = userService.registerUser(registerUser);
			return ResponseEntity.ok(user);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}

	}

	@PostMapping
	public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUser) {
		try {
			var user = userService.createUser(createUser);
			return ResponseEntity.ok(user);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
		try {
			userService.deleteUser(id);
			return ResponseEntity.noContent().build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}

	}
}
