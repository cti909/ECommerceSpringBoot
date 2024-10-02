package com.example.DemoSpringBootAPI.Service.Interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.DemoSpringBootAPI.Service.Dtos.Product.CreateProductRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Product.UpdateProductRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Product.ProductResponse;

public interface IProductService {
	public List<ProductResponse> getAllProducts();
	public ProductResponse getProductById(Long id);
	public ProductResponse createProduct(CreateProductRequest createProduct, List<MultipartFile> listUrlImage);
	public ProductResponse updateProduct(Long id, UpdateProductRequest updateProduct) throws NoSuchFieldException, IllegalAccessException;
	public void deleteProduct(Long id);
}
