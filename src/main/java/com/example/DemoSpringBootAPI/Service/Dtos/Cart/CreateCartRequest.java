package com.example.DemoSpringBootAPI.Service.Dtos.Cart;

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
