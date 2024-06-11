package com.config.serverConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ServerConfigApplication {

	private static final Logger logger = LoggerFactory.getLogger(ServerConfigApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServerConfigApplication.class, args);
		logger.info("config server has started");
	}

}
