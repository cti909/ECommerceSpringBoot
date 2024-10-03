package com.example.DemoSpringBootAPI.Service.Dtos.Product;

import java.time.LocalDateTime;
import java.util.List;

import com.example.DemoSpringBootAPI.Data.EntityEnum.ProductSize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequest
{
	private String name;
    private String description;
    private Integer quantity;
    private Long starTotal;
    private Long reviewTotal;
    private Long priceOrigin;
    private Long pricePromotion;
    private LocalDateTime dateExpire;
//    private MultipartFile[] listUrlImage;
    private List<ProductSize> productSizes;

    private List<Long> colorIds; // table color
    private Long categoryId;
    private Long styleId;
    private Long userId;
}
