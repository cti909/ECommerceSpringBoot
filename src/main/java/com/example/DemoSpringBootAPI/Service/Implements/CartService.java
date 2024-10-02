package com.example.DemoSpringBootAPI.Service.Implements;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.DemoSpringBootAPI.Data.Entities.Cart;
import com.example.DemoSpringBootAPI.Data.Entities.Product;
import com.example.DemoSpringBootAPI.Data.Entities.User;
import com.example.DemoSpringBootAPI.Repository.CartRepository;
import com.example.DemoSpringBootAPI.Repository.ProductRepository;
import com.example.DemoSpringBootAPI.Repository.UserRepository;
import com.example.DemoSpringBootAPI.Service.Dtos.Cart.CartRelatedResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Cart.CreateCartRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Cart.UpdateCartRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationResponse;
import com.example.DemoSpringBootAPI.Service.Interfaces.ICartService;

@Service
public class CartService implements ICartService
{
	private final CartRepository cartRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public CartService(ModelMapper modelMapper, CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository)
    {
        this.cartRepository = cartRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
    }

    @Override
    public List<CartRelatedResponse> getAllCarts()
    {
        return cartRepository.findAll().stream()
        		 .map(cart -> modelMapper.map(cart, CartRelatedResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public CartRelatedResponse getCartById(Long id)
    {
    	Cart cart = cartRepository.findById(id).orElse(null);
    	return cart != null ? modelMapper.map(cart, CartRelatedResponse.class) : null;
    }
    
    @Override
    public PaginationResponse<CartRelatedResponse> getCartsByUser(Long userId, PaginationRequest paginationRequest) {
        Pageable pageable = PageRequest.of(
            paginationRequest.getPage() - 1,
            paginationRequest.getPageSize(),
            Sort.by(Sort.Direction.fromString(paginationRequest.getSortType()), paginationRequest.getSortBy())
        );
        Page<Cart> cartPage = cartRepository.findByUserId(userId, pageable);
        List<CartRelatedResponse> cartRelatedResponses = cartPage.getContent().stream()
            .map(cart -> modelMapper.map(cart, CartRelatedResponse.class))
            .collect(Collectors.toList());
        return new PaginationResponse<>(
            cartRelatedResponses,
            cartPage.getNumber() + 1, 
            cartPage.getTotalPages(), 
            cartPage.getTotalElements() 
        );
    }

    @Override
    @Transactional
    public CartRelatedResponse createCart(CreateCartRequest createCart)
    {
    	boolean existsInCart = cartRepository.existsByUserIdAndProductId(createCart.getUserId(), createCart.getProductId());
        if (existsInCart) {
            throw new RuntimeException("Product already exists in the cart.");
        }

    	Cart cart = new Cart();
    	Product product = productRepository.findById(createCart.getProductId())
    			.orElseThrow(() -> new RuntimeException("Product not found with id: " + createCart.getProductId()));
        User user = userRepository.findById(createCart.getUserId())
        		.orElseThrow(() -> new RuntimeException("User not found with id: " + createCart.getUserId()));

        if (product.getQuantity() < createCart.getQuantity()) {
            throw new RuntimeException("Not enough product quantity in stock for product ID: "
                                                            + createCart.getProductId());
        }

        cart.setQuantity(createCart.getQuantity());
        cart.setProduct(product);
        cart.setUser(user);
		Cart cartCreated = cartRepository.save(cart);

		return cartCreated != null ? modelMapper.map(cartCreated, CartRelatedResponse.class) : null;
    }

    @Override
    @Transactional
    public CartRelatedResponse updateCart(Long id, UpdateCartRequest updateCartRequest) throws NoSuchFieldException, IllegalAccessException {
        Optional<Cart> optionalCart = cartRepository.findById(id);

        if (optionalCart.isPresent()) {
            Cart entity = optionalCart.get();

            Field[] fields = UpdateCartRequest.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // access private fields
                Object newValue = field.get(updateCartRequest);

                if (newValue != null) {
                    Field entityField = entity.getClass().getDeclaredField(field.getName());
                    entityField.setAccessible(true);
                    entityField.set(entity, newValue);
                }
            }
            cartRepository.save(entity);
            return modelMapper.map(entity, CartRelatedResponse.class);
        } else {
            throw new RuntimeException("Cart not found with id: " + id);
        }
    }
    @Override
    public void deleteCart(Long id)
    {
    	cartRepository.deleteById(id);
    }
}
