package com.mayikt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.mayikt.repository")
@MapperScan("com.mayikt.mapper")
public class AppSharding {

	public static void main(String[] args) {
		SpringApplication.run(AppSharding.class, args);
	}

}
