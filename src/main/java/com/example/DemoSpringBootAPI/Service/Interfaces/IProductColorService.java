package com.example.DemoSpringBootAPI.Service.Interfaces;

import java.util.List;

import com.example.DemoSpringBootAPI.Service.Dtos.ProductColor.ProductColorResponse;

public interface IProductColorService {
	public List<ProductColorResponse> getAllProductColors();
	public ProductColorResponse getProductColorById(Long id);
//	public ProductColorResponse createProductColor(CreateProductColorRequest createProductColor);
//	public ProductColorResponse updateProductColor(Long id, UpdateProductColorRequest updateProductColor) throws NoSuchFieldException, IllegalAccessException;
//	public void deleteProductColor(Long id);
}
