package com.gateaway.springCloudService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringCloudServiceApplication {

	private static final Logger logger = LoggerFactory.getLogger(SpringCloudServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServiceApplication.class, args);
		logger.info("gateway has started");
	}


}
