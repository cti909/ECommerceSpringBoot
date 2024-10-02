package com.example.DemoSpringBootAPI.Service.Dtos.ProductColor;

import java.time.LocalDateTime;
import java.util.List;

import com.example.DemoSpringBootAPI.Data.Entities.Color;
import com.example.DemoSpringBootAPI.Data.Entities.Product;
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
public class ProductColorResponse 
{
	private Long id;
	private Product product;
	private Color color;
}
