package com.example.DemoSpringBootAPI.Service.Dtos.User;

import com.example.DemoSpringBootAPI.Data.EntityEnum.UserRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {
	private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private UserRole role;
}
