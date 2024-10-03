package com.example.DemoSpringBootAPI.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Product.CreateProductRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Product.ProductFilterRequest;
import com.example.DemoSpringBootAPI.Service.Interfaces.IProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	private final IProductService productService;

	public ProductController(IProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<?> getAllProducts() {
		try {
			var products = productService.getAllProducts();
			return ResponseEntity.ok(products);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/filter")
    public ResponseEntity<?> filterProducts(@RequestBody ProductFilterRequest filterRequest) {
        try {
        	var response = productService.filterProducts(filterRequest);
             return ResponseEntity.ok(response);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
		try {
			var product = productService.getProductById(id);
			return ResponseEntity.ok(product);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(consumes = { "multipart/form-data" })
	public ResponseEntity<?> createProduct(@RequestPart("images") List<MultipartFile> images,
			CreateProductRequest createProduct) {
		try {
			var product = productService.createProduct(createProduct, images);
			return ResponseEntity.ok(product);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
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
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
