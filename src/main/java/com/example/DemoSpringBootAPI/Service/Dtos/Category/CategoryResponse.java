package com.example.DemoSpringBootAPI.Service.Dtos.Category;

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
public class CategoryResponse 
{
	private Long id;
    private String name;
    private Boolean isDeleted;
//    private List<ProductResponse> products;
}
