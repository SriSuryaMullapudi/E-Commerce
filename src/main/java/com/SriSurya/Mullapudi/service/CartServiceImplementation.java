package com.SriSurya.Mullapudi.service;

import com.SriSurya.Mullapudi.exception.ProductException;
import com.SriSurya.Mullapudi.repository.CartRepository;
import org.springframework.stereotype.Service;

import com.SriSurya.Mullapudi.model.Cart;
import com.SriSurya.Mullapudi.model.CartItem;
import com.SriSurya.Mullapudi.model.Product;
import com.SriSurya.Mullapudi.model.User;
import com.SriSurya.Mullapudi.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService{

	private CartRepository cartRepository;
	private CartItemService cartItemService;
	private ProductService productService;
	
	
	public CartServiceImplementation(CartRepository cartRepository,CartItemService cartItemService,
			ProductService productService) {
		this.cartRepository=cartRepository;
		this.productService=productService;
		this.cartItemService=cartItemService;
	}
	
	@Override
	public Cart createCart(User user) {
		Cart cart = new Cart();
		cart.setUser(user);
		Cart createdCart=cartRepository.save(cart);
		return createdCart;
	}

	@Override
	public CartItem addCartItem(Long userId, AddItemRequest req) throws ProductException {
		Cart cart =	cartRepository.findByUserId(userId);
		Product product = productService.findProductById(req.getProductId());
		CartItem isPresent = cartItemService.isCartItemExist(cart, product, req.getSize(), userId);
		
		if(isPresent==null) {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserId(userId);
			
			int price=req.getQuantity()*product.getDiscountedPrice();
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());
			
			CartItem createdCartItem = cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
			return createdCartItem;
		}
		
		
		return isPresent;
	}

	@Override
	public Cart findUserCart(Long userId) {
		Cart cart =	cartRepository.findByUserId(userId);
		int totalPrice=0;
		int totalDiscountedPrice=0;
		int totalItem=0;
		for(CartItem cartsItem : cart.getCartItems()) {
			totalPrice+=cartsItem.getPrice();
			totalDiscountedPrice+=cartsItem.getDiscountedPrice();
			totalItem+=cartsItem.getQuantity();
		}
		
		cart.setTotalPrice(totalPrice);
		cart.setTotalItem(cart.getCartItems().size());
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setDiscount(totalPrice-totalDiscountedPrice);
		cart.setTotalItem(totalItem);
		
		return cartRepository.save(cart);
	}

}
