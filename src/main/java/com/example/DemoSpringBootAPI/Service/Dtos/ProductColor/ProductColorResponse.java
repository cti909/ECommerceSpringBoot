package com.example.DemoSpringBootAPI.Service.Dtos.ProductColor;

import com.example.DemoSpringBootAPI.Data.Entities.Color;
import com.example.DemoSpringBootAPI.Data.Entities.Product;

import lombok.AllArgsConstructor;
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
