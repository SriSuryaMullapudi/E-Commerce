package com.SriSurya.Mullapudi.service;

import java.util.List;

import com.SriSurya.Mullapudi.exception.ProductException;

import com.SriSurya.Mullapudi.model.Rating;
import com.SriSurya.Mullapudi.model.User;
import com.SriSurya.Mullapudi.request.RatingRequest;


public interface RatingService {

	public Rating createRating(RatingRequest req,User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);
}
