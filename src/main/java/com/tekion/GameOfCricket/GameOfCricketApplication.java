package com.tekion.GameOfCricket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tekion.GameOfCricket.SQLRepository")
public class GameOfCricketApplication {
	static Logger logger = LogManager.getLogger(GameOfCricketApplication.class);
	public static void main(String[] args){
		System.setProperty("log4j.debug", "true");
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		SpringApplication.run(GameOfCricketApplication.class, args);
	}
}
