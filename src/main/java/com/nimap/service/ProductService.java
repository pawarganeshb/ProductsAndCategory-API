package com.nimap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nimap.entity.Category;
import com.nimap.entity.Product;
import com.nimap.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Page<Product> getAllProducts(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public Product getProductById(Long id) {

		Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

		Category category = product.getCategory();
		if (category == null) {
			throw new RuntimeException("Product does not have a valid category associated.");
		}

		return product;
	}

	public Product createProduct(Product product) {
		if (product.getCategory() != null) {
			product.getCategory().getProducts().add(product); // Maintain bidirectional relationship
		}
		return productRepository.save(product);
	}

	public Product updateProduct(Long id, Product productDetails) {
		Product product = getProductById(id);
		product.setName(productDetails.getName());
		product.setPrice(productDetails.getPrice());
		return productRepository.save(product);
	}

	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
}
