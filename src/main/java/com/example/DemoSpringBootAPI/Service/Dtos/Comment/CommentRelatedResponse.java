package com.example.DemoSpringBootAPI.Service.Dtos.Comment;

import java.time.LocalDateTime;
import java.util.List;

import com.example.DemoSpringBootAPI.Data.Entities.Comment;
import com.example.DemoSpringBootAPI.Data.Entities.Product;
import com.example.DemoSpringBootAPI.Data.Entities.User;
import com.example.DemoSpringBootAPI.Data.EntityEnum.UserRole;
import com.example.DemoSpringBootAPI.Service.Dtos.Product.ProductResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.User.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRelatedResponse 
{
	private Long id;
	private String content;
	private String star;
	private Boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private ProductResponse product;
	private UserResponse user;
//	private Comment parent;
}
