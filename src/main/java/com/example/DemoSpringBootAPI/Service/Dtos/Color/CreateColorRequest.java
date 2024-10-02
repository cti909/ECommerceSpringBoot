package com.example.DemoSpringBootAPI.Service.Dtos.Color;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateColorRequest 
{
	private String name;
    private String color;
}
