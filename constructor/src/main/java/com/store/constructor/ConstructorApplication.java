package com.store.constructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConstructorApplication {

	private static final Logger logger = LoggerFactory.getLogger(ConstructorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConstructorApplication.class, args);
		logger.info("constructor has started");
	}

}
