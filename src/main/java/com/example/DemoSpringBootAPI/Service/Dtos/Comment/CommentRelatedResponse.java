package com.example.DemoSpringBootAPI.Service.Dtos.Comment;

import java.time.LocalDateTime;
import java.util.List;

import com.example.DemoSpringBootAPI.Service.Dtos.Product.ProductResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.User.UserResponse;

import lombok.AllArgsConstructor;
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
	private Integer star;
	private Boolean isReview;
	private Boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private ProductResponse product;
	private UserResponse user;
	private List<CommentChildResponse> children;
}