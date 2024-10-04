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

import com.example.DemoSpringBootAPI.Service.Dtos.Order.OrderRelatedResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Order.CreateOrderRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationResponse;
import com.example.DemoSpringBootAPI.Service.Interfaces.IOrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
	private final IOrderService orderService;

	public OrderController(IOrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping
	public ResponseEntity<?> getAllOrders() {
		try {
			var Orders = orderService.getAllOrders();
			return ResponseEntity.ok(Orders);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable("id") Long id) {
		try {
			var Order = orderService.getOrderById(id);
			return ResponseEntity.ok(Order);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/user/{userId}")
    public ResponseEntity<?> getOrdersByUser(@PathVariable("userId") Long userId, PaginationRequest paginationRequest) {

        PaginationResponse<OrderRelatedResponse> OrderResponses = orderService.getOrdersByUser(userId, paginationRequest);
        return new ResponseEntity<>(OrderResponses, HttpStatus.OK);
    }

	@PostMapping
	public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest createOrder) {
		try {
			var Order = orderService.createOrder(createOrder);
			return ResponseEntity.ok(Order);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
//
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateOrder(@PathVariable("id") Long id, @RequestBody UpdateOrderRequest updateOrderRequest) {
//    	try
//		{
//    		OrderRelatedResponse updatedOrder = orderService.updateOrder(id, updateOrderRequest);
//            return ResponseEntity.ok(updatedOrder);
//		}
//		catch (Exception ex)
//		{
//			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
//		}
//
//    }

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
		try {
			orderService.deleteOrder(id);
			return ResponseEntity.ok(true);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
}
