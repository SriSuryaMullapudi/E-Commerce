package com.SriSurya.Mullapudi.service;

import com.SriSurya.Mullapudi.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import com.SriSurya.Mullapudi.model.OrderItem;

@Service
public class OrderItemServiceImplementation implements OrderItemService{
	
	private OrderItemRepository orderItemRepository;
	public OrderItemServiceImplementation(OrderItemRepository orderItemRepository) {
		this.orderItemRepository=orderItemRepository;
	}
	
	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		return orderItemRepository.save(orderItem);
	}

}
