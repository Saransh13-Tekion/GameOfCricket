package com.tekion.GameOfCricket;

import com.tekion.GameOfCricket.Models.Match;
import com.tekion.GameOfCricket.Services.MatchService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class GameOfCricketApplication {

	public static void main(String[] args) {

		SpringApplication.run(GameOfCricketApplication.class, args);

		System.out.print("Enter Number of Overs: ");
		Scanner sc = new Scanner(System.in);
		MatchService match = new MatchService(sc.nextInt()); // Asking User for Number of Overs.
		match.startMatch();
	}
}
