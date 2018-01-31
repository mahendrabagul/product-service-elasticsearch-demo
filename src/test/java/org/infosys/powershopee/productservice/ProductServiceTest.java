package org.infosys.powershopee.productservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.infosys.powershopee.productservice.domain.Product;
import org.infosys.powershopee.productservice.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductServiceApplication.class)
public class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Autowired
	private ElasticsearchTemplate esTemplate;

	@Before
	public void before() {
		esTemplate.deleteIndex(Product.class);
		esTemplate.createIndex(Product.class);
		esTemplate.putMapping(Product.class);
		esTemplate.refresh(Product.class);
	}

	@Test
	public void testSave() {

		Product product = new Product("1001", "Shampoo", 2, 20, "Hair & Shoulders");
		Product testProduct = productService.save(product);

		assertNotNull(testProduct.getId());
		assertEquals(testProduct.getName(), product.getName());
		assertEquals(testProduct.getSupplier(), product.getSupplier());
		assertEquals(testProduct.getQuantity(), product.getQuantity());

	}

	@Test
	public void testFindOne() {

		Product product = new Product("1001", "Shampoo", 2, 20, "Hair & Shoulders");
		productService.save(product);

		Product testProduct = productService.findOne(product.getId());

		assertNotNull(testProduct.getId());
		assertEquals(testProduct.getName(), product.getName());
		assertEquals(testProduct.getSupplier(), product.getSupplier());
		assertEquals(testProduct.getQuantity(), product.getQuantity());

	}

	@Test
	public void testFindByName() {

		Product product = new Product("1001", "Shampoo", 2, 20, "Hair & Shoulders");
		productService.save(product);

		List<Product> products = productService.findByName(product.getName());
		assertThat(products.size(), is(1));
	}

	@Test
	public void testFindBySupplier() {

		List<Product> productList = new ArrayList<>();

		productList.add(new Product("1001", "Shampoo", 2, 20, "Hair & Shoulders"));
		productList.add(new Product("1002", "Soap", 5, 20, "Hair & Shoulders"));
		productList.add(new Product("1003", "Shirt", 2, 20, "Arrow"));
		productList.add(new Product("1004", "Jeans", 1, 20, "Levi's"));
		productList.add(new Product("1005", "Refridgerator", 1, 20000, "LG"));

		for (Product product : productList) {
			productService.save(product);
		}

		Page<Product> bySupplier = productService.findBySupplier("LG", new PageRequest(0, 10));
		assertThat(bySupplier.getTotalElements(), is(1L));

		bySupplier = productService.findBySupplier("Hair & Shoulders", new PageRequest(0, 10));
		assertThat(bySupplier.getTotalElements(), is(2L));

	}

	@Test
	public void testDelete() {

		Product product = new Product("1001", "Shampoo", 2, 20, "Hair & Shoulders");
		productService.save(product);
		productService.delete(product);
		Product testProduct = productService.findOne(product.getId());
		assertNull(testProduct);
	}

}