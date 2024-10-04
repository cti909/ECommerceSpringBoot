package com.example.DemoSpringBootAPI.Service.Dtos.Order;

import java.time.LocalDateTime;
import java.util.List;

import com.example.DemoSpringBootAPI.Service.Dtos.OrderDetail.OrderDetailResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.User.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRelatedResponse
{
	private Long id;
	private String description;
	private String address;
	private String phoneNumber;
	private Boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	List<OrderDetailResponse> orderDetails;
	private UserResponse user;
}
