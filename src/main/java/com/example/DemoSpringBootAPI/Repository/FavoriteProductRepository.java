package com.example.DemoSpringBootAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DemoSpringBootAPI.Data.Entities.FavoriteProduct;

@Repository
public interface FavoriteProductRepository extends JpaRepository<FavoriteProduct, Long> {

}
