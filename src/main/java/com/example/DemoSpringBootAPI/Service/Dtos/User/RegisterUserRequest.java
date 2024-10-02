package com.example.DemoSpringBootAPI.Service.Dtos.User;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserRequest 
{
    private String name;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
}
