package com.tekion.GameOfCricket;


import org.apache.log4j.BasicConfigurator;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

@SpringBootApplication
public class GameOfCricketApplication {
	static Logger logger = LogManager.getLogger(GameOfCricketApplication.class);
	public static void main(String[] args){
		System.setProperty("log4j.debug", "true");
		PropertyConfigurator.configure("src/main/resources/log4j.properties");
		logger.info("Heyaaaa");
		logger.info("Log4j version: " + org.apache.logging.log4j.util.PropertiesUtil.class.getPackage().getImplementationVersion());
		SpringApplication.run(GameOfCricketApplication.class, args);
	}
}
