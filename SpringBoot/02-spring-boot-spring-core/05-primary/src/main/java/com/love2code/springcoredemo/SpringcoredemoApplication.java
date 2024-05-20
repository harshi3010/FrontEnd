package com.love2code.springcoredemo;

import org.springframework.boot.SpringApplication;  //bootstrap the spring boot application
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
@SpringBootApplication(
		scanBasePackages = {"com.love2code.springcoredemo",
				"com.love2code.util"}
)
 */
@SpringBootApplication
public class SpringcoredemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcoredemoApplication.class, args);
	}

}
