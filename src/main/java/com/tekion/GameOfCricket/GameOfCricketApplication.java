package com.tekion.GameOfCricket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GameOfCricketApplication {

	public static void main(String[] args) {

		SpringApplication.run(GameOfCricketApplication.class, args);
		Match m = new Match(); // Starting of Match
	}
}
