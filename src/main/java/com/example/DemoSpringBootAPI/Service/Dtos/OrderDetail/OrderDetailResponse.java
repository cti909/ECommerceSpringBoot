package com.example.DemoSpringBootAPI.Service.Dtos.OrderDetail;

import java.time.LocalDateTime;

import com.example.DemoSpringBootAPI.Data.Entities.Order;
import com.example.DemoSpringBootAPI.Data.Entities.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse
{
	private Long id;
	private Integer quantity;
	private Long price;
	private Boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	private Long orderId;
	private Long productId;
}
