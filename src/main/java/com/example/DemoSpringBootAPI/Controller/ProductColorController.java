package com.example.DemoSpringBootAPI.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoSpringBootAPI.Service.Interfaces.IProductColorService;

@RestController
@RequestMapping("/api/productColor")
public class ProductColorController {
	private final IProductColorService productColorService;

	public ProductColorController(IProductColorService productColorService) {
		this.productColorService = productColorService;
	}

	@GetMapping
	public ResponseEntity<?> getAllproductColors() {
		try {
			var productColors = productColorService.getAllProductColors();
			return ResponseEntity.ok(productColors);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getProductColorById(@PathVariable("id") Long id) {
		try {
			var productColor = productColorService.getProductColorById(id);
			return ResponseEntity.ok(productColor);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

//    @PostMapping
//    public ResponseEntity<ProductColorResponse> createproductColor(@RequestBody CreateProductColorRequest createproductColor) {
//    	try
//		{
//			var productColor = productColorService.createProductColor(createproductColor);
//			return ResponseEntity.ok(productColor);
//		}
//		catch (Exception ex)
//		{
//			return ResponseEntity.badRequest().build();
//		}
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ProductColorResponse> updateproductColor(@PathVariable("id") Long id, @RequestBody UpdateProductColorRequest updateProductColorRequest) {
//    	try
//		{
//    		productColorResponse updatedproductColor = productColorService.updateproductColor(id, updateproductColorRequest);
//            return ResponseEntity.ok(updatedproductColor);
//		}
//		catch (Exception ex)
//		{
//			return ResponseEntity.badRequest().build();
//		}
//
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProductColor(@PathVariable("id") Long id) {
//    	try
//		{
//    		productColorService.deleteProductColor(id);
//		    return ResponseEntity.noContent().build();
//		}
//		catch (Exception ex)
//		{
//			return ResponseEntity.badRequest().build();
//		}
//    }
}
