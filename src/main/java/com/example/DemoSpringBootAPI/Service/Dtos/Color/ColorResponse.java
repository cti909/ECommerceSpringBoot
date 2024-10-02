package com.example.DemoSpringBootAPI.Service.Dtos.Color;

import java.time.LocalDateTime;
import java.util.List;

import com.example.DemoSpringBootAPI.Data.EntityEnum.UserRole;
import com.example.DemoSpringBootAPI.Service.Dtos.Product.ProductResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ColorResponse 
{
	private Long id;
    private String name;
    private String color;
    private Boolean isDeleted;
}
