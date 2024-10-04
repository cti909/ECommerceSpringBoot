package com.example.DemoSpringBootAPI.Service.Implements;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.DemoSpringBootAPI.Data.Entities.Cart;
import com.example.DemoSpringBootAPI.Data.Entities.Order;
import com.example.DemoSpringBootAPI.Data.Entities.OrderDetail;
import com.example.DemoSpringBootAPI.Data.Entities.Product;
import com.example.DemoSpringBootAPI.Data.Entities.Order;
import com.example.DemoSpringBootAPI.Data.Entities.User;
import com.example.DemoSpringBootAPI.Repository.CartRepository;
import com.example.DemoSpringBootAPI.Repository.OrderRepository;
import com.example.DemoSpringBootAPI.Repository.ProductRepository;
import com.example.DemoSpringBootAPI.Repository.UserRepository;
import com.example.DemoSpringBootAPI.Service.Dtos.Order.OrderRelatedResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Order.CreateOrderRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationResponse;
import com.example.DemoSpringBootAPI.Service.Interfaces.IOrderService;

@Service
public class OrderService implements IOrderService
{
	private final OrderRepository orderRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
	private final CartRepository cartRepository;
    private final ModelMapper modelMapper;

    public OrderService(ModelMapper modelMapper, OrderRepository orderRepository, UserRepository userRepository, CartRepository cartRepository, ProductRepository productRepository)
    {
        this.orderRepository = orderRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.cartRepository = cartRepository;
		this.modelMapper = modelMapper;
    }

    @Override
    public List<OrderRelatedResponse> getAllOrders()
    {
        return orderRepository.findAll().stream()
        		 .map(order -> modelMapper.map(order, OrderRelatedResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderRelatedResponse getOrderById(Long id)
    {
    	Order order = orderRepository.findById(id).orElse(null);
    	return order != null ? modelMapper.map(order, OrderRelatedResponse.class) : null;
    }

    @Override
    public PaginationResponse<OrderRelatedResponse> getOrdersByUser(Long userId, PaginationRequest paginationRequest) {
        Pageable pageable = PageRequest.of(
            paginationRequest.getPage() - 1,
            paginationRequest.getPageSize(),
            Sort.by(Sort.Direction.fromString(paginationRequest.getSortType()), paginationRequest.getSortBy())
        );
        Page<Order> orderPage = orderRepository.findByUserId(userId, pageable);
        List<OrderRelatedResponse> orderRelatedResponses = orderPage.getContent().stream()
            .map(order -> modelMapper.map(order, OrderRelatedResponse.class))
            .collect(Collectors.toList());
        
        return new PaginationResponse<>(
    		orderRelatedResponses,
    		orderPage.getNumber() + 1,
            orderPage.getTotalPages(),
            orderPage.getTotalElements(),
            orderPage.getSize()
        );
    }

    @Override
    @Transactional
    public OrderRelatedResponse createOrder(CreateOrderRequest createOrder)
    {
    	Order order = new Order();
    	order.setDescription(createOrder.getDescription());
        order.setAddress(createOrder.getAddress());
        order.setPhoneNumber(createOrder.getPhoneNumber());
    	
        User user = userRepository.findById(createOrder.getUserId())
        		.orElseThrow(() -> new RuntimeException("User not found with id: " + createOrder.getUserId()));
        order.setUser(user);
        
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (Long cartId : createOrder.getCartIds()) {
            Cart cart = cartRepository.findById(cartId)
                    .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
        
            Product product = cart.getProduct(); 
            if (product.getQuantity() < cart.getQuantity()) {
                throw new RuntimeException("Not enough product quantity in stock for product ID: " + product.getId());
            }

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setProduct(product);
            orderDetail.setQuantity(cart.getQuantity());
            orderDetail.setPrice(cart.getProduct().getPricePromotion());
            orderDetail.setOrder(order);
            product.setQuantity(product.getQuantity() - cart.getQuantity());
            orderDetails.add(orderDetail);
            
            // need fix to performance
            cartRepository.deleteById(cartId);
        }
        order.setOrderDetails(orderDetails);
        Order orderCreated = orderRepository.save(order);

		return orderCreated != null ? modelMapper.map(orderCreated, OrderRelatedResponse.class) : null;
    }

//    @Override
//    @Transactional
//    public OrderRelatedResponse updateOrder(Long id, UpdateOrderRequest updateOrderRequest) throws NoSuchFieldException, IllegalAccessException {
//        Optional<Order> optionalOrder = orderRepository.findById(id);
//
//        if (optionalOrder.isPresent()) {
//            Order entity = optionalOrder.get();
//
//            Field[] fields = UpdateOrderRequest.class.getDeclaredFields();
//            for (Field field : fields) {
//                field.setAccessible(true); // access private fields
//                Object newValue = field.get(updateOrderRequest);
//
//                if (newValue != null) {
//                    Field entityField = entity.getClass().getDeclaredField(field.getName());
//                    entityField.setAccessible(true);
//                    entityField.set(entity, newValue);
//                }
//            }
//            orderRepository.save(entity);
//            return modelMapper.map(entity, OrderRelatedResponse.class);
//        } else {
//            throw new RuntimeException("Order not found with id: " + id);
//        }
//    }
    
    @Override
    public void deleteOrder(Long id)
    {
//    	orderRepository.deleteById(id);
    	Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setIsDeleted(true);
            orderRepository.save(order);
        } else {        	
        	throw new RuntimeException("Order not found with id: " + id);
        }
    }
}
