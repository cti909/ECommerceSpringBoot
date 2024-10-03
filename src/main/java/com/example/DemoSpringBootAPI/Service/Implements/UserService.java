package com.example.DemoSpringBootAPI.Service.Implements;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.DemoSpringBootAPI.Data.Entities.User;
import com.example.DemoSpringBootAPI.Repository.UserRepository;
import com.example.DemoSpringBootAPI.Service.Dtos.User.CreateUserRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.User.RegisterUserRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.User.UserResponse;
import com.example.DemoSpringBootAPI.Service.Interfaces.IUserService;


@Service
public class UserService implements IUserService
{
	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

	@Override
	public List<UserResponse> getAllUsers()
	{
	    return userRepository.findAll().stream()
	    		.map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
	}

	@Override
	public UserResponse getUserById(Long id)
	{
		User user = userRepository.findById(id).orElse(null);
		return user != null ? modelMapper.map(user, UserResponse.class) : null;
	}

	@Override
	public UserResponse registerUser(RegisterUserRequest registerUser)
	{
		User user = new User();
	    user.setName(registerUser.getName());
	    user.setUsername(registerUser.getUsername());
	    user.setPassword(new BCryptPasswordEncoder().encode(registerUser.getPassword()));
	    user.setEmail(registerUser.getEmail());
	    user.setPhoneNumber(registerUser.getPhoneNumber());

		User userCreated = userRepository.save(user);
		return userCreated != null ? modelMapper.map(userCreated, UserResponse.class) : null;
	}

	@Override
	public UserResponse createUser(CreateUserRequest createUser)
	{
		User user = new User();
	    user.setName(createUser.getName());
	    user.setUsername(createUser.getUsername());
	    user.setPassword(new BCryptPasswordEncoder().encode(createUser.getPassword()));
	    user.setEmail(createUser.getEmail());
	    user.setPhoneNumber(createUser.getPhoneNumber());
	    user.setRole(createUser.getRole());

		User userCreated = userRepository.save(user);
		return userCreated != null ? modelMapper.map(userCreated, UserResponse.class) : null;
	}

	@Override
	public void deleteUser(Long id)
	{
	    userRepository.deleteById(id);
	}
}
