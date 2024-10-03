package com.example.DemoSpringBootAPI.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.DemoSpringBootAPI.Data.Entities.Product;
import com.example.DemoSpringBootAPI.Repository.Interfaces.IProductCustomRepository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, IProductCustomRepository {

	@Query("SELECT p FROM Product p " +
	           "WHERE (:categoryId IS NULL OR p.category.id = :categoryId) " +
	           "AND (:colorId IS NULL OR EXISTS (SELECT pc FROM ProductColor pc WHERE pc.product.id = p.id AND pc.color.id = :colorId)) " +
	           "AND (:size IS NULL OR p.productSize LIKE %:size%) " +
	           "AND (:priceMin IS NULL OR p.pricePromotion >= :priceMin) " +
	           "AND (:priceMax IS NULL OR p.pricePromotion <= :priceMax) " +
	           "AND (:styleId IS NULL OR p.style.id = :styleId)")
	    Page<Product> filterProducts(@Param("categoryId") Long categoryId,
	                                 @Param("colorId") Long colorId,
	                                 @Param("size") String size,
	                                 @Param("priceMin") Long priceMin,
	                                 @Param("priceMax") Long priceMax,
	                                 @Param("styleId") Long styleId,
	                                 Pageable pageable);
	
	
//	@Query("SELECT p FROM Product p " +
//		       "WHERE (:categoryId IS NULL OR p.category.id = :categoryId) " +
//		       "AND (:colorId IS NULL OR EXISTS (SELECT pc FROM ProductColor pc WHERE pc.product.id = p.id AND pc.color.id = :colorId)) " +
//		       "AND (:size IS NULL OR JSON_CONTAINS(p.productSize, :size)) " + 
//		       "AND (:priceMin IS NULL OR p.pricePromotion >= :priceMin) " +
//		       "AND (:priceMax IS NULL OR p.pricePromotion <= :priceMax) " +
//		       "AND (:styleId IS NULL OR p.style.id = :styleId)")
//		Page<Product> filterProducts(
//		    @Param("categoryId") Long categoryId,
//		    @Param("colorId") Long colorId,
//		    @Param("size") String size,
//		    @Param("priceMin") Long priceMin,
//		    @Param("priceMax") Long priceMax,
//		    @Param("styleId") Long styleId,
//		    Pageable pageable);
	

}
