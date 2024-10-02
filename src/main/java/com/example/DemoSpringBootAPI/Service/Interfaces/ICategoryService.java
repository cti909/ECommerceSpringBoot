package com.example.DemoSpringBootAPI.Service.Interfaces;

import java.util.List;

import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Category.CategoryResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Category.CreateCategoryRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Category.UpdateCategoryRequest;

public interface ICategoryService {
	public List<CategoryResponse> getAllCategories();
	public CategoryResponse getCategoryById(Long id);
	public CategoryResponse createCategory(CreateCategoryRequest createCategory);
	public CategoryResponse updateCategory(Long id, UpdateCategoryRequest updateCategoryRequest) throws NoSuchFieldException, IllegalAccessException;
	public void deleteCategory(Long id);
}
