package com.example.DemoSpringBootAPI.Service.Dtos.Product;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse
{
	private Long id;
    private String name;
    private String description;
    private Integer quantity;
    private Long starTotal;
    private Long reviewTotal; // starAverage = starTotal/reviewTotal
    private Long priceOrigin;
    private Long pricePromotion;
    private LocalDateTime dateExpire;
    private String listUrlImage;
    private String productSize;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//    private List<Cart> carts;
//    private List<Comment> comments;
//    private List<FavoriteProduct> favoriteProducts;
//    private List<ProductColor> productColors;
}
