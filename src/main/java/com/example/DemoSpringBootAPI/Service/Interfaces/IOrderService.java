package com.example.DemoSpringBootAPI.Service.Interfaces;

import java.util.List;

import com.example.DemoSpringBootAPI.Service.Dtos.Order.OrderRelatedResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Order.CreateOrderRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationResponse;

public interface IOrderService {
	public List<OrderRelatedResponse> getAllOrders();
	public OrderRelatedResponse getOrderById(Long id);
	public PaginationResponse<OrderRelatedResponse> getOrdersByUser(Long userId, PaginationRequest paginationRequest);
	public OrderRelatedResponse createOrder(CreateOrderRequest createOrder);
//	public OrderRelatedResponse updateOrder(Long id, UpdateOrderRequest updateOrder) throws NoSuchFieldException, IllegalAccessException;
	public void deleteOrder(Long id);
}
