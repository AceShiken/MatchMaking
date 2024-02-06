package com.chitchat.matchmaking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"com.chitchat.matchmaking.repository"})
@EnableScheduling
public class MatchMakingApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatchMakingApplication.class, args);
	}

}
