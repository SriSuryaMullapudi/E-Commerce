package com.SriSurya.Mullapudi.service;

import java.util.List;

import com.SriSurya.Mullapudi.exception.ProductException;
import org.springframework.data.domain.Page;

import com.SriSurya.Mullapudi.model.Product;
import com.SriSurya.Mullapudi.request.CreateProductRequest;

public interface ProductService {

	public Product createProduct(CreateProductRequest req) throws ProductException;
	
	public String deleteProduct(Long productId) throws ProductException;
	
	public Product updateProduct(Long productId,Product req)throws ProductException;
	
	public Product findProductById(Long id) throws ProductException;
	
	public List<Product> findProductByCategory(String category);
	
	public List<Product> getAllProducts();
	
	public Page<Product> getAllProduct(String category, List<String>colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount,String sort, String stock, Integer pageNumber, Integer pageSize);
	
	public List<Product> searchProduct(String query);
	
}
