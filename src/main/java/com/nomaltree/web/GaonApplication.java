package com.nomaltree.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
public class GaonApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaonApplication.class, args);
	}

}
