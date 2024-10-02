package com.example.DemoSpringBootAPI.Service.Dtos.Category;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryRequest 
{
	private String name;
}
