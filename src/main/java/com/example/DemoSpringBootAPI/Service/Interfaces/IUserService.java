package com.example.DemoSpringBootAPI.Service.Interfaces;

import java.util.List;

import com.example.DemoSpringBootAPI.Service.Dtos.User.CreateUserRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.User.RegisterUserRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.User.UserResponse;

public interface IUserService {
	public List<UserResponse> getAllUsers();
	public UserResponse getUserById(Long id);
	public UserResponse registerUser(RegisterUserRequest registerUser);
	public UserResponse createUser(CreateUserRequest registerUser); // Admin create
	public void deleteUser(Long id);
}
