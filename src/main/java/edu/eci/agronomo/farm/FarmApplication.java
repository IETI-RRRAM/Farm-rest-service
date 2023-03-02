package edu.eci.agronomo.farm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class FarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmApplication.class, args);
	}

}
