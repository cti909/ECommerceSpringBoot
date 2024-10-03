package com.example.DemoSpringBootAPI.Repository.Implements;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.example.DemoSpringBootAPI.Data.Entities.Product;
import com.example.DemoSpringBootAPI.Repository.Interfaces.IProductCustomRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class ProductCustomRepository implements IProductCustomRepository
{
	@PersistenceContext
    private final EntityManager entityManager;

    public ProductCustomRepository(EntityManager entityManager)
    {
    	this.entityManager = entityManager;
    }

//    @Override
//    public Page<Product> filterProducts(Long categoryId, Long colorId, String size, Long priceMin, Long priceMax, Long styleId, Pageable pageable) {
//        StringBuilder queryStr = new StringBuilder("SELECT p FROM Product p WHERE 1=1 ");
//        StringBuilder countQueryStr = new StringBuilder("SELECT COUNT(p) FROM Product p WHERE 1=1 ");
////        List<String> conditions = new ArrayList<>();
//
//        if (categoryId != null) {
//            queryStr.append(" AND p.category.id = :categoryId");
//            countQueryStr.append(" AND p.category.id = :categoryId");
//        }
//        if (colorId != null) {
//            queryStr.append(" AND EXISTS (SELECT pc FROM ProductColor pc WHERE pc.product.id = p.id AND pc.color.id = :colorId)");
//            countQueryStr.append(" AND EXISTS (SELECT pc FROM ProductColor pc WHERE pc.product.id = p.id AND pc.color.id = :colorId)");
//        }
//        if (StringUtils.hasText(size)) {
//            queryStr.append(" AND JSON_CONTAINS(p.productSize, :size, '$')");
//            countQueryStr.append(" AND JSON_CONTAINS(p.productSize, :size, '$')");
//        }
//        if (priceMin != null) {
//            queryStr.append(" AND p.pricePromotion >= :priceMin");
//            countQueryStr.append(" AND p.pricePromotion >= :priceMin");
//        }
//        if (priceMax != null) {
//            queryStr.append(" AND p.pricePromotion <= :priceMax");
//            countQueryStr.append(" AND p.pricePromotion <= :priceMax");
//        }
//        if (styleId != null) {
//            queryStr.append(" AND p.style.id = :styleId");
//            countQueryStr.append(" AND p.style.id = :styleId");
//        }
//
//        // Tạo query từ string query đã build
//        TypedQuery<Product> query = entityManager.createQuery(queryStr.toString(), Product.class);
//        TypedQuery<Long> countQuery = entityManager.createQuery(countQueryStr.toString(), Long.class);
//
//        if (categoryId != null) {
//            query.setParameter("categoryId", categoryId);
//            countQuery.setParameter("categoryId", categoryId);
//        }
//        if (colorId != null) {
//            query.setParameter("colorId", colorId);
//            countQuery.setParameter("colorId", colorId);
//        }
//        if (StringUtils.hasText(size)) {
//            query.setParameter("size", size);
//            countQuery.setParameter("size", size);
//        }
//        if (priceMin != null) {
//            query.setParameter("priceMin", priceMin);
//            countQuery.setParameter("priceMin", priceMin);
//        }
//        if (priceMax != null) {
//            query.setParameter("priceMax", priceMax);
//            countQuery.setParameter("priceMax", priceMax);
//        }
//        if (styleId != null) {
//            query.setParameter("styleId", styleId);
//            countQuery.setParameter("styleId", styleId);
//        }
//
//        query.setFirstResult((int) pageable.getOffset());
//        query.setMaxResults(pageable.getPageSize());
//
//        Long totalCount = countQuery.getSingleResult();
//
//        return new PageImpl<>(query.getResultList(), pageable, totalCount);
//    }

}
