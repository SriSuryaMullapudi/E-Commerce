package com.SriSurya.Mullapudi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SriSurya.Mullapudi.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
