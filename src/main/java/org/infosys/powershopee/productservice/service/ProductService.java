package org.infosys.powershopee.productservice.service;

import java.util.List;

import org.infosys.powershopee.productservice.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ProductService {

	Product save(Product product);

	void delete(Product product);

	Product findOne(String id);

	Iterable<Product> findAll();

	Page<Product> findBySupplier(String supplier, PageRequest pageRequest);

	List<Product> findByName(String name);

}