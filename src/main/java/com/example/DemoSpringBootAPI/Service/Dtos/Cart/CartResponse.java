package com.example.DemoSpringBootAPI.Service.Dtos.Cart;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse
{
	private Long id;
	private Integer quantity;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
