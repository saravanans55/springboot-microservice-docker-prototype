package com.skyiots.springboot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BooksManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksManagementApplication.class, args);
	}
}
