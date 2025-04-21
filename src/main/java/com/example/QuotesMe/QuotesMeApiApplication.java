package com.example.QuotesMe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class QuotesMeApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuotesMeApiApplication.class, args);
	}
	@Bean
	public PlatformTransactionManager  transactionManager(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

	@Bean
	public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory) {
		return new MongoTemplate(mongoDbFactory);
	}

}
