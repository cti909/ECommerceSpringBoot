package com.example.DemoSpringBootAPI.Service.Implements;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.DemoSpringBootAPI.Data.Entities.Style;
import com.example.DemoSpringBootAPI.Repository.StyleRepository;
import com.example.DemoSpringBootAPI.Service.Dtos.Style.StyleResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Style.UpdateStyleRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Style.CreateStyleRequest;
import com.example.DemoSpringBootAPI.Service.Interfaces.IStyleService;

@Service
public class StyleService implements IStyleService{
	private final StyleRepository styleRepository;
    private final ModelMapper modelMapper;

    public StyleService(StyleRepository styleRepository, ModelMapper modelMapper) 
    {
        this.styleRepository = styleRepository;
		this.modelMapper = modelMapper;
    }

    @Override
    public List<StyleResponse> getAllStyles() 
    {
        return styleRepository.findAll().stream()
        		 .map(Style -> modelMapper.map(Style, StyleResponse.class))
                .collect(Collectors.toList());
    }
    
    @Override
    public StyleResponse getStyleById(Long id) 
    {
    	Style style = styleRepository.findById(id).orElse(null);
    	return style != null ? modelMapper.map(style, StyleResponse.class) : null;
    }

    @Override
    public StyleResponse createStyle(CreateStyleRequest createStyle) 
    {
    	Style style = new Style();
    	style.setName(createStyle.getName());
		
		Style styleCreated = styleRepository.save(style);
		return styleCreated != null ? modelMapper.map(styleCreated, StyleResponse.class) : null;
    }
    
    @Override
    public StyleResponse updateStyle(Long id, UpdateStyleRequest updateStyleRequest) throws NoSuchFieldException, IllegalAccessException {
    	Optional<Style> optionalStyle = styleRepository.findById(id);
        
        if (optionalStyle.isPresent()) {
            Style entity = optionalStyle.get();

            Field[] fields = UpdateStyleRequest.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // access private fields
                Object newValue = field.get(updateStyleRequest);
                
                if (newValue != null) {
                    Field entityField = entity.getClass().getDeclaredField(field.getName());
                    entityField.setAccessible(true);
                    entityField.set(entity, newValue);
                }
            }
            styleRepository.save(entity);
            return modelMapper.map(entity, StyleResponse.class);
        } else {
            throw new RuntimeException("Style not found with id: " + id);
        }
    }

    @Override
    public void deleteStyle(Long id) 
    {
//        styleRepository.deleteById(id);
    	Optional<Style> optionalStyle = styleRepository.findById(id);
        if (optionalStyle.isPresent()) {
            Style style = optionalStyle.get();
            style.setIsDeleted(true);
            styleRepository.save(style);
        } else {        	
        	throw new RuntimeException("Style not found with id: " + id);
        }
    }
}
