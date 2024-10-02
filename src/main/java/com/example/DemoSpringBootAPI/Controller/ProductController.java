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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.DemoSpringBootAPI.Service.Dtos.Product.ProductResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Product.CreateProductRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Product.UpdateProductRequest;
import com.example.DemoSpringBootAPI.Service.Interfaces.IProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	private final IProductService productService;

	public ProductController(IProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		try {
			var products = productService.getAllProducts();
			return ResponseEntity.ok(products);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long id) {
		try {
			var product = productService.getProductById(id);
			return ResponseEntity.ok(product);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping(consumes = { "multipart/form-data" })
	public ResponseEntity<ProductResponse> createProduct(@RequestPart("images") List<MultipartFile> images,
			CreateProductRequest createProduct) {
		try {
			var product = productService.createProduct(createProduct, images);
			return ResponseEntity.ok(product);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

//    @PutMapping("/{id}")
//    public ResponseEntity<ProductResponse> updateProduct(@PathVariable("id") Long id, @RequestBody UpdateProductRequest updateProductRequest) {
//    	try
//		{
//    		ProductResponse updatedProduct = productService.updateProduct(id, updateProductRequest);
//            return ResponseEntity.ok(updatedProduct);
//		}
//		catch (Exception ex)
//		{
//			return ResponseEntity.badRequest().build();
//		}
//    	
//    }

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
		try {
			productService.deleteProduct(id);
			return ResponseEntity.ok(true);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
}
