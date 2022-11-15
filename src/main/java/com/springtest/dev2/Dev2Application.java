package com.springtest.dev2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.springtest.dev2.*")
@EntityScan("com.springtest.dev2.model")
public class Dev2Application {

	public static void main(String[] args) {
		SpringApplication.run(Dev2Application.class, args);
	}

}
