package com.movie.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MovieBookingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieBookingServiceApplication.class, args);
		
		// TODO: Separate Microservice for theater operations
		// TODO: Separate Worker for processing ticket generation and sending notification
		// TODO: Kafka producer to send 'Generate ticket' message to kafka so producer can read and generate ticket
		// TODO: Proper authorization from resource server
		// TODO: Configure cache to store static data like Theatre info, Movie info to cache for faster access
	}

}
