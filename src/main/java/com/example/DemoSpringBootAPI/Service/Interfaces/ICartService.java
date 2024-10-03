package com.example.DemoSpringBootAPI.Service.Interfaces;

import java.util.List;

import com.example.DemoSpringBootAPI.Service.Dtos.Cart.CartRelatedResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Cart.CreateCartRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Cart.UpdateCartRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationResponse;

public interface ICartService {
	public List<CartRelatedResponse> getAllCarts();
	public CartRelatedResponse getCartById(Long id);
	public PaginationResponse<CartRelatedResponse> getCartsByUser(Long userId, PaginationRequest paginationRequest);
	public CartRelatedResponse createCart(CreateCartRequest createCart);
	public CartRelatedResponse updateCart(Long id, UpdateCartRequest updateCart) throws NoSuchFieldException, IllegalAccessException;
	public void deleteCart(Long id);
}
