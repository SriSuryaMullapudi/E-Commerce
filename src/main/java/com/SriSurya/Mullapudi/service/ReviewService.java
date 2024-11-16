package com.SriSurya.Mullapudi.service;

import java.util.List;

import com.SriSurya.Mullapudi.exception.ProductException;
import com.SriSurya.Mullapudi.model.Review;
import com.SriSurya.Mullapudi.model.User;
import com.SriSurya.Mullapudi.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req,User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);
	
}
