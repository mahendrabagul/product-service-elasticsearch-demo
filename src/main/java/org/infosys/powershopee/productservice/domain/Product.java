package org.infosys.powershopee.productservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "powershopee", type = "products")
public class Product {

	@Id
	private String id;
	private String name;
	private double price;
	private int quantity;
	private String supplier;

	public Product() {
	}

	public Product(String id, String name, int quantity, double price, String supplier) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
		this.supplier = supplier;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "Book{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", quantity='" + quantity + '\'' + ", price='"
				+ price + '\'' + '}';
	}
}