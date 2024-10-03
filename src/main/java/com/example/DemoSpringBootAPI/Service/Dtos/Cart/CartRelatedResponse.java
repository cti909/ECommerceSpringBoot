package com.example.DemoSpringBootAPI.Service.Dtos.Cart;

import java.time.LocalDateTime;

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
public class CartRelatedResponse
{
	private Long id;
	private Integer quantity;
	private ProductResponse product;
	private UserResponse user;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
