package com.mayikt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.mayikt.repository")
public class AppSharding {

	public static void main(String[] args) {
		SpringApplication.run(AppSharding.class, args);
	}

}
