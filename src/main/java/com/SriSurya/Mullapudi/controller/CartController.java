package com.SriSurya.Mullapudi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SriSurya.Mullapudi.exception.ProductException;
import com.SriSurya.Mullapudi.exception.UserException;
import com.SriSurya.Mullapudi.model.Cart;
import com.SriSurya.Mullapudi.model.CartItem;
import com.SriSurya.Mullapudi.model.User;
import com.SriSurya.Mullapudi.request.AddItemRequest;
import com.SriSurya.Mullapudi.response.ApiResponse;
import com.SriSurya.Mullapudi.service.CartService;
import com.SriSurya.Mullapudi.service.UserService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	private CartService cartService;
	private UserService userService;
	
	public CartController(CartService cartService,UserService userService) {
		this.cartService=cartService;
		this.userService=userService;
	}
	
	@GetMapping("/")
	public ResponseEntity<Cart> findUserCartHandler(@RequestHeader("Authorization") String jwt) throws UserException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		Cart cart=cartService.findUserCart(user.getId());
		
		System.out.println("cart - "+cart.getUser().getEmail());
		
		return new ResponseEntity<Cart>(cart,HttpStatus.OK);
	}
	
	@PutMapping("/add")
	public ResponseEntity<CartItem> addItemToCart(@RequestBody AddItemRequest req, 
			@RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		
		User user=userService.findUserProfileByJwt(jwt);
		
		CartItem item = cartService.addCartItem(user.getId(), req);
		
		ApiResponse res= new ApiResponse("Item Added To Cart Successfully",true);
		
		return new ResponseEntity<>(item,HttpStatus.ACCEPTED);
		
	}
}
