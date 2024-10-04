package com.example.DemoSpringBootAPI.Service.Dtos.Order;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderRequest
{
	private String description;
	private String address;
	private String phoneNumber;
	
	private Long userId;
	private List<Long> cartIds;
}
