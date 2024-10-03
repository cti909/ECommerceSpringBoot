package com.example.DemoSpringBootAPI.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DemoSpringBootAPI.Data.Entities.Cart;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long>
{
	boolean existsByUserIdAndProductId(Long userId, Long productId);
	Page<Cart> findByUserId(Long userId, Pageable pageable);
}
