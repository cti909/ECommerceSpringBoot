package com.example.DemoSpringBootAPI.Service.Implements;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.DemoSpringBootAPI.Data.Entities.ProductColor;
import com.example.DemoSpringBootAPI.Repository.ProductColorRepository;
import com.example.DemoSpringBootAPI.Service.Dtos.ProductColor.ProductColorResponse;
import com.example.DemoSpringBootAPI.Service.Interfaces.IProductColorService;

@Service
public class ProductColorService implements IProductColorService
{
	private final ProductColorRepository productColorRepository;
//	private final ColorRepository colorRepository;
//	private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductColorService(ProductColorRepository productColorRepository, ModelMapper modelMapper)
    {
        this.productColorRepository = productColorRepository;
		this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductColorResponse> getAllProductColors()
    {
        return productColorRepository.findAll().stream()
        		 .map(productColor -> modelMapper.map(productColor, ProductColorResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductColorResponse getProductColorById(Long id)
    {
    	ProductColor productColor = productColorRepository.findById(id).orElse(null);
    	return productColor != null ? modelMapper.map(productColor, ProductColorResponse.class) : null;
    }

//    @Override
//    public ProductColorResponse createProductColor(CreateProductColorRequest createProductColor)
//    {
//    	ProductColor productColor = new ProductColor();
//
//    	Product product = productRepository.findById(createProductColor.getProductId()).orElse(null);
//    	Color color = colorRepository.findById(createProductColor.getColorId()).orElse(null);
//
//    	ProductColor productColorCreated = productColorRepository.save(productColor);
//		return productColorCreated != null ? modelMapper.map(productColorCreated, ProductColorResponse.class) : null;
//    }
//
//    @Override
//    @Transactional
//    public ProductColorResponse updateProductColor(Long id, UpdateProductColorRequest updateProductColorRequest) throws NoSuchFieldException, IllegalAccessException {
//        Optional<Color> optionalColor = productColorRepository.findById(id);
//
//        if (optionalColor.isPresent()) {
//            Color entity = optionalColor.get();
//
//            Field[] fields = UpdateColorRequest.class.getDeclaredFields();
//            for (Field field : fields) {
//                field.setAccessible(true); // access private fields
//                Object newValue = field.get(updateProductColorRequest);
//
//                if (newValue != null) {
//                    Field entityField = entity.getClass().getDeclaredField(field.getName());
//                    entityField.setAccessible(true);
//                    entityField.set(entity, newValue);
//                }
//            }
//            productColorRepository.save(entity);
//            return modelMapper.map(entity, ColorResponse.class);
//        } else {
//            throw new RuntimeException("Color not found with id: " + id);
//        }
//    }
//    @Override
//    public void deleteProductColor(Long id)
//    {
////        productColorRepository.deleteById(id);
//    	Optional<Color> optionalColor = productColorRepository.findById(id);
//        if (optionalColor.isPresent()) {
//            Color color = optionalColor.get();
//            color.setIsDeleted(true);
//            productColorRepository.save(color);
//        }
//        throw new RuntimeException("Color not found with id: " + id);
//    }
}
