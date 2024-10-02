package com.example.DemoSpringBootAPI.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoSpringBootAPI.Service.Dtos.Category.CategoryResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Category.CreateCategoryRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Category.UpdateCategoryRequest;
import com.example.DemoSpringBootAPI.Service.Interfaces.ICategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	private final ICategoryService categoryService;

	public CategoryController(ICategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<List<CategoryResponse>> getAllPosts() {
		try {
			var categories = categoryService.getAllCategories();
			return ResponseEntity.ok(categories);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryResponse> getPostById(@PathVariable("id") Long id) {
		try {
			var category = categoryService.getCategoryById(id);
			return ResponseEntity.ok(category);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping
	public ResponseEntity<CategoryResponse> createPost(@RequestBody CreateCategoryRequest createCategory) {
		try {
			var category = categoryService.createCategory(createCategory);
			return ResponseEntity.ok(category);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<CategoryResponse> updateCategory(@PathVariable("id") Long id,
			@RequestBody UpdateCategoryRequest updateCategoryRequest) {
		try {
			CategoryResponse updatedCategory = categoryService.updateCategory(id, updateCategoryRequest);
			return ResponseEntity.ok(updatedCategory);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
		try {
			categoryService.deleteCategory(id);
			return ResponseEntity.noContent().build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
}
