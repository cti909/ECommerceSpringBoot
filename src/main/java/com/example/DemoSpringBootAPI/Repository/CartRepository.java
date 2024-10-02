package com.example.DemoSpringBootAPI.Repository;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.DemoSpringBootAPI.Data.Entities.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long>  
{
	boolean existsByUserIdAndProductId(Long userId, Long productId);
	Page<Cart> findByUserId(Long userId, Pageable pageable);
}
