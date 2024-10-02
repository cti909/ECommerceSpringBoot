package com.example.DemoSpringBootAPI.Service.Dtos.Cart;

import com.example.DemoSpringBootAPI.Data.Entities.Product;
import com.example.DemoSpringBootAPI.Data.Entities.User;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCartRequest 
{
	private Integer quantity;
	private Long productId;
	private Long userId;
}
