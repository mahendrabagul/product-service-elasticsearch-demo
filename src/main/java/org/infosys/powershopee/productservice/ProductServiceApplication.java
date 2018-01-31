package org.infosys.powershopee.productservice;

import java.util.Map;

import org.elasticsearch.client.Client;
import org.infosys.powershopee.productservice.domain.Product;
import org.infosys.powershopee.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;

	@Autowired
	private ProductService productService;

	public static void main(String args[]) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		printElasticSearchInfo();
		productService.save(new Product("1001", "Shampoo", 2, 20, "Hair & Shoulders"));
		productService.save(new Product("1002", "Soap", 5, 20, "Hair & Shoulders"));
		productService.save(new Product("1004", "Jeans", 1, 20, "Levi's"));
		Page<Product> products = productService.findBySupplier("Hair & Shoulders", new PageRequest(0, 10));
		products.forEach(x -> System.out.println(x));
	}

	// useful for debug, print elastic search details
	private void printElasticSearchInfo() {
		System.out.println("--ElasticSearch--");
		Client client = elasticsearchOperations.getClient();
		Map<String, String> asMap = client.settings().getAsMap();
		asMap.forEach((k, v) -> {
			System.out.println(k + " = " + v);
		});
		System.out.println("--ElasticSearch--");
	}
}