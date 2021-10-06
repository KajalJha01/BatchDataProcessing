package com.training;

import java.time.LocalDate;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.training.entity.Product;
import com.training.repo.ProductRepo;

@ComponentScan
@SpringBootApplication
public class BatchDataProcessingApplication implements CommandLineRunner {
	
//	@Autowired
//	private ProductRepo productRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(BatchDataProcessingApplication.class, args);
	}
	
	
//	@PostConstruct
//	public void init() {
//		Product p1= new Product(123,"123","123334","123325","132433","122333","123233");
//		productRepo.save(p1);
//		
//	}
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("I am in command line runner.");
		
		
		
		
	}
	
	
}
