package com.microservice.orderservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.orderservice.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
