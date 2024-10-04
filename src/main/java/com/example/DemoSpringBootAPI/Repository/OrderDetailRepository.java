package com.example.DemoSpringBootAPI.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DemoSpringBootAPI.Data.Entities.Order;
import com.example.DemoSpringBootAPI.Data.Entities.OrderDetail;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>
{

}
