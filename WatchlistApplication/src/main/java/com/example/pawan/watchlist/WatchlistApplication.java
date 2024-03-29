package com.example.pawan.watchlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication

public class WatchlistApplication {

	public static void main(String[] args) {
		SpringApplication.run(WatchlistApplication.class, args);
	}

}

//   project Structure----
//							        get request       to user
//											|			^
//											|			|
//											|			|
//							--------------------------------------------
//							|          controller layer                |
//							--------------------------------------------
//											|			^
//											|			|
//							--------------------------------------------
//							|          business/service layer          |
//							--------------------------------------------
//											|			^
//											|			|
//							--------------------------------------------
//							|       data access/repository layer       |
//							--------------------------------------------
//											|			^
//											|			|
//							--------------------------------------------
//							|          model/entity layer              |
//							--------------------------------------------
//											|			^
//											|			|
//							--------------------------------------------
//							|             databse layer                |
//							--------------------------------------------