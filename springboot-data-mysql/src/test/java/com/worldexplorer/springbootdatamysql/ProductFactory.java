package com.worldexplorer.springbootdatamysql;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import com.worldexplorer.springbootdatamysql.entity.Category;
import com.worldexplorer.springbootdatamysql.entity.Product;

public class ProductFactory {
	public static List<Long> productIds = new ArrayList<>();
	public static List<Product> products = new ArrayList<>();
	static Random random = new Random();
	static AtomicLong productId = new AtomicLong();
	static AtomicLong categoryId = new AtomicLong();
	
	public static Product getInstance() {
		Product product = Product.builder()
				.id(productId.getAndIncrement())
				.name("SmartPhone")
				.description("this is a smart phone" + random.nextInt(100))
				.stock(random.nextDouble(4, 1943999))
				.price(random.nextFloat(1, 1000000))
				.status("created newest")
				.createdAtDate(new Date())
				.category(Category.builder().id(categoryId.getAndIncrement()).name("phone").build())
				.build();
		productIds.add(product.getId());
		products.add(product);
		return product;
	}

	public static long getRandomProductId() {
		return productIds.get(random.nextInt(0, productIds.size()));
	}

	public static Product getRandomProduct() {
		return products.get(random.nextInt(0, productIds.size()));
	}
}
