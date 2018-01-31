package org.infosys.powershopee.productservice.repository;

import java.util.List;

import org.infosys.powershopee.productservice.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepository extends ElasticsearchRepository<Product, String> {

	Page<Product> findBySupplier(String supplier, Pageable pageable);

	List<Product> findByName(String name);

}