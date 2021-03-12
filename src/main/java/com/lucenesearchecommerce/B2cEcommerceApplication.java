package com.lucenesearchecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EntityScan({"com.lucenesearchecommerce.entity"})
@Import(LuceneConfig.class)
public class B2cEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(B2cEcommerceApplication.class, args);
	}
}

