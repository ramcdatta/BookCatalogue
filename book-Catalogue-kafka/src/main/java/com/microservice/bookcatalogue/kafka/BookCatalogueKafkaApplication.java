package com.microservice.bookcatalogue.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class BookCatalogueKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookCatalogueKafkaApplication.class, args);
	}

}
