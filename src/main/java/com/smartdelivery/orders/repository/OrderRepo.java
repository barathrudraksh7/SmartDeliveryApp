package com.smartdelivery.orders.repository;

import com.smartdelivery.orders.model.Order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//extending the jpaRepo by specifying the Entity and it's primary key
public interface OrderRepo extends JpaRepository<Order, Long>{ 

    List<Order> findByCustomerName(String name);
        
}
