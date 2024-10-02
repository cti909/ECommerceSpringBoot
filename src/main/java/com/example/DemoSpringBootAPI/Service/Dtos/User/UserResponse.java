package com.example.DemoSpringBootAPI.Service.Dtos.User;

import java.time.LocalDateTime;

import com.example.DemoSpringBootAPI.Data.EntityEnum.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse 
{
	private Long id;
    private String name;
    private String username;
//    private String password;
    private String email;
    private String phoneNumber;
    private UserRole role;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
