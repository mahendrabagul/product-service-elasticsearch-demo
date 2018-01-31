package org.infosys.powershopee.productservice.service.impl;

import java.util.List;

import org.infosys.powershopee.productservice.domain.Product;
import org.infosys.powershopee.productservice.repository.ProductRepository;
import org.infosys.powershopee.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	public void setproductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public void delete(Product product) {
		productRepository.delete(product);
	}

	public Product findOne(String id) {
		return productRepository.findOne(id);
	}

	public Iterable<Product> findAll() {
		return productRepository.findAll();
	}

	public Page<Product> findBySupplier(String supplier, PageRequest pageRequest) {
		return productRepository.findBySupplier(supplier, pageRequest);
	}

	public List<Product> findByName(String name) {
		return productRepository.findByName(name);
	}

}