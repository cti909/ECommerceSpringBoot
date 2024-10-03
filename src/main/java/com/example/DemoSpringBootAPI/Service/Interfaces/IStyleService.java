package com.example.DemoSpringBootAPI.Service.Interfaces;

import java.util.List;

import com.example.DemoSpringBootAPI.Service.Dtos.Style.CreateStyleRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Style.StyleResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Style.UpdateStyleRequest;

public interface IStyleService {
	public List<StyleResponse> getAllStyles();
	public StyleResponse getStyleById(Long id);
	public StyleResponse createStyle(CreateStyleRequest createStyle);
	public StyleResponse updateStyle(Long id, UpdateStyleRequest updateStyleRequest) throws NoSuchFieldException, IllegalAccessException;
	public void deleteStyle(Long id);
}
