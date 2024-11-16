package com.SriSurya.Mullapudi.service;

import com.SriSurya.Mullapudi.exception.ProductException;
import com.SriSurya.Mullapudi.model.Cart;
import com.SriSurya.Mullapudi.model.CartItem;
import com.SriSurya.Mullapudi.model.User;
import com.SriSurya.Mullapudi.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	
	public CartItem addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
}
