package com.example.DemoSpringBootAPI.Service.Interfaces;

import java.util.List;

import com.example.DemoSpringBootAPI.Service.Dtos.Color.ColorResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Color.CreateColorRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Color.UpdateColorRequest;

public interface IColorService {
	public List<ColorResponse> getAllColors();
	public ColorResponse getColorById(Long id);
	public ColorResponse createColor(CreateColorRequest createColor);
	public ColorResponse updateColor(Long id, UpdateColorRequest updateColor) throws NoSuchFieldException, IllegalAccessException;
	public void deleteColor(Long id);
}
