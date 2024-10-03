package com.example.DemoSpringBootAPI.Service.Implements;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.DemoSpringBootAPI.Data.Entities.Color;
import com.example.DemoSpringBootAPI.Repository.ColorRepository;
import com.example.DemoSpringBootAPI.Service.Dtos.Color.CreateColorRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Color.UpdateColorRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Color.ColorResponse;
import com.example.DemoSpringBootAPI.Service.Interfaces.IColorService;

@Service
public class ColorService implements IColorService
{
	private final ColorRepository colorRepository;
    private final ModelMapper modelMapper;

    public ColorService(ColorRepository colorRepository, ModelMapper modelMapper) 
    {
        this.colorRepository = colorRepository;
		this.modelMapper = modelMapper;
    }

    @Override
    public List<ColorResponse> getAllColors() 
    {
        return colorRepository.findAll().stream()
        		 .map(color -> modelMapper.map(color, ColorResponse.class))
                .collect(Collectors.toList());
    }
    
    @Override
    public ColorResponse getColorById(Long id) 
    {
    	Color color = colorRepository.findById(id).orElse(null);
    	return color != null ? modelMapper.map(color, ColorResponse.class) : null;
    }

    @Override
    public ColorResponse createColor(CreateColorRequest createColor) 
    {
    	Color color = new Color();
    	color.setName(createColor.getName());
    	color.setColor(createColor.getColor());
		
		Color colorCreated = colorRepository.save(color);
		return colorCreated != null ? modelMapper.map(colorCreated, ColorResponse.class) : null;
    }
    
    @Override
    @Transactional
    public ColorResponse updateColor(Long id, UpdateColorRequest updateColorRequest) throws NoSuchFieldException, IllegalAccessException {
        Optional<Color> optionalColor = colorRepository.findById(id);
        
        if (optionalColor.isPresent()) {
            Color entity = optionalColor.get();

            Field[] fields = UpdateColorRequest.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // access private fields
                Object newValue = field.get(updateColorRequest);
                
                if (newValue != null) {
                    Field entityField = entity.getClass().getDeclaredField(field.getName());
                    entityField.setAccessible(true);
                    entityField.set(entity, newValue);
                }
            }
            colorRepository.save(entity);
            return modelMapper.map(entity, ColorResponse.class);
        } else {
            throw new RuntimeException("Color not found with id: " + id);
        }
    }
    @Override
    public void deleteColor(Long id) 
    {
//        colorRepository.deleteById(id);
    	Optional<Color> optionalColor = colorRepository.findById(id);
        if (optionalColor.isPresent()) {
            Color color = optionalColor.get();
            color.setIsDeleted(true);
            colorRepository.save(color);
        } else {
        	throw new RuntimeException("Color not found with id: " + id);        	
        }
    }
}
