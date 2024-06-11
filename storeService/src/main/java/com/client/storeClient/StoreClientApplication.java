package com.client.storeClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoreClientApplication {

	private static final Logger logger = LoggerFactory.getLogger(StoreClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StoreClientApplication.class, args);
		logger.info("store has started");
	}

}
