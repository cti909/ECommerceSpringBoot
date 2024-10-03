package com.example.DemoSpringBootAPI.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoSpringBootAPI.Service.Dtos.Cart.CartRelatedResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Cart.CreateCartRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Cart.UpdateCartRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationResponse;
import com.example.DemoSpringBootAPI.Service.Interfaces.ICartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
	private final ICartService cartService;

	public CartController(ICartService cartService) {
		this.cartService = cartService;
	}

	@GetMapping
	public ResponseEntity<?> getAllCarts() {
		try {
			var Carts = cartService.getAllCarts();
			return ResponseEntity.ok(Carts);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCartById(@PathVariable("id") Long id) {
		try {
			var Cart = cartService.getCartById(id);
			return ResponseEntity.ok(Cart);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/user/{userId}")
    public ResponseEntity<?> getCartsByUser(@PathVariable("userId") Long userId, PaginationRequest paginationRequest) {

        PaginationResponse<CartRelatedResponse> cartResponses = cartService.getCartsByUser(userId, paginationRequest);
        return new ResponseEntity<>(cartResponses, HttpStatus.OK);
    }

	@PostMapping
	public ResponseEntity<?> createCart(@RequestBody CreateCartRequest createCart) {
		try {
			var Cart = cartService.createCart(createCart);
			return ResponseEntity.ok(Cart);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCart(@PathVariable("id") Long id, @RequestBody UpdateCartRequest updateCartRequest) {
    	try
		{
    		CartRelatedResponse updatedCart = cartService.updateCart(id, updateCartRequest);
            return ResponseEntity.ok(updatedCart);
		}
		catch (Exception ex)
		{
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

    }

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCart(@PathVariable("id") Long id) {
		try {
			cartService.deleteCart(id);
			return ResponseEntity.ok(true);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
}
