package com.example.DemoSpringBootAPI.Service.Dtos.Product;

import java.time.LocalDateTime;

import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilterRequest {
	private Long categoryId;
    private Long colorId;
    private String size;
    private Long priceMin;
    private Long priceMax;
    private Long styleId;
    private PaginationRequest paginationRequest;
}
