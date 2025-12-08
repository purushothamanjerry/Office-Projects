package com.officeproject.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class BackendApplication {


	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
