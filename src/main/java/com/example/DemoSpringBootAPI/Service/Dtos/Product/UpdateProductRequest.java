package com.example.DemoSpringBootAPI.Service.Dtos.Product;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.DemoSpringBootAPI.Data.EntityEnum.ProductSize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
	private String name;
    private String description;
    private Integer quantity;
    private Long starTotal;
    private Long reviewTotal;
    private Long priceOrigin;
    private Long pricePromotion;
    private LocalDateTime dateExpire;
    private List<MultipartFile> listUrlImage; 
    private List<ProductSize> productSizes;
    private Boolean isDeleted;
    private LocalDateTime updatedAt = LocalDateTime.now(); // update

    private Long categoryId;     
    private Long styleId;        
    private Long userId;
}
