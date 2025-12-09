package com.officeproject.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class BackendApplication {
	@Autowired
	private MongoTemplate mongoTemplate;


	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void displayDatabaseName() {
		String dbName = mongoTemplate.getDb().getName();
		System.out.println("✅ Connected to MongoDB Database: " + dbName);
	}
}
