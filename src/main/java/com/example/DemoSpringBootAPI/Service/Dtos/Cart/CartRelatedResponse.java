package com.example.DemoSpringBootAPI.Service.Dtos.Cart;

import java.time.LocalDateTime;
import java.util.List;

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
public class CartRelatedResponse 
{
	private Long id;
	private Integer quantity;
	private ProductResponse product;
	private UserResponse user;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
