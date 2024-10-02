package com.example.DemoSpringBootAPI.Service.Implements;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.DemoSpringBootAPI.Data.Entities.Category;
import com.example.DemoSpringBootAPI.Data.Entities.Color;
import com.example.DemoSpringBootAPI.Data.Entities.Product;
import com.example.DemoSpringBootAPI.Data.Entities.ProductColor;
import com.example.DemoSpringBootAPI.Data.Entities.Style;
import com.example.DemoSpringBootAPI.Data.Entities.User;
import com.example.DemoSpringBootAPI.Data.EntityEnum.ProductSize;
import com.example.DemoSpringBootAPI.Repository.CategoryRepository;
import com.example.DemoSpringBootAPI.Repository.ColorRepository;
import com.example.DemoSpringBootAPI.Repository.ProductColorRepository;
import com.example.DemoSpringBootAPI.Repository.ProductRepository;
import com.example.DemoSpringBootAPI.Repository.StyleRepository;
import com.example.DemoSpringBootAPI.Repository.UserRepository;
import com.example.DemoSpringBootAPI.Service.Dtos.Product.CreateProductRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Product.UpdateProductRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Product.ProductResponse;
import com.example.DemoSpringBootAPI.Service.Interfaces.IProductService;
import com.example.DemoSpringBootAPI.Utils.UploadPhoto;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.OneToMany;

@Service
public class ProductService implements IProductService
{
	private final String uploadDirectory = "uploads/products/";
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;
	private final ProductColorRepository productColorRepository;
	private final ColorRepository colorRepository;
	private final StyleRepository styleRepository;
	private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, ModelMapper modelMapper, StyleRepository styleRepository, UserRepository userRepository, ProductColorRepository productColorRepository, CategoryRepository categoryRepository, ColorRepository colorRepository) 
    {
        this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.productColorRepository = productColorRepository;
		this.colorRepository = colorRepository;
		this.styleRepository = styleRepository;
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductResponse> getAllProducts() 
    {
        return productRepository.findAll().stream()
        		 .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }
    
    @Override
    public ProductResponse getProductById(Long id) 
    {
    	Product product = productRepository.findById(id).orElse(null);
    	return product != null ? modelMapper.map(product, ProductResponse.class) : null;
    }

    @Override
    @Transactional
    public ProductResponse createProduct(CreateProductRequest createProduct, List<MultipartFile> listUrlImage) 
    {
    	
    	Product product = new Product();
    	product.setName(createProduct.getName());
    	product.setDescription(createProduct.getDescription());
    	product.setPriceOrigin(createProduct.getPriceOrigin());
    	product.setPricePromotion(createProduct.getPricePromotion());
    	product.setDateExpire(createProduct.getDateExpire());
    	
    	// set string ProductSizes and convert XXL -> 2XL XXXL-> 3XL ...
    	List<String> productSizeValues = createProduct.getProductSizes().stream()
                .map(size -> size.getValue())
                .collect(Collectors.toList());
    	ObjectMapper objectMapper = new ObjectMapper();
    	try {
    	    String jsonProductSizes = objectMapper.writeValueAsString(productSizeValues);
    	    product.setProductSize(jsonProductSizes);
    	} catch (Exception e) {
    	    throw new RuntimeException("Error converting product sizes to JSON.");
    	}
    	
    	// upload
    	UploadPhoto.createUploadDirectory(uploadDirectory);
    	List<String> fileUrls = UploadPhoto.uploadFiles(listUrlImage, uploadDirectory);
    	try {
            String jsonFileUrls = new ObjectMapper().writeValueAsString(fileUrls);
            product.setListUrlImage(jsonFileUrls);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error convert link image to Json.");
        }
    	
    	Category category = categoryRepository.findById(createProduct.getCategoryId())
    			.orElseThrow(() -> new RuntimeException("Category not found with id: " + createProduct.getCategoryId()));
        Style style = styleRepository.findById(createProduct.getStyleId())
        		.orElseThrow(() -> new RuntimeException("Style not found with id: " + createProduct.getStyleId()));
        User user = userRepository.findById(createProduct.getUserId())
        		.orElseThrow(() -> new RuntimeException("User not found with id: " + createProduct.getUserId()));
        product.setCategory(category);
        product.setStyle(style);
        product.setUser(user);
        
    	
    	// Use relate in entity @OneToMany -> save
        List<ProductColor> productColors = createProduct.getColorIds().stream()
                .map(colorId -> {
                    Color color = colorRepository.findById(colorId)
                            .orElseThrow(() -> new RuntimeException("Color not found with id: " + colorId));
                    return new ProductColor(null, product, color);
                })
                .collect(Collectors.toList());

        product.setProductColors(productColors);
    	
		Product productCreated = productRepository.save(product);
		
//		// create productColor - for in query
//		List<Long> colorIds = createProduct.getColorId();
//	    if (colorIds != null && !colorIds.isEmpty()) {
//	        for (Long colorId : colorIds) {
//	            ProductColor productColor = new ProductColor();
//	            Color color = colorRepository.findById(colorId)
//	            		.orElseThrow(() -> new RuntimeException("Color not found with id: " + colorId));
//	            if (color != null) {
//	                productColor.setColor(color);
//	                productColor.setProduct(productCreated);
//	                productColorRepository.save(productColor);
//	            }
//	        }
//	    }
        
		return productCreated != null ? modelMapper.map(productCreated, ProductResponse.class) : null;
    }
    
    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, UpdateProductRequest updateProductRequest) throws NoSuchFieldException, IllegalAccessException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        
        if (optionalProduct.isPresent()) {
            Product entity = optionalProduct.get();

            Field[] fields = UpdateProductRequest.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // access private fields
                Object newValue = field.get(updateProductRequest);
                
                if (newValue != null) {
                    Field entityField = entity.getClass().getDeclaredField(field.getName());
                    entityField.setAccessible(true);
                    entityField.set(entity, newValue);
                }
            }
            productRepository.save(entity);
            return modelMapper.map(entity, ProductResponse.class);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }
    @Override
    public void deleteProduct(Long id) 
    {
//        productRepository.deleteById(id);
    	Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setIsDeleted(true);
            productRepository.save(product);
        } else {        	
        	throw new RuntimeException("Product not found with id: " + id);
        }
    }
}
